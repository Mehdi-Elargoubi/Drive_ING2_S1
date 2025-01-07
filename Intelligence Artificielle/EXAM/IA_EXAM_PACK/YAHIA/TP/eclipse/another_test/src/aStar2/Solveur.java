package aStar2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solveur {
	ArrayList<Noeud> liste_noeuds_ouverts;
	ArrayList<Noeud> liste_noeuds_fermes;
	
	/*
	public Grille chargerFichier(String nomFichier){
		int t;
		String taille;
		 try {
		      File myObj = new File("/puzzles/filename.txt");
		      Scanner myReader = new Scanner(myObj);
		      if(myReader.hasNext()) {
		    	  taille = myReader.next();
		      }
		      t = Integer.parseInt(taille);
		      int mat[][] = new int[t][t];
		      while (myReader.hasNext()) {
		        String data = myReader.next();
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		
		Grille grille = new Grille(mat);
		
		return grille;
	}
	*/
	public static Grille chargerFichier(String nomFichier) {

        int[][] grille = null;
        try {
            File f = new File(nomFichier);
            Scanner scanner = new Scanner(f);

            // la première ligne : taille du puzzle
            Scanner sc = new Scanner(scanner.nextLine());
            int N = sc.nextInt();

            grille = new int[N][N];
            int i = 0;
            while (scanner.hasNext()) {
                sc = new Scanner(scanner.nextLine());
                int j = 0;
                while (sc.hasNext()) {
                    grille[i][j] = sc.nextInt();
                    j++;
                }
                i++;
            }

            // les autres lignes la grille du puzzle
            sc.close();
            scanner.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found " + exception.getMessage());
        }
        assert grille != null;
        return new Grille(grille);
    }
	
	
	public ArrayList<Noeud> successeurs(ArrayList<Grille> g, Noeud p){
		ArrayList<Noeud> succ = new ArrayList<Noeud>();
		for(Grille x : g) {
			succ.add(new Noeud(x, p, p.g+1));
		}
		return succ;
	}
	
	public Noeud existe(ArrayList<Noeud> l, Noeud s) {
		Noeud exist = null;
		for(Noeud n : l) {
			if(l.contains(s)) {
				exist = n;
			}
		}
		return exist;
	}
	
	public Noeud algoAstar(Grille init) {
		ArrayList<Noeud> liste_noeuds_courant_succ = null;
		Noeud noeud_init = new Noeud(init, null, 0);
		Noeud noeudInetermediaire = null;
		Noeud noeudCourant = noeud_init;
		liste_noeuds_ouverts = new ArrayList<Noeud>();
		liste_noeuds_fermes = new ArrayList<Noeud>();
		liste_noeuds_ouverts.add(noeud_init);
		//noeudCourant
		Integer myInf = Integer.MAX_VALUE;
		int min=myInf;
		
		while(!liste_noeuds_ouverts.isEmpty() && !noeudCourant.estUnEtatFinal()) {
			/*System.out.println("---------------------------------------------------------------------------------");
			System.out.println("\n--------select best node from open list-------\n");
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("liste des noeuds ouverts : " + liste_noeuds_ouverts + "\n\n");*/
			min=myInf;
			for(Noeud node : liste_noeuds_ouverts) {
				//System.out.println(min + " " +node.g());
				if(node.f()<min) {
					min=node.f();
					noeudCourant = node;
					//System.out.println("Oui");
				}
			}
			
			liste_noeuds_fermes.add(noeudCourant);			
			liste_noeuds_ouverts.remove(noeudCourant);
			
			liste_noeuds_courant_succ = this.successeurs(noeudCourant.successeurs(), noeudCourant);
			/*
			System.out.println("\n--------nombre les successeurs-------\n");
			
			System.out.println("---");
			System.out.println(liste_noeuds_courant_succ.size() + "\n");
			System.out.println(liste_noeuds_courant_succ);
			System.out.println("---");
			
			System.out.println("\n--------traiter les successeurs-------\n");*/
			for(Noeud n : liste_noeuds_courant_succ) {
				if(liste_noeuds_fermes.contains(n)) {
					//System.out.println("\n noeud existant dans la liste des fermes\n");
					continue;
				}
				
				if(liste_noeuds_ouverts.contains(n)) {
					//System.out.println("\n noeud existant dans la liste des ouverts");
					noeudInetermediaire = this.existe(liste_noeuds_ouverts, n);
					if(n.f() < noeudInetermediaire.f())
						
						liste_noeuds_ouverts.add(n);
						liste_noeuds_fermes.add(noeudInetermediaire);
						liste_noeuds_ouverts.remove(noeudInetermediaire);
				}else {
					//System.out.println("\nnouveau noeud");
					liste_noeuds_ouverts.add(n);
				}
				n.setPere(noeudCourant);
				
			}
		}
		if(liste_noeuds_ouverts == null) return null;//--
		//if(liste_noeuds_ouverts.isEmpty() && !noeudCourant.estUnEtatFinal()) return null;
		return noeudCourant;
	}
	
	public ArrayList<Grille> cheminComplet(Noeud noeudFinal) {
		ArrayList<Grille> chemin = new ArrayList<Grille>();
		Noeud n=noeudFinal;
		while(n.g != 0) {
			n.getGrille().toString();
			chemin.add(n.getGrille());
			n = n.getPere();
		}
		chemin.add(n.getGrille());
		Collections.reverse(chemin);
		return chemin;
	}
	
	public static void main(String args[]) {
		Grille etatInitial = chargerFichier("C:/Users/Youssef/Desktop/_2022_2023_/2_IA/TP/puzzles/puzzle05.txt");
		System.out.println("Etat initial : ");
		System.out.println(etatInitial);
		
		Solveur solveur = new Solveur();
		
		Noeud noeudFinal = solveur.algoAstar(etatInitial);
		System.out.println("\nResultat final : \n" + noeudFinal);
		if(noeudFinal == null) {
			System.out.println("Pas de solution possible");
		}else {
			System.out.println("Nombre de mouvements minimal: G = " + noeudFinal.g());
			System.out.println("************A Star Algorithm************");
			List<Grille> finalPath = solveur.cheminComplet(noeudFinal);
			for (Grille g : finalPath)
				System.out.println(g);
		}
		
	}
}
