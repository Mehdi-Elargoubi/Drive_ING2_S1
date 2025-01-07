package com.ia.astar;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solveur {

	    private List<Noeud> liste_noeuds_ouverts = new ArrayList<Noeud>(); // liste des noeuds ouverts (à explorer)
	    private List<Noeud> liste_noeuds_fermes= new ArrayList<Noeud>();
	    
	    public Noeud algoAstar(Grille initial) {
	    	
	    	Noeud courant = new Noeud(initial);
	    	
	    	int[][] solution = {{1,2,3},{4,5,6},{7,8,0}}; 
	    	
	    	Grille test = new Grille(solution);
	    	
	    	Noeud NoeudFinal = new Noeud(test); 
	    	
	    	liste_noeuds_ouverts.add(courant);
	    	while(!courant.estUnEtatFinal() && !liste_noeuds_ouverts.isEmpty())
	    	{
	    		courant = meilleurNoeud(liste_noeuds_ouverts);
	    		liste_noeuds_fermes.add(courant);
	    		liste_noeuds_ouverts.remove(courant);
	    		
	    		for(Grille b:courant.successeurs())
	    		{
	    			
	    			if(existe(b, liste_noeuds_fermes))continue;
	    			
	    			Noeud n = new Noeud(b, courant, courant.g()+1);
	    			
	    			int i = getIndex(b, liste_noeuds_ouverts);
	    			if(i == -1)liste_noeuds_ouverts.add(n);
	    			else if(n.f() < liste_noeuds_ouverts.get(i).g())
	    			{
	    				liste_noeuds_ouverts.set(i, n);
	    			}
	    		}
	    	}
			return NoeudFinal;
	    	
	    } 
	    
	    public Noeud meilleurNoeud(List<Noeud> openList){
			int min = 10000;
			Noeud n= new Noeud(null, null, 0);
			
			for (int i = 0; i < openList.size(); i++) {
				if (min >= openList.get(i).f()) {
					min = openList.get(i).f();
					n=openList.get(i);
				}
			}
			return n;
		}
	    public boolean existe(Grille b, List<Noeud> openList){
			for (int i = 0; i < openList.size(); i++) {
				if(b.equals(openList.get(i).getGrille()))
					return true;
			}
			return false;
		}
		
		public int getIndex(Grille b, List<Noeud> openList){
			for (int i = 0; i < openList.size(); i++) {
				if(b.equals(openList.get(i).getGrille()))
					return i;
			}
			return -1;
		}
	    public List<Grille> cheminComplet(){
	    	List<Grille> b= new ArrayList<Grille>();
	    	Noeud n = liste_noeuds_fermes.get(liste_noeuds_fermes.size()-1);
	    	while (n != null) {
				b.add(n.getGrille());
				n = n.getPere();
			}
	    	
	    	Collections.reverse(b);
	    	return b;
	    }
	    public Grille chargerFichier(String fileName) {
	        int[][] tableau = null;
	        try {
	        	FileInputStream inputStream = new FileInputStream( fileName ) ;
	            Scanner scanner = new Scanner(inputStream);
	            Scanner sc = new Scanner(scanner.nextLine());
	            int N = sc.nextInt();		
	            tableau = new int [N][N];
	            int i = 0;
	            while (scanner.hasNext()) {
	                sc = new Scanner(scanner.nextLine());
	                int j = 0;
	                while (sc.hasNext()) {
	                    tableau[i][j] = sc.nextInt();
	                    j++;
	                }
	                i++;
	            }
	            sc.close();
	        } catch (FileNotFoundException exception) {
	            System.out.println("File not found");
	        }
	        return new Grille(tableau);
	    }
	    
	    
	    
}
