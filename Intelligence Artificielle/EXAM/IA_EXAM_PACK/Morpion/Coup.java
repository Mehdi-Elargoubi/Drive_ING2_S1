public class Coup {
    private int x;
    private int y;
    private int eval;


    public Coup( int eval,int x, int y) {
        this.x = x;
        this.y = y;
        this.eval = eval;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getEval() {
        return eval;
    }

    @Override
    public String toString() {
        return "Coup{" +
                "x=" + x +
                ", y=" + y +
                ", eval=" + eval +
                '}';
    }
}
