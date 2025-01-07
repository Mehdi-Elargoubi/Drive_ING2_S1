package Alpha_Beta;

import java.util.Arrays;

public class Node {
	int [][] matrice;
	boolean max;
	int h;
	
	public Node(boolean max, int[][] matrice) {
		this.max=max;
		//int i,j;
		this.matrice =matrice; //new int[matrice.length][matrice.length];
		//for(i=0;i<matrice.length;i++) {
		//	for(j=0;j<matrice.length;j++) {
		//		this.matrice[i][j]=matrice[i][j];
		//	}
		//}
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

	@Override
	public String toString() {
		return "Node [matrice=" + Arrays.toString(matrice) + ", max=" + max + ", h=" + h + "]";
	}
	
	public int troisPionsAlignesLigne(boolean typeJoueur) {
		int i,j;
		String s;
		for(i=0;i<this.matrice.length;i++) {
			s="";
			for(j=0;j<this.matrice.length;j++) {
				s=s+matrice[i][j];
			}
			if(typeJoueur)
				if(s.contains("111"))
					return 1000;
			if(!typeJoueur)
				if(s.contains("222"))
					return 1000;
		}
		return 0;
	}
	
	public int troisPionsAlignesColonne(boolean typeJoueur) {
		int i,j;
		String s;
		for(i=0;i<this.matrice.length;i++) {
			s="";
			for(j=0;j<this.matrice.length;j++) {
				
				s=s+matrice[j][i];
				//System.out.println(s);
			}
			System.out.println(s);
			if(s=="111")
				return 10000;
			if(s=="222")
				return 10000;
		}
		return 0;
	}
	
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
					res=res+200;
				if(s.contains("101"))
					res=res+200;
				if(s.contains("110"))
					res=res+200;
				if(s.contains("01"))
					res=res+30;
				if(s.contains("10"))
					res=res+30;
			}
			if(!typeJoueur){
				if(s.contains("022"))
					res=res+200;
				if(s.contains("202"))
					res=res+200;
				if(s.contains("220"))
					res=res+200;
				if(s.contains("02"))
					res=res+30;
				if(s.contains("20"))
					res=res+30;
			}
		}
		
		return res;
	}
	
	public int troisPionsPossiblesColonne(boolean typeJoueur) {
		int i,j,res=0;
		String s="";
		for(i=0;i<this.matrice.length;i++) {
			s="";
			for(j=0;j<this.matrice.length;j++) {
				s=s+matrice[j][i];
			}
			if(typeJoueur) {
				//if(s.contains("011"))
					//res=res+200;
			//	if(s.contains("101"))
				//	res=res+200;
				if(s.contains("110"))
					res=res+200;
				//if(s.contains("01"))
				//	res=res+30;
				if(s.contains("10"))
					res=res+30;
			}
			if(!typeJoueur){
			//	if(s.contains("022"))
				//	res=res+200;
				//if(s.contains("202"))
					//res=res+200;
				if(s.contains("220"))
					res=res+200;
				//if(s.contains("02"))
					//res=res+30;
				if(s.contains("20"))
					res=res+30;
			}
		}
		
		return res;
	}
	
	public int evaluer() {
		this.h =  -2*troisPionsAlignesLigne(false)
				+ troisPionsAlignesLigne(true)
				-2*troisPionsAlignesColonne(false)
				+ troisPionsAlignesColonne(true)
				-2*troisPionsPossiblesLigne(false)
				+ troisPionsPossiblesLigne(true)
				-2*troisPionsPossiblesColonne(false)
				+ troisPionsPossiblesColonne(true);
		return this.h;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m[][] = {{0,0,0,0,0},{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
		
		Node racine = new Node(true,m);
		System.out.println(racine.troisPionsPossiblesLigne(true));
		//60
		System.out.println(racine.troisPionsPossiblesLigne(false));
		//260
		
		System.out.println(racine.troisPionsPossiblesColonne(true));
		//60
		System.out.println(racine.troisPionsPossiblesColonne(false));
		//30
		
		racine.evaluer();
		System.out.println(racine.getH());
		// -460
	}
}
