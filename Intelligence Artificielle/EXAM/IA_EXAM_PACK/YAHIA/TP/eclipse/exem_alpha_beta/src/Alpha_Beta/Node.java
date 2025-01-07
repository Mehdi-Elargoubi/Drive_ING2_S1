package Alpha_Beta;

// // veuillez ignorer les commentaire qui sont a l' interieur des methodes c'etaient juste pour les debugages
// le code est le meme que j'ai fait en tp alpha beta et a chaque methode je vais mentionner en commentaires
//les changement que j'ai fait dans chaque methode
import java.util.Arrays;

public class Node {
	int [][] matrice;
	boolean max;
	int h;
	
	public Node(boolean max, int[][] matrice) {
		this.max=max;
		int i,j;
		this.matrice = new int[matrice.length][matrice.length];
		for(i=0;i<matrice.length;i++) {
			for(j=0;j<matrice.length;j++) {
				this.matrice[i][j]=matrice[i][j];
			}
		}
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

	// ici j'ai adapté l'affichage pour qui'il devient en carataire X et O mais deriere il y a 1 et 2 comme jour puisque c'est la meme logique
	@Override
	public String toString() {
		int i,j;
		char mat[][] = new char[matrice.length][matrice.length];
		for(i=0;i<this.matrice.length;i++) {
			for(j=0;j<this.matrice.length;j++) {
				mat[i][j]='-';
				if(matrice[i][j]==1)mat[i][j]='X';
				if(matrice[i][j]==2)mat[i][j]='O';
				
			}
		}
		String s="\n";
		for(i=matrice.length-1;i>=0;i--) {
			for(j=0;j<matrice.length;j++) {
				s=s+"\t"+mat[i][j];
			}
			s=s+"\n";
		}
		return "Node [matrice=" + s + "]";
	}
	
	//ici j'ai modifie la valeur demandee dans l'ennonce 
	public int troisPionsAlignesLigne(boolean typeJoueur) {
		int i,j;
		String s;
		for(i=0;i<this.matrice.length;i++) {
			s="";
			for(j=0;j<this.matrice.length;j++) {
				s=s+matrice[i][j];
				//System.out.println(s + s.contains("111"));
				//System.out.println(s + s.contains("222"));
			}
			//System.out.println(s + s.contains("111"));
			//System.out.println(s + s.contains("222"));
			
			if(typeJoueur)
				if(s.contains("111"))
					return 30;
			if(!typeJoueur)
				if(s.contains("222"))
					return -30;
		}
		return 0;
	}
	// ici c'est pareil
	public int troisPionsAlignesColonne(boolean typeJoueur) {
		int i,j;
		String s="";
		for(i=0;i<this.matrice.length;i++) {
			s="";
			for(j=0;j<this.matrice.length;j++) {
				
				s=s+matrice[j][i];
				//System.out.println(s);
			}
			//System.out.println(s + s.contains("111"));
			//System.out.println(s + s.contains("222"));
			//----------------------------------------------------------------------System.out.println(s);
			//System.out.println(s + s.contains("111"));
			//System.out.println(s + s.contains("222"));
			if(s.contains("111")){
				return 30;
			}
			if(s.contains("222")) {
				return -30;
			}
				
		}
		return 0;
	}
	
	//ici j'ai ajoute la methode qui retourne la valeur 30 si une diagonale est complete par des X ou de O
	public int troisPionsAlignesDiagonale(boolean typeJoueur) {
		int i,j;
		String s1="",s2="";
		for(i=0,j=0;i<this.matrice.length;i++,j++) {
			s1=s1+matrice[i][j];
			s2=s2+matrice[i][matrice.length-1-j];
		}
		/*System.out.println(s1 + s1.contains("111"));
		System.out.println(s1 + s1.contains("222"));
		System.out.println(s2 + s2.contains("111"));
		System.out.println(s2 + s2.contains("222"));*/
		if(typeJoueur)
			if(s1.contains("111")||s2.contains("111"))
				return 30;
		if(!typeJoueur)
			if(s1.contains("222")||s2.contains("222"))
				return -30;
		return 0;
	}

	//ici j'ai modifie en ajoutant les conditions adabtable au alignement possible pour une ligne pour avoir 
	//la somme de toute les lignes
	public int troisPionsPossiblesLigne(boolean typeJoueur) {
		int i,j,res=0;
		String s="";
		for(i=0;i<this.matrice.length;i++) {
			s="";
			for(j=0;j<this.matrice.length;j++) {
				s=s+matrice[i][j];
			}
			if(typeJoueur) {
				if(s.contains("011"))
					res=res+9;
				if(s.contains("110"))
					res=res+9;
				if(s.contains("101"))
					res=res+9;
				if(s.contains("010"))
					res=res+6;
				if(s.contains("100"))
					res=res+6;
				if(s.contains("001"))
					res=res+6;
			}
			if(!typeJoueur){
				if(s.contains("022"))
					res=res-9;
				if(s.contains("202"))
					res=res-9;
				if(s.contains("220"))
					res=res-9;
				if(s.contains("020"))
					res=res-6;
				if(s.contains("200"))
					res=res-6;
				if(s.contains("002"))
					res=res-6;
			}
		}
		
		return res;
	}
	//ici j'ai modifie en ajoutant les conditions adabtable au alignement possible pour une ligne pour avoir 
	//la somme de toute les colonnes
	public int troisPionsPossiblesColonne(boolean typeJoueur) {
		int i,j,res=0;
		String s="";
		for(i=0;i<this.matrice.length;i++) {
			s="";
			for(j=0;j<this.matrice.length;j++) {
				s=s+matrice[j][i];
			}
			if(typeJoueur) {
				if(s.contains("011"))
					res=res+9;
				if(s.contains("110"))
					res=res+9;
				if(s.contains("101"))
					res=res+9;
				if(s.contains("010"))
					res=res+6;
				if(s.contains("100"))
					res=res+6;
				if(s.contains("001"))
					res=res+6;
			}
			if(!typeJoueur){
				if(s.contains("022"))
					res=res-9;
				if(s.contains("202"))
					res=res-9;
				if(s.contains("220"))
					res=res-9;
				if(s.contains("020"))
					res=res-6;
				if(s.contains("200"))
					res=res-6;
				if(s.contains("002"))
					res=res-6;
			}
		}
		
		return res;
	}
	
	//ici j'ai ajoute la methode qui retourne la somme des possibilite si une diagonale est potentielement peut
	// aider a etre alignee par des X ou de O
	//ici j'ai modifie en ajoutant les conditions adabtable au alignement possible pour une ligne pour avoir 
	//la somme de toute les lignes
	public int troisPionsPossiblesDiagonale(boolean typeJoueur) {
		int i,j,res=0;
		String s1="",s2="";
		for(i=0,j=0;i<this.matrice.length;i++,j++) {
			s1=s1+matrice[i][j];
			s2=s2+matrice[i][matrice.length-1-j];
		}
		if(typeJoueur) {
			if(s1.contains("011")||s2.contains("011"))
				res=res+9;
			if(s1.contains("110")||s2.contains("110"))
				res=res+9;
			if(s1.contains("101")||s2.contains("101"))
				res=res+9;
			if(s1.contains("010")||s2.contains("010"))
				res=res+6;
			if(s1.contains("100")||s2.contains("100"))
				res=res+6;
			if(s1.contains("001")||s2.contains("001"))
				res=res+6;
		}
		if(!typeJoueur){
			if(s1.contains("022")||s2.contains("022"))
				res=res-9;
			if(s1.contains("202")||s2.contains("202"))
				res=res-9;
			if(s1.contains("220")||s2.contains("220"))
				res=res-9;
			if(s1.contains("020")||s2.contains("020"))
				res=res-6;
			if(s1.contains("200")||s2.contains("200"))
				res=res-6;
			if(s1.contains("002")||s2.contains("002"))
				res=res-6;
		}
		
		return res;
	}
	
	// j'ai modifie la methode evaluer selon l'exemple de l'ennoncee
	public int evaluer() {
		this.h =  troisPionsAlignesLigne(false)
				+ troisPionsAlignesLigne(true)
				
				+ troisPionsAlignesColonne(false)
				+ troisPionsAlignesColonne(true)
				
				+ troisPionsAlignesDiagonale(false)
				+ troisPionsAlignesDiagonale(true)
				
				+ troisPionsPossiblesLigne(false)
				+ troisPionsPossiblesLigne(true)
				
				+ troisPionsPossiblesColonne(false)
				+ troisPionsPossiblesColonne(true)
				
				+ troisPionsPossiblesDiagonale(false)
				+ troisPionsPossiblesDiagonale(true)
				
				;
		return this.h;
	}
	
	// veuillez  ignoer ce main c'etait juste pour debuger les methodes sur lesquels j'ai tavailler dans cette classe
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m[][] = {{0,0,1},{0,1,0},{1,0,0}};
		
		Node racine = new Node(true,m);
		System.out.println(racine.toString());
		System.out.println(racine.troisPionsPossiblesLigne(true));
		//
		System.out.println(racine.troisPionsPossiblesColonne(true));
		//30
		System.out.println("diag : "+racine.troisPionsPossiblesDiagonale(true));
		//30
		System.out.println(racine.troisPionsAlignesDiagonale(true));
		//
		System.out.println(racine.troisPionsAlignesColonne(true));
		//
		System.out.println(racine.troisPionsAlignesLigne(true));
		//
		racine.evaluer();
		System.out.println(racine.getH());
		// -460
	}
}
