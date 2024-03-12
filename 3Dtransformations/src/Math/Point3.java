package Math;

public class Point3 {
    
    private Double x,y,w;

    public Point3(Double x, Double y, Double w) {
        this.x = x;
        this.y = y;
        this.w = 1.0;
    }

    public Point3(){
        this.x = 0.0;
        this.y = 0.0;
        this.w = 1.0;
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

    public void setValue(Double v, int i) {
        if(i == 0){
            this.x = v;
        }else if(i == 1){
            this.y = v;
        }else{
            this.w = v;
        }
    }
}
