package Math;

public class Point4 {
    private Double x,y,z,w;

    public Point4(Double x, Double y, Double z,Double w) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.z = z;
    }

    public Point4(){
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        this.w = 0.0;
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

    public void setValue(Double v, int i) {
        if(i == 0){
            this.x = v;
        }else if(i == 1){
            this.y = v;
        }else if(i == 2){
            this.z = v;
        }else{
            this.w = v;
        }
    }
}
