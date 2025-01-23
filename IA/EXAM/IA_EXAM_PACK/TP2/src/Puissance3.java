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
        if(i >= HEIGHT) return false;
        matrice[i][colonne] = target;
        return true;
        }

    public boolean estFinJeu(boolean typeJoueur,int[][] matrice){
        Noeud n = new Noeud(typeJoueur,matrice);
        int score = 0;
        score += n.troisPionsAlignesLigne(typeJoueur) ;
        score += n.troisPionsAlignesColonne(typeJoueur) ;
        if(score >= 1000) return true;
        else {
            if (n.estPleine() == true) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public String toString() {
        String s= "";
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 5 ; j++) {
                s += getMatriceJeu()[i][j] + "|";
            }
            s += "\n";
        }
        return s;
    }

    public int[][] copieMatrice(Noeud n) {
        int[][] originalMatrice = n.getMatrice();
        int[][] newMatrice = new int[originalMatrice.length][originalMatrice[0].length];

        for (int i = 0; i < originalMatrice.length; i++) {
            for (int j = 0; j < originalMatrice[i].length; j++) {
                newMatrice[i][j] = originalMatrice[i][j];
            }
        }

        return newMatrice;
    }



    public Coup alpha_beta(Noeud n,int alpha,int beta,int profondeur){
        Coup meilleurCoup = null;
        int bestJ = -1;
        if((profondeur == 0) || estFinJeu(!n.isMax(),n.getMatrice())){
            n.evaluer();
            meilleurCoup = new Coup(n.getH(),-1);
            return meilleurCoup;
        }
        if(n.isMax()) {
            for (int j = 0; j < n.getMatrice().length; j++) {
                int[][] copieMatrice = copieMatrice(n);
                jouer(n.isMax(),j,copieMatrice);
                Noeud succ = new Noeud(!n.isMax(),copieMatrice);

                Coup cp = alpha_beta(succ,alpha,beta,profondeur-1);

                if(cp.getEval() > alpha) {
                    alpha = cp.getEval();
                    bestJ = j;
                }
                if( alpha >= beta) {
                    return new Coup(alpha,bestJ);
                }
            }
            return new Coup(alpha,bestJ);
        } else {
            for (int j = 0; j < n.getMatrice().length; j++) {
                int[][] copieMatrice = copieMatrice(n);
                jouer(n.isMax(),j,copieMatrice);
                Noeud succ = new Noeud(!n.isMax(),copieMatrice);
                Coup cp = alpha_beta(succ,alpha,beta,profondeur-1);

                if(cp.getEval() < beta) {
                    beta = cp.getEval();
                    bestJ = j;
                }
                if( beta <= alpha) {
                    return new Coup(beta,bestJ);
                }
            }
            return new Coup(beta,bestJ);
        }
    }
}



