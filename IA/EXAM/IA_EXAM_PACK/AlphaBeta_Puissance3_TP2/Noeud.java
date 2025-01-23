import java.util.Arrays;

public class Noeud {
    private int[][] matrice;
    private boolean max;
    private int h;


    public Noeud(boolean max, int[][] matrice) {
        this.matrice = matrice;
        this.max = max;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int[][] getMatrice() {
        return matrice;
    }

    public boolean isMax() {
        return max;
    }

    @Override
    public String toString() {
        String s= "";
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 5 ; j++) {
                s += matrice[i][j] + "|";
            }
            s += "\n";
        }
        return s;
    }

    public int troisPionsAlignesLigne(boolean typeJoueur) {
        int score = 0;
        int target = typeJoueur ? 1 : 2; // 1 for player 1, 2 for player 2
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrice[i][j] == target && matrice[i][j + 1] == target && matrice[i][j + 2] == target) {
                    score = 1000;
                    break;
                }
            }
        }
        return score;
    }

    public int troisPionsAlignesColonne(boolean typeJoueur) {
        int score = 0;
        int target = typeJoueur ? 1 : 2; // 1 for player 1, 2 for player 2
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 3; i++) {
                if (matrice[i][j] == target && matrice[i + 1][j] == target && matrice[i + 2][j] == target) {
                    score = 1000;
                    break;
                }
            }
        }
        return score;
    }


    public int troisPionsPossiblesLigne(boolean typeJoueur) {
        int score = 0;
        char target = typeJoueur ? '1' : '2';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                String str = "" + matrice[i][j] + matrice[i][j + 1] + matrice[i][j + 2];
                if (str.chars().filter(num -> num == target).count() == 2 && str.chars().filter(num -> num == '0').count() == 1) {
                    score += 200;
                } else if(str.chars().filter(num -> num == target).count() == 1 && str.chars().filter(num -> num == '0').count() == 2 ) {
                    score+=30;
                }
            }
        }
        return score;
    }

    public int troisPionsPossiblesColonne(boolean typeJoueur) {
        int score = 0;
        char target = typeJoueur ? '1' : '2';
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 3; i++) {
                String str = "" + matrice[i][j] + matrice[i+1][j] + matrice[i+2][j];
                if (str.chars().filter(num -> num == target).count() == 2 && str.chars().filter(num -> num == '0').count() == 1) {
                    score += 200;
                } else if(str.chars().filter(num -> num == target).count() == 1 && str.chars().filter(num -> num == '0').count() == 2 ) {
                    score+=30;
                }
            }
        }
        return score;
    }

    public int evaluer() {
        h = -1*troisPionsAlignesLigne(false)
                + troisPionsAlignesLigne(true)
                - 1*troisPionsAlignesColonne(false)
                + troisPionsAlignesColonne(true)
                - 1*troisPionsPossiblesLigne(false)
                + troisPionsPossiblesLigne(true)
                - 1*troisPionsPossiblesColonne(false)
                + troisPionsPossiblesColonne(true);
        return h;
    }

    public boolean estPleine(){
        boolean estPleine = true;
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                if(matrice[i][j] == 0) estPleine=false;
            }
        }
        return estPleine;
    }
}

