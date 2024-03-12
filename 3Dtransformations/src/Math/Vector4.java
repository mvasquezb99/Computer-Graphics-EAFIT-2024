package Math;

public class Vector4 {
    public Double x,y,z,w;

    public Vector4(Double x, Double y, Double z, Double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4(){
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        this.w = 0.0;
    }

    // public void setW(Double w) {
    //     this.w = w;
    // }
    // public void setX(Double x) {
    //     this.x = x;
    // }
    // public void setY(Double y) {
    //     this.y = y;
    // }
    // public void setZ(Double z) {
    //     this.z = z;
    // }

    public void setValue(Double v, int i){
        if(i == 0){
            this.x = v;
        }else if(i == 1){
            this.y = v;
        }else if(i == 2){
            this.z = v;
        }else if(i == 3){
            this.w = v;
        }
    }
    public Double getW() {
        return w;
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
}
