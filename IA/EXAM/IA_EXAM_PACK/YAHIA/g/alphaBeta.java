package Alpha_Beta;

public class alphaBeta { //c'est puissance3

	private String[][] matriceJeu;
	private int WIDTH =3;
	private int HEIGHT =3;
	
	
	public alphaBeta(String[][] matriceJeu) {
		
		this.matriceJeu = new String[WIDTH][HEIGHT];
		copyMatrice(matriceJeu, this.matriceJeu);
		
	}
	
	void copyMatrice(String[][] mS, String[][] mD) {
		
		for(int i=0; i<mS.length; i++) {
			for(int j=0; j<mS.length; j++) {
				mD[i][j] = mS[i][j];
			}
		}
	}

	public String[][] getMatriceJeu() {
		return matriceJeu;
	}

	public void setMatriceJeu(String[][] matriceJeu) {
		this.matriceJeu = matriceJeu;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}
	
	public boolean jouer(boolean typeJoueur, int j, String[][] matrice) {
		
		
		
		if(j==-1) return true;//victoire d'un joueur
		if(matrice[HEIGHT-1][j]!= "0") return false;//grille pleine pour col j
		//tester si i,j est = 0
		for(int i=0;i<WIDTH;i++)
			if(matrice[i][j]== "0") {
				
				if(typeJoueur) {
					matrice[i][j]="O";
					//System.out.println( "matrice[i][j]= " + matrice[i][j] + "\n");
				}
					
				if(!typeJoueur) {
					matrice[i][j]="X";
					//System.out.println( "matrice[i][j]= " + matrice[i][j] +"\n");
				}
					
				return true;//coup reussi
			}		
		return false;
	}
	
	public boolean estFinJeu(boolean typeJoueur, String[][] matrice) {
		
		Noeud n = new Noeud(typeJoueur, matrice);
		boolean gangant = (n.troisPionsAlignesColonne(typeJoueur) !=0) 
				|| (n.troisPionsAlignesLigne(typeJoueur) != 0) ;
		boolean full =true;
		
		for(int i=0; i<HEIGHT; i++) {
			for(int j=0; j<WIDTH; j++) {
				if(matrice[i][j] == "0") {
					full = false; //s'il y a une case 0 alors n'est pas pleine
					break;
				}
				
			}
		}
		
		return gangant || full;
		
	}
	
	
	public Coup alphabeta(Noeud n, int alpha, int beta, int profondeur) {
		Coup coup ;
		
		String m[][] = new String[WIDTH][HEIGHT];
		
		int bestj = 0 , j;
		boolean test;
		Noeud succ = null;
		
		if(profondeur == 1 || this.estFinJeu(!n.isMax(),n.getMatrice())) {
			//counter++;
			//System.out.println( "Counter = \n" + counter);
			n.evaluer();
			return new Coup(n.getH(),-1);
		}
		if(n.isMax()) {
			//counter++;
			//System.out.println( "Counter = \n" + counter);
			for(j=0;j<this.WIDTH;j++) {
				copyMatrice(n.getMatrice(), m);
				//System.out.println( "j = \n" + j);
				test=jouer(n.isMax(),j,m);
				if(test) {
					succ = new Noeud(!n.isMax(),m);
				}else {
					continue;
				}
				coup = alphabeta(succ, alpha, beta, profondeur-1);
				succ.setH(coup.getEval());
				if(coup.getEval()>alpha) {
					alpha = coup.getEval();
					bestj=j;
					//System.out.println( "bestj = \n" + bestj);
				}
				if(alpha>=beta)
					return new Coup(alpha,bestj);
			}
			return new Coup(alpha,bestj);
		}else {
			for(j=0;j<this.WIDTH-1;j++) {
				copyMatrice(n.getMatrice(), m);
				//System.out.println( "j = " + j);
				test=jouer(n.isMax(),j,m);
				if(test) {
					
					succ = new Noeud(!n.isMax(),m);
				}else {
					continue;
				}
				coup = alphabeta(succ, alpha, beta, profondeur-1);
				succ.setH(coup.getEval());
				if(coup.getEval()<beta) {
					beta = coup.getEval();
					bestj=j;
					//System.out.println( "bestj = " + bestj);
				}
				if(alpha>=beta)
					return new Coup(beta,bestj);
			}
			return new Coup(beta,bestj);
		}
	}
	
//	public String afficher_matrice(String [][] matriceJeu, int width, int height) {
//		int i,j;
//		String s="\n";
//		for(i=width-1;i>=0;i--) {
//			for(j=0;j<height;j++) {
//				s=s+"\t"+matriceJeu[i][j];
//			}
//			s=s+"\n";
//		}
//		return "matrice [matriceJeu=" + s + "]";
//	}
	
	
	public String afficher_matrice(String [][] matriceJeu, int width, int height) {
		int i,j;
		String s="\n";
		for(i=0;i<width;i++) {
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
		for(i=this.WIDTH -1;i>=0;i--) {
			for(j=0;j<this.HEIGHT;j++) {
				s=s+"\t"+matriceJeu[i][j];
			}
			s=s+"\n";
		}
		return "Puissance3 [matriceJeu=" + s + ", width=" + WIDTH + ", height=" + HEIGHT
				+ "]";
	}
	
	//@Override
//	public String toString() {
//		int i,j;
//		String s="\n";
//		for(i=0;i<this.WIDTH;i++) {
//			for(j=0;j<this.HEIGHT;j++) {
//				s=s+"\t"+matriceJeu[i][j];
//			}
//			s=s+"\n";
//		}
//		return "Puissance3 [matriceJeu=" + s + ", width=" + WIDTH + ", height=" + HEIGHT
//				+ "]";
//	}
	
	

}
