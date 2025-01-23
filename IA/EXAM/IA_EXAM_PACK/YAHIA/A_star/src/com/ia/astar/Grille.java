package com.ia.astar;

public class Grille {

	private int  grille[][];
	private int taille;
	private int ligne0;
	private int colonne0;
	
	public Grille(int[][] grille, int taille, int ligne0, int colonne0) {
		this.grille = new int[grille.length][grille.length];
		this.taille= grille.length;
		for (int i = 0; i < grille.length; i++) {
			for (int k = 0; k < grille.length; k++) {
				this.grille[i][k]=grille[i][k];
				if(grille[i][k] == 0) {
					
					this.ligne0 = i;
					this.colonne0 =k;
			}
				}
		}
		this.taille = taille;;
	}

	public Grille() {
		
	}

	public Grille(int[][] grille) {
		this.grille = new int[grille.length][grille.length];
		this.taille= grille.length;
		for (int i = 0; i < grille.length; i++) {
			for (int k = 0; k < grille.length; k++) {
				this.grille[i][k]=grille[i][k];
				if(grille[i][k] == 0) {
					
					this.ligne0 = i;
					this.colonne0 =k;
			}
				}
		}
	}
	public int[][] copier()
	{	int nouvelleGrille[][] = new int [taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				nouvelleGrille[i][j] = grille[i][j];
			}
		}
		return nouvelleGrille;
	}
	 
	public boolean equals(Grille grille) {
		
		if (grille == null) return false;
		if(taille != grille.taille) return false;
		if(ligne0 != grille.getColonne0()) return false;
		if(colonne0 != grille.getLigne0()) return false;
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				if(grille.getGrille()[i][j] != this.getGrille()[i][j]) return false;
			}
		}
		
		return true;
	}
	
	public String afficherGrille(int[][] block){
		String l="{";
		for (int i = 0; i < taille; i++) {
			l=l+"{";
			for (int j = 0; j < taille; j++) {
				
				l=l+block[i][j];
				
				if (j != taille-1) {
					l=l+ " , ";
				}
				
			}
			l=l+"}\n";	
			if (i != taille-1) {
				l=l+ " , ";
			}
		}
		
		return l+"}";
	}
	
	//Getters And Setters
	public int[][] getGrille() {
		return grille;
	}
	
	public int getValeur(int i,int j) {
		return grille[i][j];
	}
	public int getTaille() {
		return taille;
	}
	public int getLigne0() {
		return ligne0;
	}
	
	public int getColonne0() {
		return colonne0;
	}

	@Override
	public String toString() {
		return afficherGrille(grille); 
				
	}
	
	
}
