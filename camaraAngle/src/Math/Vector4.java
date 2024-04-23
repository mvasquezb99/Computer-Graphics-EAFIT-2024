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

    public Vector4 substract(Vector4 x){
        return new Vector4(this.getX() - x.getX(), this.getY() - x.getY(), this.getZ() - x.getZ(), this.getW() - x.getW());
    }

    public Double Magnitude(){
        Double answ; 
        answ = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2) + Math.pow(this.w, 2));
        return answ;
    }

    public void Normalize(){
        Double mag;

        mag = this.Magnitude();

        this.setX(this.x / mag);
        this.setY(this.y / mag);
        this.setZ(this.z / mag);
        this.setW(this.w / mag);
    }

    public Vector4 crossedProduct4(Vector4 v1){
        Double i = this.getY() * v1.getZ() - this.getZ()*v1.getY();
        Double j = this.getX() * v1.getZ() - this.getZ()* v1.getX();
        Double k = this.getX() * v1.getY() - this.getY() * v1.getX();

        return new Vector4(i, j, k, this.getW());

    }

    public Double dotProduct4(Vector4 v1){
        return this.getX() * v1.getX() + this.getY() * v1.getY() + this.getZ() * v1.getZ() + this.getW() * v1.getW();
    }

    public Vector4 minus(){
        return new Vector4(this.getX()*-1, this.getY()*-1,this.getZ()*-1, this.getW()*-1);
    }


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

    public void setZ(Double v){
        this.z = v;
    }
    public void setX(Double v){
        this.x = v;
    }
    public void setY(Double v){
        this.y = v;
    }

    public void setW(Double v){
        this.w = v;
    }
}
