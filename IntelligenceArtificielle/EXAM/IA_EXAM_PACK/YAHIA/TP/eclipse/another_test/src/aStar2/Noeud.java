package aStar2;

import java.util.ArrayList;
import java.util.Objects;

public class Noeud {
	@Override
	public String toString() {
		return "Noeud [grille=" + grille + ", g=" + g + "]";
	}


	Grille grille;
	Noeud pere;
	int g;
	
	
	public Noeud(Grille grille, Noeud p, int g) {
		this.grille = grille;
		pere = p;
		this.g = g;
	}


	public Grille getGrille() {
		return grille;
	}


	public Noeud getPere() {
		return pere;
	}
	
	public void setPere(Noeud p) {
		this.pere = p;
	}
	
	public int h() {
		int i, j, bien=1, mal=0;
		for(i=0;i<grille.grille.length;i++) {
			for(j=0; j<grille.grille[i].length; j++) {
				if(bien == grille.taille*grille.taille)continue;
				//if(bien == grille.taille*grille.taille) bien=0;
				//if(grille.grille[i][j]==0)continue;
				if(grille.grille[i][j] != bien) {
					mal+=1;
				}
				bien+=1;
			}
		}
		return mal;
	}


	public int g() {
		return g;
	}
	
	public int f() {
		return g()+h();
	}
	
	
	public boolean estUnEtatFinal() {
		int i,j, bien=1;
		boolean mal = true;
		for(i=0;i<grille.taille;i++) {
			for(j=0;j<grille.taille;j++) {
				if(i==grille.taille-1 && j==grille.taille-1) break;
				if(grille.grille[i][j] != bien) {
					mal = false;
				}
			bien+=1;
			}
		}
		return mal;
	}
	
	public ArrayList<Grille> successeurs(){
		int ligne0, colone0;
		int temp;
		int[][] cpy;
		
		ArrayList<Grille> succ = new ArrayList<Grille>();
		
		ligne0=grille.ligne0;
		colone0=grille.colone0;
		
		if(ligne0!=0) {
			cpy = this.grille.copier();
			temp=cpy[ligne0][colone0];
			cpy[ligne0][colone0]=cpy[ligne0-1][colone0];
			cpy[ligne0-1][colone0]=temp;
			succ.add(new Grille(cpy));
		}
		if(ligne0!=grille.taille-1) {
			cpy = this.grille.copier();
			temp=cpy[ligne0][colone0];
			cpy[ligne0][colone0]=cpy[ligne0+1][colone0];
			cpy[ligne0+1][colone0]=temp;
			succ.add(new Grille(cpy));
		}
		if(colone0!=0) {
			cpy = this.grille.copier();
			temp=cpy[ligne0][colone0];
			cpy[ligne0][colone0]=cpy[ligne0][colone0-1];
			cpy[ligne0][colone0-1]=temp;
			succ.add(new Grille(cpy));
		}
		if(colone0!=grille.taille-1) {
			cpy = this.grille.copier();
			temp=cpy[ligne0][colone0];
			cpy[ligne0][colone0]=this.grille.grille[ligne0][colone0+1];
			cpy[ligne0][colone0+1]=temp;
			succ.add(new Grille(cpy));
		}
		return succ;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Noeud other = (Noeud) obj;
		return Objects.equals(grille, other.grille);
	}


	public void printSucc(ArrayList<Grille> s) {
		
		for(Grille a : s) {
			System.out.println(a.toString());
		}
	}
	
	
	public static void main(String[] args) {
		int [] [] tab = {{7,2,4},{5,0,6},{8,3,1}};
		Grille etatInitial = new Grille(tab);
		System.out.println(etatInitial);
		System.out.println("-----------------------");
		Noeud n = new Noeud(etatInitial, null, 0);
		System.out.println("Valeur de h = " + n.h());
		ArrayList<Grille> SUCCs = n.successeurs();
		//System.out.println(SUCCs);
		n.printSucc(SUCCs);
	}
	
	
}
