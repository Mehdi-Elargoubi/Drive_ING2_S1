public class Puissance3 {
    private int[][] matriceJeu;
    public final int WIDTH=5;
    public final int HEIGHT=5;

    public Puissance3(){
        matriceJeu = new int[WIDTH][HEIGHT];
    }

    public int[][] getMatriceJeu() {
        return matriceJeu;
    }

    public boolean jouer(boolean typeJoueur,int colonne,int[][] matrice){
        int target = typeJoueur ? 1 : 2;
        int i = 0;
        while( i<HEIGHT && matrice[i][colonne] != 0){
            i++;
        }
        if() return false;
            matrice[i][colonne] = target;
            return true;
        }

    public boolean estFinJeu(boolean typeJoueur,int[][] matrice){
        Noeud n = new Noeud(typeJoueur,matrice);
        int score = 0;
        score += n.troisPionsAlignesLigne(typeJoueur) ;
        score += n.troisPionsAlignesColonne(typeJoueur) ;
        if(score >= 1000) return true;
        else{
            return n.estPleine();
        }
    }


    public Coup alpha_beta(Noeud n,int alpha,int beta,int profondeur){
        if((profondeur ==0) || estFinJeu(!n.isMax(),n.getMatrice())){
            n.evaluer();
            return new Coup(n.getH(),-1);
        }
        if(n.isMax()) {
            for (int: j
                 ) {

            }
        }
        }




}
