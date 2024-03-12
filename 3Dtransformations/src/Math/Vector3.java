package Math;

/*
 ? Implemented operations: 
 * 1. Compute the Magnitude of a vector
 * 2. Normalize the vector changing the inital values and returning it.
 * 3. Compute the Dot product of two vectors.
 * 4. Compute the Cross product of two vectors.
 */
public class Vector3 {
    public Double x, y, z;

    public Vector3(Double x, Double y, Double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(){
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }
    public Double Magnitude(){
        Double answ; 
        answ = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
        return answ;
    }

    public void Normalize(){
        Double mag;

        mag = this.Magnitude();

        this.setX(this.x / mag);
        this.setY(this.y / mag);
        this.setZ(this.z / mag);
    }

    public Vector3 NormalizeAndReturn(){
        Double mag;
        mag = this.Magnitude();

        return new Vector3(this.getX()/mag, this.getY()/mag, this.getZ()/mag);
    }

    public Double dotProduct3(Vector3 v1){
        return this.getX() * v1.getX() + this.getY() * v1.getY() + this.getZ() * v1.getZ();
    }

    public Vector3 crossedProduct3(Vector3 v1){
        Double i = this.getY() * v1.getZ() - this.getZ()*v1.getY();
        Double j = this.getX() * v1.getZ() - this.getZ()* v1.getX();
        Double k = this.getX() * v1.getY() - this.getY() * v1.getX();

        return new Vector3(i, j, k);

    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }
    public void setValue(Double v, int i) {
        if(i == 0) {
            this.x = v;
        }else if(i == 1) {
            this.y = v;
        }else if(i == 2) {
            this.z = v;
        }
    }

    public void setX(Double x) {
        this.x = x;
    }
    public void setY(Double y) {
        this.y = y;
    }
    public void setZ(Double z) {
        this.z = z;
    }
}
