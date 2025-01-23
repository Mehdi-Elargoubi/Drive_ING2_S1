public class Noeud {

    private String [][] matrice;
    private boolean max;
    private int h;

    public Noeud(boolean max, String [][] matrice) {
        this.max = max;
        this.matrice = matrice;
    }

    public String[][] getMatrice() {
        return this.matrice;
    }

    public boolean isMax() {
        return this.max;
    }

    public int getH() {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int troisSymboleAlignesLigne(boolean typeJoueur ) {
        String joueur = typeJoueur ? "O" : "X";
        for (String[] ligne : this.matrice) {
            if(ligne[0].equals(joueur) && ligne[1].equals(joueur) && ligne[2].equals(joueur))
                    return 30;
        }
        return 0;
    }

    public int troisSymboleAlignesColonne(boolean typeJoueur ) {

        String joueur = typeJoueur ? "O" : "X";

        for (int col = 0; col < this.matrice.length; col++) {
            if(matrice[0][col].equals(joueur) && matrice[1][col].equals(joueur) && matrice[2][col].equals(joueur))
                return 30;
            }
        return 0;
    }

    public int troisSymboleAlignesDiagonale(boolean typeJoueur ) {

        String joueur = typeJoueur ? "O" : "X";

        if(matrice[0][0].equals(joueur) && matrice[1][1].equals(joueur) && matrice[2][2].equals(joueur))
            return 30;
        if(matrice[2][0].equals(joueur) && matrice[1][1].equals(joueur) && matrice[0][2].equals(joueur))
            return 30;
        return 0;
    }

    public int troisSymbolePossibleLigne(boolean typeJoueur ) {

        int score = 0;
        StringBuilder sb;
        String joueur = typeJoueur ? "O" : "X"; // pour définir le jouer Max = 1, Min = 2

        for (String[] ligne : this.matrice) {
            sb = new StringBuilder("");
            for (int col = 0; col < this.matrice.length; col++) {
                sb.append(ligne[col]);
            }
            score = getScore(score, sb, joueur);
        }

        return score;
    }

    public int troisSymbolePossibleColonne(boolean typeJoueur ) {

        int score = 0;
        StringBuilder sb;
        String joueur = typeJoueur ? "O" : "X"; // pour définir le jouer Max = 1, Min = 2

        for (int col = 0; col < this.matrice.length; col++) {
            sb = new StringBuilder("");
            for (int lig = 0; lig < this.matrice.length; lig++) {
                sb.append(matrice[lig][col]);
            }
            score = getScore(score, sb, joueur);
        }

        return score;
    }

    public int troisSymbolePossibleDiagonale(boolean typeJoueur ) {

        int score = 0;
        StringBuilder sb;
        String joueur = typeJoueur ? "O" : "X"; // pour définir le jouer Max = 1, Min = 2

        for (int lig = 0; lig < this.matrice.length - 2; lig++) {
            sb = new StringBuilder();
            for (int col = 0; col < this.matrice.length - 2; col++) {
                sb.append(matrice[lig][col]).append(matrice[lig+1][col+1]).append(matrice[lig+2][col+2]);
            }
            score = getScore(score, sb, joueur);
        }
        for (int lig = this.matrice.length-1; lig > 1; lig--) {
            sb = new StringBuilder();
            for (int col = 0; col <this.matrice.length-2; col++) {
                sb.append(matrice[lig][col]).append(matrice[lig-1][col+1]).append(matrice[lig-2][col+2]);
            }
            score = getScore(score, sb, joueur);
        }

        return score;
    }


    private int getScore(int score, StringBuilder sb, String joueur) {
        String result;
        result = sb.toString();
        score = (result.contains(joueur + joueur + "-") || result.contains(joueur + "-" + joueur) || result.contains("-" + joueur + joueur)) ? (score + 9) : score;
        score = (result.contains(joueur + "-"+"-") || result.contains("-"+joueur + "-") || result.contains("-" +"-"+ joueur)) ? (score + 6) : score;
        return score;
    }

    public void evaluer() {
        this.h = -troisSymboleAlignesLigne(false)
                + troisSymboleAlignesLigne(true)
                -troisSymboleAlignesColonne(false)
                + troisSymboleAlignesColonne(true)
                -troisSymboleAlignesDiagonale(false)
                + troisSymboleAlignesDiagonale(true)
                -troisSymbolePossibleLigne(false)
                + troisSymbolePossibleLigne(true)
                -troisSymbolePossibleColonne(false)
                + troisSymbolePossibleColonne(true)
                -troisSymbolePossibleDiagonale(false)
                + troisSymbolePossibleDiagonale(true);
    }

    public String toString() {
        StringBuilder affichage = new StringBuilder("\n --------------------------------- \n");
        for (int lig = 0; lig < this.matrice.length; lig++) {
            for (int col = 0; col < this.matrice.length; col++) {
                affichage.append(" | ").append(this.matrice[lig][col]).append(" | ");
            }
            affichage.append("\n --------------------------------- \n");
        }
        return affichage.toString();
    }
}
