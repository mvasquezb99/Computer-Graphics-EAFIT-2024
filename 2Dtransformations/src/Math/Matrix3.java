package Math;


public class Matrix3 {
    public Vector3[] matrix;

    public Matrix3(Vector3 v1, Vector3 v2, Vector3 v3) {
        this.matrix = new Vector3[3];
        this.matrix[0] = v1;
        this.matrix[1] = v2;
        this.matrix[2] = v3;
    }

    public Matrix3() {
        this.matrix = new Vector3[3];
        this.matrix[0] = new Vector3(1.0, 0.0, 0.0);
        this.matrix[1] = new Vector3(0.0, 1.0, 0.0);
        this.matrix[2] = new Vector3(0.0, 0.0, 1.0);
    }

    public Matrix3 transpose() {

        Double a12 = this.matrix[0].getY();
        Double a13 = this.matrix[0].getZ();
        Double a11 = this.matrix[0].getX();
        Double a23 = this.matrix[1].getZ();
        Double a21 = this.matrix[1].getX();
        Double a22 = this.matrix[1].getY();
        Double a31 = this.matrix[2].getX();
        Double a32 = this.matrix[2].getY();
        Double a33 = this.matrix[2].getZ();

        Vector3 v1 = new Vector3(a11, a21, a31);
        Vector3 v2 = new Vector3(a12, a22, a32);
        Vector3 v3 = new Vector3(a13, a23, a33);

        return new Matrix3(v1, v2, v3);

    }

    public Point3 timesP3(Point3 point) {
        Point3 answPoint = new Point3();
        for (int i = 0; i < 3; i++) {
            Vector3 va = this.matrix[i]; 
            answPoint.setValue(va.getX() * point.getX() + va.getY() * point.getY() + va.getZ() * point.getW(), i); 
        }
        return answPoint;
    }

    public Matrix3 times3(Matrix3 opMatrix) {
        opMatrix = opMatrix.transpose();
        Matrix3 answMatrix3 = new Matrix3();
        for (int i = 0; i < 3; i++) {
            Vector3 va = this.matrix[i];
            Vector3 vr = new Vector3();
            for (int j = 0; j < 3; j++) {
                Vector3 vb = opMatrix.matrix[j];
                vr.setValue(va.getX() * vb.getX() + va.getY() * vb.getY() + va.getZ() * vb.getZ(), j);
            }
            answMatrix3.setVector(vr, i);
        }

        return answMatrix3;

    }

    public void printMatrix() {
        System.out.println("------------------------------------------");
        for (int i = 0; i < 3; i++) {
            System.out.println(this.matrix[i].getX() + " " + this.matrix[i].getY() + " " + this.matrix[i].getZ());
        }
        System.out.println("------------------------------------------");
    }

    public Vector3 getVector1() {
        return this.matrix[0];
    }

    public Vector3 getVector2() {
        return this.matrix[1];
    }

    public Vector3 getVector3() {
        return this.matrix[2];
    }

    public void setVector(Vector3 v, int i) {
        this.matrix[i] = v;
    }
}
