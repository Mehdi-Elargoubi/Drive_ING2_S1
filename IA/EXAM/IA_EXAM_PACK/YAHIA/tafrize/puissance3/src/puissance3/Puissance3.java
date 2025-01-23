package puissance3;

public class Puissance3 {
	
	private int [][] matriceJeu;
	int WIDTH=5;
	int HEIGHT=5;
	
	public Puissance3() {
		this.HEIGHT=5;
		this.WIDTH=5;
		this.matriceJeu= new int [WIDTH][HEIGHT];
		
	}
	
	public boolean jouer(boolean typeJoueur, int colonne, int[][] matrice){
        int i;
        int taille = matrice.length;
        if( colonne >= 0 && colonne < this.HEIGHT){
            for(i=taille-1; i>=0; i--){
                if( matrice[i][colonne] == 0){
                    if(typeJoueur)
                        matrice[i][colonne] = 1;
                    else
                        matrice[i][colonne] = 2;
                    return true;
                }
            }
        }
        return false;
    }
	
	
	public boolean estFinJeu(boolean typeJoueur,int [][]matrice) {
		
		
		Noeud n=new Noeud(typeJoueur,matrice);
		boolean gagne=false;
		//
		boolean fin=true;
		
		for(int i=0;i<this.WIDTH;i++) {
			for(int j=0;j<this.HEIGHT;j++){
				
				if(matrice[i][j]==0)
					
				fin=false;
				
			}
		}
		
		if((fin==false && n.troisPionsAlignesLigne(typeJoueur)!=0) || (fin==false && n.troisPionsAlignesColonne(typeJoueur)!=0)) {
			gagne=true;
		}
		
		return gagne;
	}
	
	public void copieMatrice(int [][]mSource,int [][]mDest) {
		
		for(int i=0;i<mSource.length;i++) {
			for(int j=0;j<mDest.length;j++){
				
				mDest[i][j]=mSource[i][j];
				
			}
		}
		
		
		
	}
	
	public int [][]getMatrice(){
		return this.matriceJeu;
	}
	
	
	
    public Coup alpha_beta(Noeud n, int alpha, int beta, int profondeur){
        int bestJ = 0;
        int a, b;
        Coup MeilleurCoup;
        Coup coup;

        if((profondeur == 1) || (estFinJeu(n.isMax(), n.getMatrice()))){
            n.evaluer();
            MeilleurCoup = new Coup(n.getH(), -1);
            return MeilleurCoup;
        }

        if(n.isMax()){
            a = alpha;
            int j;
            for(j=0; j<4; j++){
                int MatriceDestMax[][] = new int[n.getMatrice().length][n.getMatrice().length];
                copieMatrice(n.getMatrice(), MatriceDestMax);
                jouer(n.isMax(), j, MatriceDestMax);
                boolean maxSucc = !(n.isMax()); //
                Noeud successeur = new Noeud(maxSucc,MatriceDestMax);
                coup = alpha_beta(successeur, a, beta, profondeur-1);
                successeur.setH(coup.getEval());

                if(coup.getEval() > a){
                    a = coup.getEval();
                    bestJ = j;
                }

                if(a >= beta){
                    coup = new Coup(a, bestJ);
                    return coup;
                }
            }
            coup = new Coup(a, bestJ);
            return coup;
        }

        else{
            b = beta;
            int j;
            for(j=0; j<4; j++){
                int MatriceDestMin[][] = new int[n.getMatrice().length][n.getMatrice().length];
                copieMatrice(n.getMatrice(), MatriceDestMin);
                jouer(n.isMax(), j, MatriceDestMin);
                boolean minSucc = !(n.isMax()); //
                Noeud successeur = new Noeud( minSucc,MatriceDestMin);
                coup = alpha_beta(successeur, alpha, b, profondeur-1);
                successeur.setH(coup.getEval());

                if(coup.getEval() < b){
                    b = coup.getEval();
                    bestJ = j;
                }

                if(b <= beta){
                    coup = new Coup(b, bestJ);
                    return coup;
                }
            }
            coup = new Coup(b, bestJ);
            return coup;
        }

    }
    
    public String toString() {
    	
    	String Grille="\n";
        int taille = this.matriceJeu.length;
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                int indice = this.matriceJeu[i][j];
                if (indice >= -1 && indice <= 9){
                    if(indice == 1)
                        Grille += " | " + Integer.toString(this.matriceJeu[i][j]) +    " | ";
                    else if(indice == 2)
                        Grille += " | "  + Integer.toString(this.matriceJeu[i][j]) +  " | ";
                    else
                        Grille += " | " + Integer.toString(this.matriceJeu[i][j]) + " | ";
                }
                if (indice >= 10 && indice <= 99)
                    Grille += " | " + Integer.toString(this.matriceJeu[i][j]) + " | ";
            }
            Grille += "\n";
        }
        return Grille;

    	
    	
    }



	}
	
	
	


