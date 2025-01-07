package exem;

import java.util.Arrays;

public class Noeud {
	
	private String[][] matrice;
	private boolean max;
	private int h;

	
	public Noeud(boolean max, String[][] matrice) {
		
		this.max = max; //true si joueur est max sinon false: joueur min
		this.matrice = matrice;
		
//		this.matrice = new int[matrice.length][matrice.length];
//		for(int i=0; i<matrice.length; i++) {
//			for(int j=0; j<matrice.length; j++) {
//				this.matrice[i][j] = matrice[i][j];
//			}
//		}
	}


	public String[][] getMatrice() {
		return matrice;
	}


	public void setMatrice(String[][] matrice) {
		this.matrice = matrice;
	}


	public boolean isMax() {
		return max;
	}


	public void setMax(boolean max) {
		this.max = max;
	}


	public int getH() {
		return h;
	}


	public void setH(int h) {
		this.h = h;
	}
	
	
	
	@Override
	public String toString() {
		String s= "Noeud [matrice=\n" ;
		for(int i=0; i<matrice.length; i++) {
			for(int j=0; j<matrice.length; j++) {
				s += matrice[i][j];
			}
			s +="\n";
		}
		
		s += ", max=" + max + ", h=" + h + "]";
		return s;
	}
	
	
	public int troisPionsAlignesLigne(boolean typeJoueur) {
		int score = 0;
		String s; //pour chercher les patterns OOO .. pour j1=max => SCORE 30..
		
		for(int i=0; i<matrice.length; i++) {
			//initialise s pour chaque ligne
			s = "";
			for(int j=0; j<matrice.length; j++) {
				s += matrice[i][j];
			}
			//verifie la ligne
			if( typeJoueur &&  s.contains("OOO")) {
				score = 30;
			}
			else if( !typeJoueur && s.contains("XXX")) {
				score = -30;
			}
			
		}
		
		return score;
		
	}
	
	
	public int troisPionsAlignesColonne(boolean typeJoueur) {
		
		int score = 0;
		String s; //pour chercher les patterns OOO en col pour j1 => SCORE 30
		
		for(int i=0; i<matrice.length; i++) {
			//initialise s pour chaque col
			s = "";
			for(int j=0; j<matrice.length; j++) {
				s += matrice[j][i];//on inverse i et j pour chercher en col
			}
			//verifie la ligne
			if( typeJoueur &&  s.contains("OOO")) {
				score = 30;
			}
			else if( !typeJoueur && s.contains("XXX")) {
				score = -30;
			}
			
		}
		
		return score;
		
	}
	
	
	
	public int troisPionsAlignesDiagonale(boolean typeJoueur) {
		
		int score = 0;
		String s1="", s2=""; //pour chercher les patterns OOO en diagonale pour j1 => SCORE 30
		int j = matrice.length -1;
		for(int i=0; i<matrice.length; i++) { //i= 0 1 2
			//initialise s
//			s1 = "";
//			s2 = "";
			 //j= 2 1 0 
			s1 += matrice[i][i]; // 00 11 22 
			s2 += matrice[i][j];// 02 11 20
			j--;
			
			
		}
			
		//verifie la digonale1
		if( typeJoueur &&  s1.contains("OOO")) {
			score = 30;
		}
		if( typeJoueur &&  s2.contains("OOO")) {
			score = 30;
		}
		
		
		//pour joueur X min
		if( !typeJoueur &&  s1.contains("XXX")) {
			score = -30;
		}
		if( !typeJoueur &&  s2.contains("XXX")) {
			score = -30;
		}
		
		return score;
		
	}
	
	
	
	public int troisPionsPossiblesDiagonale(boolean typeJoueur) {
		
		int score = 0;
		String s1="", s2=""; //pour chercher les patterns OOO en diagonale pour j1 => SCORE 30
		int j = matrice.length -1;
		for(int i=0; i<matrice.length; i++) { //i= 0 1 2
			//initialise s
//			s1 = "";
//			s2 = "";
			 //j= 2 1 0 
			s1 += matrice[i][i]; // 00 11 22 
			s2 += matrice[i][j];// 02 11 20
			j--;
			
			
		}
			
		//verifie la digonale1
		
		
		if( typeJoueur &&  s1.contains("OO0")) {
			score += 9;
		}
		if( typeJoueur &&  s1.contains("O0O")) {
			score += 9;
		}
		if( typeJoueur &&  s1.contains("0OO")) {
			score += 9;
		}
		if( typeJoueur &&  s1.contains("00O")) {
			score += 6;
		}
		if( typeJoueur &&  s1.contains("0O0")) {
			score += 6;
		}
		if( typeJoueur &&  s1.contains("O00")) {
			score += 6;
		}
//-------------Diagonale 2
		if( typeJoueur &&  s2.contains("OO0")) {
			score += 9;
		}
		if( typeJoueur &&  s2.contains("O0O")) {
			score += 9;
		}
		if( typeJoueur &&  s2.contains("0OO")) {
			score += 9;
		}
		if( typeJoueur &&  s2.contains("00O")) {
			score += 6;
		}
		if( typeJoueur &&  s2.contains("0O0")) {
			score += 6;
		}
		if( typeJoueur &&  s2.contains("O00")) {
			score += 6;
		}
		
		
		//pour joueur X min
		
		if( !typeJoueur &&  s1.contains("XX0")) {
			score += (-9);
		}
		if( !typeJoueur &&  s1.contains("X0X")) {
			score += (-9);
		}
		if( !typeJoueur &&  s1.contains("0XX")) {
			score += (-9);
		}
		if( !typeJoueur &&  s1.contains("00X")) {
			score += (-6);
		}
		if( !typeJoueur &&  s1.contains("0X0")) {
			score += (-6);
		}
		if( !typeJoueur &&  s1.contains("X00")) {
			score += (-6);
		}
//-------------Diagonale 2
		if( !typeJoueur &&  s2.contains("XX0")) {
			score += (-9);
		}
		if( !typeJoueur &&  s2.contains("X0X")) {
			score += (-9);
		}
		if( !typeJoueur &&  s2.contains("0XX")) {
			score += (-9);
		}
		if( !typeJoueur &&  s2.contains("00X")) {
			score += (-6);
		}
		if( !typeJoueur &&  s2.contains("0X0")) {
			score += (-6);
		}
		if( !typeJoueur &&  s2.contains("X00")) {
			score += (-6);
		}
		
		
		
		return score;
		
	}
	
	
	
