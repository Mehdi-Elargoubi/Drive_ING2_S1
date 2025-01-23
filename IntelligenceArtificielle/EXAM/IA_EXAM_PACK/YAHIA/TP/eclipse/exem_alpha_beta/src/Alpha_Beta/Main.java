package Alpha_Beta;

import java.util.Arrays;
import java.util.Scanner;


////veuillez ignorer les commentaire qui sont a l' interieur des methodes c'etaient juste pour les debugages
//le code est le meme que j'ai fait en tp alpha beta et a chaque methode je vais mentionner en commentaires 
//les changement que j'ai fait dans chaque methode


// c'est le meme main sauf que j'ai adapté le cas d'egalité // et la grille de depart qui vide 3x3

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

		
		
	int m[][]= {{0,0,0},{0,0,0},{0,0,0}};
		Node racine = new Node(true,m);
		
		Puissance3 p3=new Puissance3(m);

		int choixLigne = 0;
		int choixColonne = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("grille de depart :\n");
		System.out.println(p3);
		System.out.println("\nRemarque : position (0,0) en bas a gauche et (2,2) en haut a droite\n");
	do{
		p3.afficher_matrice(p3.getMatriceJeu(),3,3);
		 racine = new Node(true,p3.getMatriceJeu());
		 
			Coup c = p3.alpha_beta(racine, Integer.MIN_VALUE, Integer.MAX_VALUE, 10);
			racine.setH(c.getEval());
			System.out.println("la pisition que l'IA à joué est : ("+c.getColonne()+","+c.getLigne()+")\n\n");		
			p3.jouer(true, c.getLigne(), c.getColonne(), p3.getMatriceJeu());
			System.out.println(p3);
			
			if(p3.estFinJeu(true,p3.getMatriceJeu())) 
			{	
				if(p3.gagner(true,p3.getMatriceJeu()))
					System.out.println( "L'IA a gagné  !\n");
				else
					System.out.println( " égalité  !\n");
				break;
			}
			
		 
		 System.out.println("\nVeuillez donner vos choix ( la ieme position(ligne) et jieme position(colonne) ):\n");
			String strLigne = sc.nextLine();
			choixLigne = Integer.parseInt(strLigne);
			System.out.println("\nplayer line choice : "+choixLigne);
			
			String strColonne = sc.nextLine();
			choixColonne = Integer.parseInt(strColonne);
			System.out.println("\nplayer colomn choice : "+strColonne);
			/*boolean tester = */p3.jouer(false, choixLigne, choixColonne, p3.getMatriceJeu());
			//System.out.println(tester);
			//if(!tester)break;
			System.out.println(p3);
			
			if(p3.estFinJeu(false,p3.getMatriceJeu())) 
			{
				if(p3.gagner(false,p3.getMatriceJeu()))
					System.out.println( " Vous avez gagné  !\n");
				else
					System.out.println( " égalité  !\n");
				break;
			}

	
		
	
		}while(true);

	}

}