public class Morpion implements Cloneable  {
    private  int[][] matriceJeu;
    private final  int WIDTH = 3;
    private final  int HEIGHT = 3;

    public Morpion() {
        this.matriceJeu = new int[WIDTH][HEIGHT];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                matriceJeu[i][j]=0;
            }
        }
    }
  public Morpion(int[][] matrice)
  {
    this.matriceJeu = new int[WIDTH][HEIGHT];
    for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                matriceJeu[i][j]=matrice[i][j];
            }
        }
  }
    public int[][] getMatriceJeu() {
        return matriceJeu;
    }
    public boolean jouer(boolean typeJoueur,int x,int y){
        return jouer(typeJoueur,x,y,matriceJeu);
    }
    public boolean jouer(boolean typeJoueur,int x,int y, int[][] matrice){
        if(matriceJeu[x][y]==0)
        {
            matriceJeu[x][y]=(typeJoueur)?1:2;
            return true;
        }
        return false;

    }
    public boolean estFinJeu(boolean typeJoueur,int[][] matrice){
        Noeud n = new Noeud(matrice,typeJoueur);
        return  n.estFinJeu();
    }
    public boolean finJeu(){
        return estFinJeu(true,matriceJeu) || estFinJeu(false,matriceJeu)  ;
    }
    public String toString() {
        StringBuilder aff= new StringBuilder("\n");

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                aff.append("\t").append(matriceJeu[i][j]);
            }
            aff.append("\n");
        }
        return aff.toString();
    }
    public String toString(int [][] m) {
        String aff="\n";

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                aff = aff+"\t"+m[i][j];
            }
            aff = aff+"\n";
        }
        return aff;
    }
    public void copieMatrice(int[][] mSource,int[][] mDest){
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                mDest[i][j] = mSource[i][j];
            }
        }
    }
    public Coup alpha_beta(Noeud n,int alpha,int beta,int profondeur){



        if(profondeur==0 || estFinJeu(! n.isMax(),n.getMatrice())){
            n.evaluer();
            return  new Coup(n.getH(),-1,-1);
        }
        else if (n.isMax()==true) {
            int bestI=0;
            int bestJ=0;
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    int[][] cp = new int[3][3];
                    copieMatrice(n.getMatrice(), cp);


                    if (jouer(true, i,j, cp)) {

                        Noeud successeur = new Noeud(cp, false);
                        Coup coup = alpha_beta(successeur, alpha, beta, profondeur - 1);


                        if (coup.getEval() > alpha) {
                            alpha = coup.getEval();
                            bestI = i;
                            bestJ=j;
                        }
                        if (alpha > beta) {
                            // System.out.println("test 2 :" +(alpha> beta));
                            return new Coup(alpha,bestI,bestJ);
                        }
                    }

                }
            }

            return  new Coup(alpha,bestI,bestJ);


            }

        else {
            int bestI = 0;
            int bestJ = 0;
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    int[][] cp = new int[5][5];
                    copieMatrice(n.getMatrice(), cp);
                    if (jouer(false, i, j, cp)) {
                        Noeud successeur = new Noeud(cp, true);
                        Coup coup = alpha_beta(successeur, alpha, beta, profondeur - 1);
                        if (coup.getEval() < beta) {
                            beta = coup.getEval();
                            bestI = i;
                            bestJ = j;
                        }
                        if (beta < alpha) {
                            return new Coup(beta, bestI, bestJ);
                        }
                    }
                }
            }

            return new Coup(beta, bestI, bestJ);
        }
    }
  public Object clone() throws CloneNotSupportedException 
   {
      return (Morpion)super.clone();
   }
}