	public int troisPionsPossiblesLigne(boolean typeJoueur) {
		
		int score = 0;
		String s; //pour chercher les patterns 111 en ligne pour j1 => SCORE 1000
		
		for(int i=0; i<matrice.length; i++) {
			//initialise s pour chaque ligne
			s = "";
			for(int j=0; j<matrice.length; j++) {
				s += matrice[i][j];
			}
			//verifie la ligne pour max
			
			if( typeJoueur &&  s.contains("OO0")) {
				score += 9;
			}
			if( typeJoueur &&  s.contains("O0O")) {
				score += 9;
			}
			if( typeJoueur &&  s.contains("0OO")) {
				score += 9;
			}
			if( typeJoueur &&  s.contains("00O")) {
				score += 6;
			}
			if( typeJoueur &&  s.contains("0O0")) {
				score += 6;
			}
			if( typeJoueur &&  s.contains("O00")) {
				score += 6;
			}
			
			
			//pour joueur X min
			
			if( !typeJoueur &&  s.contains("XX0")) {
				score += (-9);
			}
			if( !typeJoueur &&  s.contains("X0X")) {
				score += (-9);
			}
			if( !typeJoueur &&  s.contains("0XX")) {
				score += (-9);
			}
			if( !typeJoueur &&  s.contains("00X")) {
				score += (-6);
			}
			if( !typeJoueur &&  s.contains("0X0")) {
				score += (-6);
			}
			if( !typeJoueur &&  s.contains("X00")) {
				score += (-6);
			}
			
			
		}
		
		return score;
		
	}
	
	public int troisPionsPossiblesColonne(boolean typeJoueur) {
		
		int score = 0;
		String s; //pour chercher les patterns 111 en ligne pour j1 => SCORE 1000
		
		for(int i=0; i<matrice.length; i++) {
			//initialise s pour chaque ligne
			s = "";
			for(int j=0; j<matrice.length; j++) {
				s += matrice[j][i];
			}
			
//verifie la col pour max
			
			if( typeJoueur &&  s.contains("OO0")) {
				score += 9;
			}
			if( typeJoueur &&  s.contains("O0O")) {
				score += 9;
			}
			if( typeJoueur &&  s.contains("0OO")) {
				score += 9;
			}
			if( typeJoueur &&  s.contains("00O")) {
				score += 6;
			}
			if( typeJoueur &&  s.contains("0O0")) {
				score += 6;
			}
			if( typeJoueur &&  s.contains("O00")) {
				score += 6;
			}
			
			
			//pour joueur X min
			
			if( !typeJoueur &&  s.contains("XX0")) {
				score += (-9);
			}
			if( !typeJoueur &&  s.contains("X0X")) {
				score += (-9);
			}
			if( !typeJoueur &&  s.contains("0XX")) {
				score += (-9);
			}
			if( !typeJoueur &&  s.contains("00X")) {
				score += (-6);
			}
			if( !typeJoueur &&  s.contains("0X0")) {
				score += (-6);
			}
			if( !typeJoueur &&  s.contains("X00")) {
				score += (-6);
			}
			
		}
		
		return score;
		
		
	}
	
	
	public int evaluer() {
		
		this.h = troisPionsAlignesLigne(false)
				+ troisPionsAlignesLigne(true)
				+troisPionsAlignesColonne(false)
				+ troisPionsAlignesColonne(true)
				+ troisPionsAlignesDiagonale(false)
				+ troisPionsAlignesDiagonale(true)
				+troisPionsPossiblesLigne(false)
				+ troisPionsPossiblesLigne(true)
				+ troisPionsPossiblesColonne(false)
				+ troisPionsPossiblesColonne(true)
				+ troisPionsPossiblesDiagonale(false)
				+ troisPionsPossiblesDiagonale(true);
		
		return h;
	}
	
}
