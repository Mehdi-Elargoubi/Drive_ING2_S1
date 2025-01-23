package Alpha_Beta;

import java.util.Arrays;
import java.util.Scanner;


public class Main {
	
	

	public static void main(String[] args) {
		

	/*	int m[][]= {{1,2,0,2,0},{0,1,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
		Noeud racine = new Noeud(true,m);
		System.out.println(racine);
		
		System.out.println(racine.troisPionsPossiblesLigne(true));
		System.out.println(racine.troisPionsPossiblesLigne(false));
		
		System.out.println(racine.troisPionsPossiblesColonne(true));
		System.out.println(racine.troisPionsPossiblesColonne(false));
		
		racine.evaluer();
		
		System.out.println(racine.getH());*/
		
		/*
		
		int matriceJeu[][]= {{1,2,0,2,0},{0,1,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
	
		Puissance3 p3= new Puissance3(matriceJeu);
		
		p3.jouer(false, 1, p3.getMatriceJeu());
		
		System.out.println(p3);
		
		int matriceFin[][]= {{1,2,2,2,0},{0,1,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
		
		
		if(p3.estFinJeu(false,matriceFin) )
			System.out.println("\n Fin du jeu");
		
		*/

		
		
	int m[][]= {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
		Node racine = new Node(true,m);
		
		Puissance3 p3=new Puissance3(m);

		int choix = 0;
		Scanner sc = new Scanner(System.in);
	
		System.out.println(p3);
	do{
		p3.afficher_matrice(p3.getMatriceJeu(),5,5);
		 racine = new Node(true,p3.getMatriceJeu());
		 
			Coup c = p3.alpha_beta(racine, Integer.MIN_VALUE, Integer.MAX_VALUE, 4);
			racine.setH(c.getEval());
			System.out.println("IA : "+c.getColonne());		
			p3.jouer(true, c.getColonne(), p3.getMatriceJeu());
			System.out.println(p3);
			
			if(p3.estFinJeu(true,p3.getMatriceJeu())) 
			{
				System.out.println( "L'IA a gagné  !\n");
				break;
			}
			
		 
		 System.out.println("Veuillez donner votre choix");
			String str = sc.nextLine();
			choix = Integer.parseInt(str);
			System.out.println("player : "+choix);
			p3.jouer(false, choix, p3.getMatriceJeu());
			System.out.println(p3);
			
			if(p3.estFinJeu(false,p3.getMatriceJeu())) 
			{
				System.out.println( " Vous avez gagné  !\n");
				break;
			}

	
		
	
		}while(true);

	}

}