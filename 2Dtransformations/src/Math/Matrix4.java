package Math;

public class Matrix4 {
    Vector4[] matrix;

    public Matrix4(Vector4 v1, Vector4 v2, Vector4 v3, Vector4 v4) {
        this.matrix = new Vector4[4];
        this.matrix[0] = v1;
        this.matrix[1] = v2;
        this.matrix[2] = v3;
        this.matrix[3] = v4;
    }

    public Matrix4() {
        this.matrix = new Vector4[4];
        this.matrix[0] = new Vector4(1.0, 0.0, 0.0, 0.0);
        this.matrix[1] = new Vector4(0.0, 1.0, 0.0, 0.0);
        this.matrix[2] = new Vector4(0.0, 0.0, 1.0, 0.0);
        this.matrix[3] = new Vector4(0.0, 0.0, 0.0, 1.0);
    }

    public Matrix4 transpose() {

        Double a12 = this.matrix[0].getY();
        Double a13 = this.matrix[0].getZ();
        Double a11 = this.matrix[0].getX();
        Double a14 = this.matrix[0].getW();

        Double a23 = this.matrix[1].getZ();
        Double a21 = this.matrix[1].getX();
        Double a22 = this.matrix[1].getY();
        Double a24 = this.matrix[1].getW();

        Double a31 = this.matrix[2].getX();
        Double a32 = this.matrix[2].getY();
        Double a33 = this.matrix[2].getZ();
        Double a34 = this.matrix[2].getW();

        Double a41 = this.matrix[3].getX();
        Double a42 = this.matrix[3].getY();
        Double a44 = this.matrix[3].getW();
        Double a43 = this.matrix[3].getZ();

        Vector4 v1 = new Vector4(a11, a21, a31, a41);
        Vector4 v2 = new Vector4(a12, a22, a32, a42);
        Vector4 v3 = new Vector4(a13, a23, a33, a43);
        Vector4 v4 = new Vector4(a14, a24, a34, a44);

        return new Matrix4(v1, v2, v3, v4);

    }

    public Matrix4 times4(Matrix4 opMatrix) {
        opMatrix = opMatrix.transpose();
        Matrix4 ansMatrix4 = new Matrix4();

        for (int i = 0; i < 4; i++) {
            Vector4 va = this.matrix[i];
            Vector4 vr = new Vector4();
            for (int j = 0; j < 4; j++) {
                Vector4 vb = opMatrix.matrix[j];
                vr.setValue(va.getX() * vb.getX() + va.getY() * vb.getY() + va.getZ() * vb.getZ() + va.getW() * vb.getW(), j);
            }
            ansMatrix4.setVector(vr, i);
        }

        return ansMatrix4;
    }

    public void printMatrix() {
        System.out.println("------------------------------------------");
        for (int i = 0; i < 4; i++) {
            System.out.println(this.matrix[i].getX() + " " + this.matrix[i].getY() + " " + this.matrix[i].getZ() + " "
                    + this.matrix[i].getW());
        }
        System.out.println("------------------------------------------");
    }

    public Vector4 getVector1() {
        return this.matrix[0];
    }

    public Vector4 getVector2() {
        return this.matrix[1];
    }

    public Vector4 getVector3() {
        return this.matrix[2];
    }

    public Vector4 getVector4() {
        return this.matrix[3];
    }

    public void setVector(Vector4 v, int i) {
        this.matrix[i] = v;
    }
}
