import java.lang.Math;
import java.util.ArrayList;

public class Noeud {
    private int[][] grille;
    private Noeud pere;
    private int g;
    private int taille;
    Noeud(Noeud pere, int taille, int g, int[][] oldGrille ){
        this.pere = pere;
        this.taille = taille;
        this.g = g;
        grille = new int[taille][taille];
        grille = copyGrille(oldGrille);
    }
    Noeud(Noeud pere, int taille, int g){
        this.pere = pere;
        this.taille = taille;
        this.g = g;
        grille = new int[taille][taille];
    }
    public int[][] copyGrille(int[][] oldGrille){
        int[][] newGrille = new int[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                newGrille[i][j] = oldGrille[i][j];
            }
        }
        return newGrille;
    }
    public int getElementOfGrille(int i, int j){
        return grille[i][j];
    }
    public Noeud getPere(){
        return pere;
    }
    public int getG(){
        return g;
    }
    @Override
    public String toString(){
        String text = new String();
        for (int i = 0; i < taille; i++) {
            text += " --- --- ---\n|";
            for (int j = 0; j < taille; j++) {
                text += (grille[i][j] != 0) ? " " + grille[i][j] + " |" : "   |";
            }
            text += "\n";
        }
        text += " --- --- ---";
        return text;
    }
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Noeud otherNoeud = (Noeud)obj;
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (grille[i][j] != otherNoeud.getElementOfGrille(i,j)) {
                    return false;
                }
            }
        }
        return true;
    }
    public int h1(){
        int tmpDiv = 0;
        int tmpMod = 0;
        int somme = 0;
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (grille[i][j] != 0) {
                    tmpDiv = (grille[i][j] - 1) / taille;
                    tmpMod = (grille[i][j] - 1) % taille;
                    if ((i != tmpDiv) || (j != tmpMod)) {
                        somme++;
                    }
                }
            }
        }
        return somme;
    }
    public int h2(){
        int tmpDiv = 0;
        int tmpMod = 0;
        int somme = 0;
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (grille[i][j] != 0) {
                    tmpDiv = (grille[i][j] - 1) / taille;
                    tmpMod = (grille[i][j] - 1) % taille;
                    if ((i != tmpDiv) || (j != tmpMod)) {
                        somme += Math.abs(i - tmpDiv) + Math.abs(j - tmpMod);
                    }
                }
            }
        }
        return somme;
    }
    public int f1(){
        return g + h1();
    }
    public int f2(){
        return g + h2();
    }
    public boolean estUnEtatFinal(){
        // int tmpDiv = 0;
        // int tmpMod = 0;
        // if (grille[taille-1][taille-1] != 0) {
        //     return false;
        // }
        // for (int i = 0; i < taille; i++) {
        //     for (int j = 0; j < taille; j++) {
        //         if ((i != taille - 1) && (j != taille - 1)) {
        //             tmpDiv = (grille[i][j] - 1) / taille;
        //             tmpMod = (grille[i][j] - 1) % taille;
        //             if ((i != tmpDiv) || (j != tmpMod)) {
        //                 return false;
        //             }
        //         }
        //     }
        // }
        // return true;
        return (this.h1() == 0);
    }
    public ArrayList<Noeud> successeurs(){
        ArrayList<Noeud> successeurs = new ArrayList<Noeud>();
        int tmpI = 0, tmpJ = 0;
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (grille[i][j] == 0) {
                    tmpI = i;
                    tmpJ = j;
                    break;
                }
            }
        }
        int[][] tmpGrille;
        Noeud tmpNoeud;
        // deplacer vers le haut
        if (tmpI != 0) {
            tmpGrille = copyGrille(grille);
            tmpGrille[tmpI][tmpJ] = tmpGrille[tmpI - 1][tmpJ];
            tmpGrille[tmpI - 1][tmpJ] = 0;
            tmpNoeud = new Noeud(this, taille, g+1, tmpGrille);
            if (this.getPere() == null || !tmpNoeud.equals(this.getPere())) {
                successeurs.add(tmpNoeud);
            }   
            tmpGrille = null;
        }
        // deplacer vers le bas
        if (tmpI != (taille - 1)) {
            tmpGrille = copyGrille(grille);
            tmpGrille[tmpI][tmpJ] = tmpGrille[tmpI + 1][tmpJ];
            tmpGrille[tmpI + 1][tmpJ] = 0;
            tmpNoeud = new Noeud(this, taille, g+1, tmpGrille);
            if (this.getPere() == null || !tmpNoeud.equals(this.getPere())) {
                successeurs.add(tmpNoeud);
            }   
            tmpGrille = null;
        }
        // deplacer vers la gauche
        if (tmpJ != 0) {
            tmpGrille = copyGrille(grille);
            tmpGrille[tmpI][tmpJ] = tmpGrille[tmpI][tmpJ - 1];
            tmpGrille[tmpI][tmpJ - 1] = 0;
            tmpNoeud = new Noeud(this, taille, g+1, tmpGrille);
            if (this.getPere() == null || !tmpNoeud.equals(this.getPere())) {
                successeurs.add(tmpNoeud);
            }   
            tmpGrille = null;
        }
        // deplacer vers la droite
        if (tmpJ != (taille - 1)) {
            tmpGrille = copyGrille(grille);
            tmpGrille[tmpI][tmpJ] = tmpGrille[tmpI][tmpJ + 1];
            tmpGrille[tmpI][tmpJ + 1] = 0;
            tmpNoeud = new Noeud(this, taille, g+1, tmpGrille);
            if (this.getPere() == null || !tmpNoeud.equals(this.getPere())) {
                successeurs.add(tmpNoeud);
            }            
            tmpGrille = null;
        }
        return successeurs;
    }

}
