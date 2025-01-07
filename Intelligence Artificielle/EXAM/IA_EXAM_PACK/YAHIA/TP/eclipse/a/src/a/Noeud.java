package a;

import java.util.ArrayList;
import java.util.List;

public class Noeud {

	private Grille grille;
	private Noeud pere ;
	private int g; // c'est le cout du changement
	
	public Noeud() {
		// TODO Auto-generated constructor stub
	}
	
	public Noeud(Grille grille, Noeud pere, int g) {
		super();
		this.grille = grille;
		this.pere = pere;
		this.g = g;
	}
	
	
	public Noeud(Grille grille) {
		super();
		this.grille = grille;
	}

	public int h1() {
			int valCase = 1;
			int nbr= 0;
		for (int i = 0; i < grille.getTaille(); i++) {
			
			for (int j = 0; j < grille.getTaille(); j++) {
				if(i == grille.getTaille()-1 && j == grille.getTaille() -1) continue;
				 if( grille.getValeur(i, j) != valCase) {
						nbr = nbr +1;
				}
				valCase = valCase+1;
			}
		}
		return nbr;
	}
	
	public int h2() {
		
		int k=1, i1, j1,res=0;
		for (int i = 0; i < grille.getTaille()-1; i++) {
			for (int j = 0; j < grille.getTaille()-1; j++) {
				int nb = grille.getValeur(i, j);
				if (nb != k) {
					i1 = nb/(grille.getTaille()-1);
					j1 =  nb%(grille.getTaille()-1);
					res = Math.abs(i-i1) + Math.abs(j-j1);
				}
			}
			k = k + 1;
		}
		
		return res;
	}
	public int f() {
		int res = g()+h1();
		return res ;
	}
	
	public boolean estUnEtatFinal() {
		if (h1() == 0) {
			return true;
		}
		return false;
	}
	
	
	public List<Grille> successeurs(){
			List<Grille> list = new ArrayList<Grille>();
			if(grille.getLigne0() -1 >= 0) {
				int [][]grille1 = grille.copier();
				grille1[grille.getLigne0()][grille.getColonne0()] = grille.getGrille()[grille.getLigne0()-1][grille.getColonne0()];
				grille1[grille.getLigne0()-1][grille.getColonne0()]=0;
				Grille grille2 = new Grille(grille1);
				list.add(grille2);
			}
			if(grille.getLigne0() + 1 < grille.getTaille()) {
				int [][]grille1 = grille.copier();
				grille1[grille.getLigne0()][grille.getColonne0()] = grille.getGrille()[grille.getLigne0()+1][grille.getColonne0()];
				grille1[grille.getLigne0()+1][grille.getColonne0()]=0;
				Grille grille2 = new Grille(grille1);
				list.add(grille2);
			}
			if(grille.getColonne0() -1 >= 0) {
				int [][]grille1 = grille.copier();
				grille1[grille.getLigne0()][grille.getColonne0()] = grille.getGrille()[grille.getLigne0()][grille.getColonne0()-1];
				grille1[grille.getLigne0()][grille.getColonne0()-1]=0;
				Grille grille2 = new Grille(grille1,grille.getTaille(),grille.getColonne0() - 1 , grille.getLigne0());
				list.add(grille2);
			}
			
			if(grille.getColonne0() +1 < grille.getTaille()) {
				int [][]grille1 = grille.copier();
				grille1[grille.getLigne0()][grille.getColonne0()] = grille.getGrille()[grille.getLigne0()][grille.getColonne0()+1];
				grille1[grille.getLigne0()][grille.getColonne0()+1]=0;
				Grille grille2 = new Grille(grille1,grille.getTaille(),grille.getColonne0() + 1 , grille.getLigne0());
				list.add(grille2);
			}
		
		return list;
	}
	
	public Grille getGrille() {
		return grille;
	}
	public Noeud getPere() {
		return pere;
	}
	public int g() {
		return g;
	}

	
	
	
}
