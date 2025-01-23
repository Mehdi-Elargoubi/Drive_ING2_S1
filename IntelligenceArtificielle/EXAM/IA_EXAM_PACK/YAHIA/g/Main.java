package Alpha_Beta;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public Main() {
		
	}
	
	public static void main(String[] args) {
		
		
		String m[][] = new String[][] { {"X","O","0"}, {"X","O","0"}, {"0","O","0"}};
		
		//String m[][] = new String[][] { {"X","0","X"}, {"0","O","0"}, {"0","0","0"}}; //pour test
		
		Noeud racine = new Noeud(true, m);
		//System.out.println(racine);
		
//		System.out.println("Score:"+racine.troisPionsAlignesLigne(true));
		//System.out.println("Score:"+racine.troisPionsAlignesColonne(true));
		//System.out.println("Score:"+racine.troisPionsAlignesDiagonale(true));
		//System.out.println("Score:"+racine.troisPionsPossiblesLigne(false));
		//System.out.println("Score:"+racine.troisPionsPossiblesColonne(true));
		
		//System.out.println("Evaluer: "+racine.evaluer());
		
		
		alphaBeta p3=new alphaBeta(m);
		int choix = 0;
		Scanner sc = new Scanner(System.in);
	
//		System.out.println(p3);//affichage inverse
//		System.out.println(p3.afficher_matrice(p3.getMatriceJeu(),3, 3));
//		
		
		
		do{
			System.out.println(p3.afficher_matrice(p3.getMatriceJeu(), 3, 3));
			
			racine = new Noeud(true,p3.getMatriceJeu());
			Coup coup = p3.alphabeta(racine, Integer.MIN_VALUE, Integer.MAX_VALUE, 3);//profondeur maximale
			racine.setH(coup.getEval());
			System.out.println("Joueur rond L'IA : "+coup.getColonne()+"-------------------------------");		
			p3.jouer(true, coup.getColonne(), p3.getMatriceJeu());
			System.out.println(p3.afficher_matrice(p3.getMatriceJeu(), 3, 3));
			
			if(p3.estFinJeu(true,p3.getMatriceJeu())) 
			{
				System.out.println( "Le joueur rond  a gagné  !\n");
				break;
			}
			
			
			System.out.println("Veuillez donner votre choix : ");
			String str = sc.nextLine();
			choix = Integer.parseInt(str);
			while(choix>2 || choix<0) {
				System.out.println("Votre choix n'existe pas ");
				System.out.println("Veuillez donner un choix valide : ");
				str = sc.nextLine();
				choix = Integer.parseInt(str);
			}
			System.out.println("Joueur X : "+choix+"-------------------------------");
			p3.jouer(false, choix, p3.getMatriceJeu());
			System.out.println(p3.afficher_matrice(p3.getMatriceJeu(), 3, 3));
			
			if(p3.estFinJeu(false,p3.getMatriceJeu())) 
			{
				System.out.println( " Joueur X a gagné  !\n");
				break;
			}
			
		}while(true);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
