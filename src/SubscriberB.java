public class SubscriberB {
    String name;

    double Y;
    int P;
    private double X;
    double A;
    int K;
    double recievedY;
    String log;

    SubscriberB(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setP(int p){
        for (int i = 2; i <= p/2; i++){
            int temp = p % i;
            if (temp == 0){
                this.log = ("P - не простое число!");
                return;
            }
        }
        this.P = p;
        this.log = "P = " + this.P;
    }

    public void setA(double a){
        int p = this.P;
        if ((a > 1) && (a < p--)){
            this.A = a;
        } else {
            this.log = ("1 < A < P-1 не выполняется!");
            return;
        }
        this.log = "A = " + this.A;
    }

    public void setX(double x){
        this.X = x;
        this.log = "X " + this.getName() + " = " + this.X;
    }

    public double getY() {
        return Y;
    }

    public int getP() {
        return P;
    }

    public double getX() {
        return X;
    }

    public double getA() {
        return A;
    }

    public void computeY(double a, double x, int p){
        Y =  Math.pow(a, x) % p;
        logY();
    }

    public void computeKey(double y, double x, int p){
        K = (int) Math.pow(y, x) % p;
        logKey();
    }

    public int getK(){
        return K;
    }

    public void recieveY(double y){
        this.recievedY = y;
        this.log = this.getName() +" получил Y = " + this.recievedY;
    }

    public double getRecievedY() {
        return recievedY;
    }

    public void logY(){
        this.log = "Y " + this.getName() + " = "  + this.getA() + "^" + this.getX() + " mod " + this.getP() + " = " + this.getY();
    }

    public void logKey(){
        this.log = "K " + this.getName() + " = "  + this.getRecievedY() + "^" + this.getX() + " mod " + this.getP() + " = " + this.getK();
    }

    public String getLog() {
        return log;
    }
}