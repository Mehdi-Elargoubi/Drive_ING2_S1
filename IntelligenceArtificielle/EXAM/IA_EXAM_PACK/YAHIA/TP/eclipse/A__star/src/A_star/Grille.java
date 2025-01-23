package A_star;

import java.util.Arrays;

public class Grille {
	int grille[][];
	int taille;
	int ligne0;
	int colone0;
	
	public Grille(int[][] g) {
		this.taille=g.length;
		grille = new int[taille][taille];
		//grille = g;
		int i,j;
		for(i = 0; i < taille; i++) {
			for(j = 0; j < taille; j++) {
				grille[i][j]=g[i][j];
				if(grille[i][j]==0) {
					ligne0=i;
					colone0=j;
				}	
			}
		}
	}

	public int[][] getGrille() {
		return grille;
	}
	
	public int getTaille() {
		return taille;
	}

	public int getLigne0() {
		return ligne0;
	}

	public int getColone0() {
		return colone0;
	}
	
	public int getValeur(int i, int j) {
		return grille[i][j];
	}
	
	public int[][] copier(){
		int[][] g = new int[taille][taille];
		int i,j;
		for(i = 0; i < taille; i++) {
			for(j = 0; j < taille; j++) {
				g[i][j] = grille[i][j];
			}
		}
		return g;
	}

	@Override
	public String toString() {
		String res="\n";
		int i,j;
		for(i = 0; i < taille; i++) {
			res+="\n";
			for(j = 0; j < taille; j++) {
				res+=grille[i][j]+" ";
			}
		}
		res+="\n";
		return "Grille [grille=" + res + ", taille=" + taille + ", ligne0=" + ligne0 + ", colone0="
				+ colone0 + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grille other = (Grille) obj;
		return colone0 == other.colone0 && Arrays.deepEquals(grille, other.grille) && ligne0 == other.ligne0
				&& taille == other.taille;
	}
	
	/*
	public static void main(String[] args) {
		int [] [] tab = {{7,2,4},{5,0,6},{8,3,1}};
		Grille grille1 = new Grille(tab);
		System.out.println(grille1);
		System.out.println("-----------------------");
		int [] [] tab_copy = grille1.copier();
		Grille grille2 = new Grille(tab_copy);
		System.out.println(grille2);
		System.out.println("-----------------------");
		System.out.println("\n" + grille1.equals(grille2));
		
	}
	*/
}
