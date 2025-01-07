public class Noeud {
    private  int[][] matrice;
    private  boolean max;
    private  int h;

    private  final int  width = 3;
    private  final int  height = 3;

    public Noeud(int[][] matrice, boolean max) {
        this.matrice = matrice;
        this.max = max;
        this.h=0;
    }
    public int[][] getMatrice() {
        return matrice;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public boolean isMax() {
        return max;
    }
public void evaluer(){
      h=troisPionsAlignes(true)-troisPionsAlignes(false)+deuxPionsAlignes(true)-deuxPionsAlignes(false)+unPionsAligne(true)-unPionsAligne(false);
    }
    public  int troisPionsAlignes(boolean typeJoueur) {
        return troisAlignesDiag(typeJoueur)+troisAlignesLC(typeJoueur,true)+troisAlignesLC(typeJoueur,false);


    }
    public  int deuxPionsAlignes(boolean typeJoueur) {
        
        return deuxAlignesDiag(typeJoueur)+deuxAlignesLC(typeJoueur,true)+deuxAlignesLC(typeJoueur,false);

    }
    public int unPionsAligne(boolean typeJoueur)
    {
        
        return unAligneDiag(typeJoueur)+unAligneLC(typeJoueur,true)+unAligneLC(typeJoueur,false);
    }
    public int troisAlignesDiag(boolean typeJoueur) {
        String diag1=""+matrice[0][0]+matrice[1][1]+matrice[2][2];
        String diag2=""+matrice[2][0]+matrice[1][1]+matrice[0][2];
        int typeJ=typeJoueur? 1 : 2;
        int  sc=0;

        if(diag1.contains(""+typeJ+typeJ+typeJ))
        {
            sc+=30;
        }
       if(diag2.contains(""+typeJ+typeJ+typeJ))
       {
           sc+=30;
       }
        return sc;
    }

    public int deuxAlignesDiag(boolean typeJoueur)
    {
        String diag1=""+matrice[0][0]+matrice[1][1]+matrice[2][2];
        String diag2=""+matrice[2][0]+matrice[1][1]+matrice[0][2];
        int typeJ=typeJoueur? 1 : 2;
        String seq1=""+typeJ+"0"+typeJ;
        String seq2="0"+typeJ+typeJ;
        String seq3=""+typeJ+typeJ+"0";
        int sc=0;
  
        if(diag1.contains(seq1) || diag1.contains(seq2) || diag1.contains(seq3))
            sc+=9;
        if(diag2.contains(seq1) || diag2.contains(seq2) || diag2.contains(seq3))
            sc+=9;
        return sc;

    }

    public int unAligneDiag(boolean typeJoueur)
    {
        String diag1=""+matrice[0][0]+matrice[1][1]+matrice[2][2];
        String diag2=""+matrice[2][0]+matrice[1][1]+matrice[0][2];
        int typeJ=typeJoueur? 1 : 2;
        String seq1=""+typeJ+"0"+"0";
        String seq2="0"+"0"+typeJ;
        String seq3="0"+typeJ+"0";
        int sc=0;
        if(diag1.contains(seq1) || diag1.contains(seq2) || diag1.contains(seq3))
            sc+=6;
        if(diag2.contains(seq1) || diag2.contains(seq2) || diag2.contains(seq3))
            sc+=6;
        return sc;
    }
    public int troisAlignesLC(boolean typeJoueur,boolean ligne)
    {
        int typeJ=typeJoueur? 1 : 2;
        String seq=""+typeJ+typeJ+typeJ;

        String l,c;
        int sc=0;
        if(ligne)
        {
            for (int i = 0; i < width; i++)
            {
                l="";
                for (int j = 0; j < height; j++)
                {
                    l+=matrice[i][j];
                }
                if(l.contains(seq))
                {
                    sc+=30;
                }
            }
        }
        else
        {
            for (int i = 0; i < height; i++)
            {
                c="";
                for (int j = 0; j < width; j++)
                {
                    c+=matrice[j][i];
                }
                if(c.contains(seq))
                {
                    sc+=30;
                }
            }

        }
        return sc;
    }
    public int deuxAlignesLC(boolean typeJoueur,boolean ligne)
    {
        int typeJ=typeJoueur? 1 : 2;
        String seq1=""+typeJ+"0"+typeJ;
        String seq2="0"+typeJ+typeJ;
        String seq3=""+typeJ+typeJ+"0";
        String l,c;
        int sc=0;
        if(ligne)
        {
            for (int i = 0; i < width; i++)
            {
                l="";
                for (int j = 0; j < height; j++)
                {
                    l+=matrice[i][j];
                }
                if(l.contains(seq1) || l.contains(seq2) || l.contains(seq3))
                {
                    sc+=9;
                }
            }
        }
        else
        {
            for (int i = 0; i < height; i++)
            {
                c="";
                for (int j = 0; j < width; j++)
                {
                    c+=matrice[j][i];
                }
                if(c.contains(seq1) || c.contains(seq2) || c.contains(seq3))
                {
                    sc+=9;
                }
            }
        }

        return sc;

    }
    public int unAligneLC(boolean typeJoueur,boolean ligne)
    {
        int typeJ=typeJoueur? 1 : 2;
        String seq1=""+typeJ+"0"+"0";
        String seq2="0"+"0"+typeJ;
        String seq3="0"+typeJ+"0";
        String l,c;
        int sc=0;
        if(ligne)
        {
            for (int i = 0; i < width; i++)
            {
                l="";
                for (int j = 0; j < height; j++)
                {
                    l+=matrice[i][j];
                }
                if(l.contains(seq1) || l.contains(seq2) || l.contains(seq3))
                {
                    sc+=6;
                }
            }
        }
        else
        {
            for (int i = 0; i < height; i++)
            {
                c="";
                for (int j = 0; j < width; j++)
                {
                    c+=matrice[j][i];
                }
                if(c.contains(seq1) || c.contains(seq2) || c.contains(seq3))
                {
                   sc+=6;
                }
            }
        }

        return sc;

    }
  public boolean gagne(boolean typeJoueur){
        if(troisPionsAlignes(typeJoueur)>=30){
            return true;
        }
      boolean fin=true;

    for (int i = 0; i < width; i++) {   
      for (int j = 0; j < height; j++) {
              if(matrice[i][j]==0)
                  fin=false;
          }
    }

        return  fin;
    }
     public boolean estFinJeu(){
        if(troisPionsAlignes(max)>=30){
            return true;
        }

        return  false;
    }
    public void afficher() {
        for (int i = 0; i < matrice.length; i++) {
          for (int j = 0; j < matrice[0].length; j++) {
            System.out.print(matrice[i][j] + "\t");
          }
      System.out.println();
    
    }

}
}

