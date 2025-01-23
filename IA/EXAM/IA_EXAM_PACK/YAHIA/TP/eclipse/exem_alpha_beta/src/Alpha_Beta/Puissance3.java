package Alpha_Beta;

import java.util.Arrays;

//// veuillez ignorer les commentaire qui sont a l' interieur des methodes c'etaient juste pour les debugages
//le code est le meme que j'ai fait en tp alpha beta et a chaque methode je vais mentionner en commentaires 
//les changement que j'ai fait dans chaque methode


//import java.util.ArrayList;
//import java.util.Arrays;

public class Puissance3 {
	int[][] matriceJeu;
	public int width=3;
	public int height=3;
	
	

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
	
	// j'ai ajouter cette methode pour distingue en l'egalité et si le a gagner, apres la fin du jeu
	public boolean estComplet(int matrice[][]) {
		int i,j;
		
		for(i=0;i<matrice.length;i++)
			for(j=0;j<matrice.length;j++)
				if(matrice[i][j]==0)
					return false;
		return true;
	}
	
	// pour jouer un coup il faut ajouter un parametre ligne qiu liee a l'attribut ligne de la classe Coup
	// car le jeu tic tac toe on joue a n'importe quelle case vide
	// j'ai adapte tout :
	// j'ai enlever la boucle car maintenant on a la position exacte sur laquel on va jouer
	// j'ai adapter la condition selon le n
	//-----
	// lors du test veuillez ne pas jouer sur une case deja remplie ou en dehors de la grille 
	// sinon se sera consideree comme un pass de tour
	// l'objectif du tp c'est atteindre le cas normal, du coup j'ai eviter de coder les cas 
	// d'erreurs pour ne pas perdre du temps
	public boolean jouer(boolean typeJoueur, int ligne, int colonne, int matrice[][]) {
		if(estComplet(matrice))
			return false;
		if( (colonne >= 0 && colonne < this.height) && (ligne >= 0 && ligne < this.width) ){
			if(matrice[ligne][colonne]==0) {
				
				if(typeJoueur) {
					matrice[ligne][colonne]=1;
					//System.out.println( "matrice[i][j]= " + matrice[i][j] + "\n");
				}
					
				if(!typeJoueur) {
					//System.out.println("joueur 2 : \t"+ligne +"\t"+colonne);
					matrice[ligne][colonne]=2;
					//System.out.println( "matrice[i][j]= " + matrice[i][j] +"\n");
				}
				return true;
			}
		}		
		return false;
	}
	
	// methode pour savoir si jouer a gagner comme on sait si egalite ou gagne apres fin du jeu
	// 
	public boolean gagner(boolean typeJoueur,int matrice[][]) {
		Node n = new Node(typeJoueur, matrice);
		//System.out.println(Math.abs(n.evaluer()));
		/*if(Math.abs(n.getH())>30)
			return true;
		return false;*/
		return (n.troisPionsAlignesDiagonale(typeJoueur) != 0) || (n.troisPionsAlignesLigne(typeJoueur) != 0)
		|| (n.troisPionsAlignesColonne(typeJoueur) != 0);
	}
	//fin du jeu adapted
	public boolean estFinJeu(boolean typeJoueur, int matrice[][]) {
		boolean gagnant = gagner(typeJoueur,matrice);
		boolean full = estComplet(matrice);
		return gagnant || full;
	}
	
	/*public Coup meilleurCoup(int h, int profondeur) {
		Coup c = new Coup(0, 0);
		return c;
	}*/
	//static int counter = 0;
	
	// adaptation en rechercheant la meilleur position a jouer (besti)
	public Coup alpha_beta(Node n, int alpha, int beta, int profondeur){
		int m[][]= new int[width][height];
		Coup c;
		int bestj = 1,besti =1,i,j;
		boolean test;
		Node succ=null;
		
		if(profondeur == 1 || this.estFinJeu(!n.isMax(),n.matrice)) {
			//counter++;
			//System.out.println( "Counter = \n" + counter);
			n.evaluer();
			return new Coup(n.getH(),-1,-1);
		}
		if(n.max) {
			//counter++;
			//System.out.println( "Counter = \n" + counter);
			for(i=0;i<this.width;i++) {
				for(j=0;j<this.height;j++) {
					
					copyMatrice(n.matrice, m);
					//System.out.println( "j = \n" + j);
					test=jouer(n.max,i,j,m);
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
						besti=i;
						//System.out.println( "bestj = \n" + bestj);
					}
					if(alpha>=beta)
						return new Coup(alpha,bestj,besti);
				}
			}
			return new Coup(alpha,bestj,besti);
		}else {
			for(i=0;i<this.width;i++) {
				for(j=0;j<this.width;j++) {
					copyMatrice(n.matrice, m);
					//System.out.println( "j = " + j);
					test=jouer(n.max,i,j,m);
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
						besti=i;
						//System.out.println( "bestj = " + bestj);
					}
					if(alpha>=beta)
						return new Coup(beta,besti,bestj);
				}
			}
			return new Coup(beta,besti,bestj);
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
	
	
	// adaptation d'affichage comme avant dans node
	@Override
	public String toString() {
		int i,j;
		
		char mat[][] = new char[matriceJeu.length][matriceJeu.length];
		for(i=0;i<this.matriceJeu.length;i++) {
			for(j=0;j<this.matriceJeu.length;j++) {
				mat[i][j]='-';
				if(matriceJeu[i][j]==1)mat[i][j]='X';
				if(matriceJeu[i][j]==2)mat[i][j]='O';
				
				
			}
		}
		
		
		
		String s="\n";
		for(i=this.width-1;i>=0;i--) {
			for(j=0;j<this.height;j++) {
				s=s+"\t"+mat[i][j];
			}
			s=s+"\n";
		}
		return "Puissance3 [matriceJeu=" + s + ", width=" + width + ", height=" + height
				+ "]";
	}
}
