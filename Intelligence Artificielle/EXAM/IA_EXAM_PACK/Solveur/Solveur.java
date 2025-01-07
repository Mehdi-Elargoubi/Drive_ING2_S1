
public class Solveur {

    private String matriceJeu[][];

    public Solveur() {
        this.matriceJeu = new String[3][3];
    }

    public String[][] getMatriceJeu() {
        return matriceJeu;
    }

    public boolean jouer(boolean typeJoeur, int ligne,int colonne, String [][] matrice) {
        if(!matrice[ligne][colonne].equals("-"))return false;
        matrice[ligne][colonne] = typeJoeur ? "O" : "X";
        return true;
    }

    public boolean estFinJeu(boolean typeJoueur, String [][] matrice) {
        Noeud noeud = new Noeud(typeJoueur, matrice);
        boolean finPartie = (noeud.troisSymboleAlignesLigne(typeJoueur) != 0) || (noeud.troisSymboleAlignesColonne(typeJoueur) != 0) || (noeud.troisSymboleAlignesDiagonale(typeJoueur) != 0);
        return finPartie;
    }

    public void copieMatrice(String [][] mSource, String [][] mDestination) {
        for (int lig = 0; lig < mSource.length; lig++) {
            for (int col = 0; col < mSource.length; col++) {
                mDestination[lig][col] = mSource[lig][col];
            }
        }
    }

    public Coup alph_beta(Noeud noeud, int alpha, int beta, int profondeur) {

        Noeud racine = noeud;

        String[][] copieM;
        int alphaCourant, betaCourant, bestColonne = 0, bestLigne = 0;
        Coup coup;

        if (profondeur == 1 || estFinJeu(racine.isMax(), racine.getMatrice())) {
            racine.evaluer();
            return new Coup(racine.getH(), -1, -1);
        }
        if (noeud.isMax()) {
            alphaCourant = alpha;
            for (int ligne = 0; ligne < 3; ligne++) {
                for (int colonne = 0; colonne < 3; colonne++) {
                    copieM = new String[3][3];
                    copieMatrice(racine.getMatrice(), copieM);
                    if (jouer(racine.isMax(), ligne, colonne, copieM)) {
                        Noeud sucesseur = new Noeud(!racine.isMax(), copieM);
                        coup = alph_beta(sucesseur, alphaCourant, beta, profondeur - 1);
                        sucesseur.setH(coup.getEval());
                        if (coup.getEval() > alphaCourant) {
                            alphaCourant = coup.getEval();
                            bestLigne = ligne;
                            bestColonne = colonne;
                        }
                        if (alphaCourant >= beta)
                            return new Coup(alphaCourant, bestLigne, bestColonne);

                    }

                }
            }
            return new Coup(alphaCourant, bestLigne, bestColonne);
        } else {
            betaCourant = beta;
            for (int ligne = 0; ligne < 3; ligne++) {
                for (int colonne = 0; colonne < 3; colonne++) {
                    copieM = new String[3][3];
                    copieMatrice(racine.getMatrice(), copieM);
                    if (jouer(racine.isMax(), ligne, colonne, copieM)) {
                        Noeud sucesseur = new Noeud(!racine.isMax(), copieM);
                        coup = alph_beta(sucesseur, alpha, betaCourant, profondeur - 1);
                        sucesseur.setH(coup.getEval());
                        if (coup.getEval() < betaCourant) {
                            betaCourant = coup.getEval();
                            bestColonne = colonne;
                            bestLigne = ligne;
                        }
                        if (betaCourant <= alpha) {
                            return new Coup(betaCourant, bestLigne, bestColonne);
                        }
                    }
                }
            }
            return new Coup(betaCourant, bestLigne, bestColonne);
        }
    }

}
