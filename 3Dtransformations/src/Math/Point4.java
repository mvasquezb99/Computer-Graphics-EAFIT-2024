package Math;

public class Point4 {
    private Double x,y,z,w;

    public Point4(Double x, Double y, Double z,Double w) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.z = z;
    }

    public Double getX() {
        return x;
    }
    public Double getY() {
        return y;
    }
    public Double getW() {
        return w;
    }
    public Double getZ(){
        return z;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y){
        this.y = y;
    }

    public void setZ(Double z){
        this.z = z;
    }
}
