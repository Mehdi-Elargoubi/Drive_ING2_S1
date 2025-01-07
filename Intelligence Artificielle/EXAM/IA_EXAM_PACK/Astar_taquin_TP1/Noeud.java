import java.util.ArrayList;
import java.util.Arrays;

public class Noeud {

    private int[][] grille ;

    private Noeud pere;

    private int g;

    private int taille;

    public Noeud(int[][] grille, Noeud pere,int taille , int g) {
        this.grille = grille;
        this.pere = pere;
        this.g = g;
        this.taille=taille;


    }

    public int[][] getGrille() {
        return grille;
    }

    public Noeud getPere() {
        return pere;
    }

    public int getG() {
        return g;
    }

    @Override
    public String toString() {
        String text=" ";
        for (int i=0;i<taille;i++){
            text += " --- --- ---\n|";
            for (int j=0;j<taille;j++){
                text+=((grille[i][j])!=0) ?" "+grille[i][j]+"  |" : "  |";

            }
            text += "\n";
        }
        return text;
    }

    public boolean est_identique(Noeud autre){
        for (int i=0;i<taille;i++){
            for (int j=0;i<taille;j++){
                if (grille[i][j]!=autre.grille[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    public int calculerH1() {
        int h1 = 0;
        int valeurAttendue = 1;

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (grille[i][j] != valeurAttendue) {
                    h1++;
                }
                valeurAttendue++;
            }
        }

       return h1-1;
    }

    public int calculerH2(){
        int h2=0;
        int lig2=0;
        int col2=0;

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (grille[i][j]!=0){
                 lig2=(grille[i][j]-1)/taille;
                 col2=(grille[i][j]-1)%taille;
            if ((i!=lig2) || (j!=col2)){
                h2+=Math.abs(i-lig2)+Math.abs(j-col2);
            }
                }
    }
        }
        return h2;

        }


        public int calculF1(){
        return g+calculerH1();
        }
        public int calculF2(){
        return g+calculerH2();
        }
        public boolean estunetatfinal(){
            int valeurAttendue = 1;

            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (grille[i][j] != valeurAttendue) {
                        return false;

                    }
                    valeurAttendue++;

                }
            }
            return true;
        }

        public ArrayList<Noeud> successeur(){
        ArrayList<Noeud> successeur = new ArrayList<>();
        int lig = 0;
                int col =0;
            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < taille; j++) {
                    if (grille[i][j] == 0) {
                        lig=i;
                        col=j;
                        break;
                    }
                }
            }
            if (lig>0){
                successeur.add(creerSuccesseur(lig,col,lig-1,col));
            }
            if (lig<grille.length-1){
                successeur.add(creerSuccesseur(lig,col,lig+1,col));
            }
            if(col>0){
                successeur.add(creerSuccesseur(lig,col,lig,col-1));
            }
            if (col<grille.length-1){
                successeur.add(creerSuccesseur(lig,col,lig,col+1));
            }
            return successeur;
            }

            private Noeud creerSuccesseur (int lig_act,int col_act,int newlig,int newcol){
    int[][] newgrille= copierGrille();
    int tmp= newgrille[lig_act][col_act];
    newgrille[lig_act][col_act]=newgrille[newlig][newcol];
    newgrille[newlig][newcol]=tmp;

    return new Noeud(newgrille,this,taille,g+1);
    }

    private int [][]copierGrille(){
        int [][]newgrille= new int [grille.length][grille[0].length];
        for (int i=0;i<grille.length;i++){
            System.arraycopy(grille[i],0,newgrille[i],0,grille.length);
        }
        return newgrille;
    }
}