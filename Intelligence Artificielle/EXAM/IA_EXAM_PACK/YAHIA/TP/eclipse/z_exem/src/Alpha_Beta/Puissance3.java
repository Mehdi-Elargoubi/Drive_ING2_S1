package Alpha_Beta;

//import java.util.ArrayList;
//import java.util.Arrays;

public class Puissance3 {
	int[][] matriceJeu;
	public int width=5;
	public int height=5;
	
	

	public Puissance3(int [][] matriceJeu) {
		this.matriceJeu = new int[width][height];
		copyMatrice(matriceJeu, this.matriceJeu);
	}
	
	public void copyMatrice(int [][] mS, int [][] mD) {
		int i,j;
		for(i=0;i<this.width;i++) {
			for(j=0;j<this.height;j++) {
				mD[i][j]=mS[i][j];
			}
		}
	}
	

	public int[][] getMatriceJeu() {
		/*int[][] ret;
		ret = new int[width][height];
		copyMatrice(matriceJeu, ret);
		return ret;*/
		return matriceJeu;
	}
	
	public boolean jouer(boolean typeJoueur, int j, int matrice[][]) {
		int i;
		if(j==-1) return true;
		if(matrice[height-1][j]!=0) return false;
		if( j >= 0 && j < this.height){
		for(i=0;i<width;i++)
			if(matrice[i][j]==0) {
				
				if(typeJoueur) {
					matrice[i][j]=1;
					//System.out.println( "matrice[i][j]= " + matrice[i][j] + "\n");
				}
					
				if(!typeJoueur) {
					matrice[i][j]=2;
					//System.out.println( "matrice[i][j]= " + matrice[i][j] +"\n");
				}
					
				return true;
			}
		}		
		return false;
	}
	
	public boolean estFinJeu(boolean typeJoueur, int matrice[][]) {
		Node n = new Node(typeJoueur, matrice);
		//System.out.println(Math.abs(n.evaluer()));
		/*if(Math.abs(n.getH())>1000)
			return true;
		return false;*/
		boolean gagnant = (n.troisPionsAlignesLigne(typeJoueur) != 0)
				|| (n.troisPionsAlignesColonne(typeJoueur) != 0);
		boolean full = true;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (matrice[i][j] == 0)
					full = false;
				break;
			}

		}

		return gagnant || full;

	}
	
	/*public Coup meilleurCoup(int h, int profondeur) {
		Coup c = new Coup(0, 0);
		return c;
	}*/
	//static int counter = 0;
	public Coup alpha_beta(Node n, int alpha, int beta, int profondeur){
		int m[][]= new int[width][height];
		Coup c;
		int bestj = 1,j;
		boolean test;
		Node succ=null;
		
		if(profondeur == 1 || this.estFinJeu(!n.isMax(),n.matrice)) {
			//counter++;
			//System.out.println( "Counter = \n" + counter);
			n.evaluer();
			return new Coup(n.getH(),-1);
		}
		if(n.max) {
			//counter++;
			//System.out.println( "Counter = \n" + counter);
			for(j=0;j<this.width;j++) {
				copyMatrice(n.matrice, m);
				//System.out.println( "j = \n" + j);
				test=jouer(n.max,j,m);
				if(test) {
					succ = new Node(!n.max,m);
				}else {
					continue;
				}
				c = alpha_beta(succ, alpha, beta, profondeur-1);
				succ.setH(c.getEval());
				if(c.getEval()>alpha) {
					alpha = c.getEval();
					bestj=j;
					//System.out.println( "bestj = \n" + bestj);
				}
				if(alpha>=beta)
					return new Coup(alpha,bestj);
			}
			return new Coup(alpha,bestj);
		}else {
			for(j=0;j<this.width;j++) {
				copyMatrice(n.matrice, m);
				//System.out.println( "j = " + j);
				test=jouer(n.max,j,m);
				if(test) {
					
					succ = new Node(!n.max,m);
				}else {
					continue;
				}
				c = alpha_beta(succ, alpha, beta, profondeur-1);
				succ.setH(c.getEval());
				if(c.getEval()<beta) {
					beta = c.getEval();
					bestj=j;
					//System.out.println( "bestj = " + bestj);
				}
				if(alpha>=beta)
					return new Coup(beta,bestj);
			}
			return new Coup(beta,bestj);
		}
	}
	
	public String afficher_matrice(int [][] matriceJeu, int width, int height) {
		int i,j;
		String s="\n";
		for(i=width-1;i>=0;i--) {
			for(j=0;j<height;j++) {
				s=s+"\t"+matriceJeu[i][j];
			}
			s=s+"\n";
		}
		return "matrice [matriceJeu=" + s + "]";
	}
	
	@Override
	public String toString() {
		int i,j;
		String s="\n";
		for(i=this.width-1;i>=0;i--) {
			for(j=0;j<this.height;j++) {
				s=s+"\t"+matriceJeu[i][j];
			}
			s=s+"\n";
		}
		return "Puissance3 [matriceJeu=" + s + ", width=" + width + ", height=" + height
				+ "]";
	}
}
