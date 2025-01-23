public class Coup {

    private int eval;
    private int colonne;
    private int ligne;


    public Coup(int val, int ligne,int colonne) {
        this.eval = val;
        this.colonne = colonne;
        this.ligne = ligne;

    }

    public int getEval() {
        return eval;
    }

    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }
}
