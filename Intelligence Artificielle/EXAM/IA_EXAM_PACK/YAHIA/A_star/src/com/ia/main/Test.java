package com.ia.main;

import java.util.ArrayList;
import java.util.List;


import com.ia.astar.Grille;
import com.ia.astar.Noeud;

import com.ia.astar.Solveur;

public class Test {

	public static void main(String[] args) {
		int[][] grille={{4,3,5},{1,0,6},{7,8,2}};// tableau initial
		Grille g = new Grille(grille);
		//System.out.println(g);
		
		
		
		Noeud n = new Noeud(g);
		int e = n.h1();
		List<Grille> r = new ArrayList<Grille>();
		r = n.successeurs();
		for (Grille grille2 : r) {
			//System.out.println(grille2);
		}
		
        // créer un nouveau board à partir d'un fichier
			Solveur s = new Solveur();
			Grille g2  = s.chargerFichier("./puzzles/puzzle19.txt");
	      	System.out.println(g2);
	        // résoudre le puzzle
	      	s.algoAstar(g2);
     		System.out.println("res");
	        // imprimer la solution si elle existe
	      	List<Grille> b = s.cheminComplet();
	      	for (Grille board : b) {
				System.out.println(board.toString());
	      	}
		
	}

}
