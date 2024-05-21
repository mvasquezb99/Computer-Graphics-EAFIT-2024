package display;

public class Material {
    public double kA;
    public double kD;
    public double kS;
    public double kR;
    public double kO;
    public int n;

    public Material(double kA, double kD, double kS,double kR, double kO,int n) {
        this.kA = kA;
        this.kD = kD;
        this.kS = kS;
        this.kR = kR;
        this.kO = kO;
        this.n = n;

    }
}
