package display;

import math.Vector4;
import geometry.Ray;
import geometry.Solution;

public class Shader {
        /**
         * Compute the local illumination of a point in the scene
         * @param point Intersection point of the ray with the object
         * @param N Normal of the object at the point of intersection
         * @param Olambda Colour of the object at the point of intersection
         * @param material Material of the object
         * @return The colour of the point after computing the local illumination
         */
    public static Colour computeLocalIllumination(Vector4 point, Vector4 N, 
        Colour Olambda, Material material) {
        Colour Ilambda = new Colour(0, 0, 0);
        double Ka = material.kA;
        double Kd = material.kD;
        double Ks = material.kS;
        double n = material.n;
        Colour IaLambda = Scene.colors.get(Scene.ambientLight).copy();  // Ambient light color
        // Compute ambient light
        Colour ambient = IaLambda.timesScalar(Ka);
        ambient = ambient.timesColour(Olambda);
        // Note that timesScalar and timesColour both return a NEW colour
        Ilambda.acumColor(ambient);
        // Compute the diffuse and specular components with respect to each light in the scene
        for (Light light : Scene.lights) {
            Vector4 L = (Vector4.subtract(light.position, point));  // Light vector
            L.normalize();
            double dot = Vector4.dotProduct(L, N);
            // Check if this point is not in the shadow of another object with respect to this light
            Ray shadowRay = new Ray(point, light.position);
            Solution s = Scene.intersectForShadow(shadowRay);   // Null if no under shadow
            if (dot > 0 && s == null) {
                // Compute the diffuse component
                Colour IpLambda = Scene.colors.get(light.colorIndex);   // Color of the point light
                Colour diffuse = IpLambda.timesScalar(Kd*dot);
                diffuse = diffuse.timesColour(Olambda);
                // Now compute the specular component
                Colour specular = new Colour(0, 0, 0);    
                Vector4 Lneg = Vector4.subtract(point, light.position); // Negative light vector
                Lneg.normalize();
                // Compute R, the reflected light vector
                double dot2 = Vector4.dotProduct(Lneg, N);
                Vector4 R = Lneg.subtract(N.timesScalar(2 * dot2));
                Vector4 V = Vector4.subtract(Scene.camera.cameraPosition, point);   // View vector
                V.normalize();
                double dot3 = Vector4.dotProduct(R, V);
                if (dot3 > 0) {
                    specular = IpLambda.timesScalar(Math.pow(Ks*dot3, n)).timesColour(Olambda);
                }
                // Accumulate the diffuse and specular components
                Ilambda.acumColor(diffuse);
                Ilambda.acumColor(specular);
            }
        }
        return Ilambda;
    }
}
