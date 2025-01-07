package tafriz;

import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		

		int m[][]= {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,1,0,0,0},{1,2,0,2,0}};
		Noeud racine = new Noeud(true,m);
		System.out.println(racine);
		
		System.out.println(racine.troisPionsPossiblesLigne(true));
		System.out.println(racine.troisPionsPossiblesLigne(false));
		
		System.out.println(racine.troisPionsPossiblesColonne(true));
		System.out.println(racine.troisPionsPossiblesColonne(false));
		
		racine.evaluer();
		
		System.out.println(racine.getH());
		
		
		//Noeud racine = new Noeud(true,m);
		
		Puissance3 p3=new Puissance3();

		int choix = 0;
		Scanner sc = new Scanner(System.in);
	
		System.out.println(p3);
	do{
		

		 racine = new Noeud(true,p3.getMatrice());
		 
			Coup c = p3.alpha_beta(racine, Integer.MIN_VALUE, Integer.MAX_VALUE, 4);
			racine.setH(c.getEval());
							
			p3.jouer(true, c.getColonne(),p3.getMatrice());
			System.out.println(p3);
			
			if(p3.estFinJeu(true,p3.getMatrice())) 
			{
				System.out.println( "L'IA a gagnÃ©  !\n");
				break;
			}
			
		 
		 System.out.println("Veuillez donner votre choix");
			String str = sc.nextLine();
			choix = Integer.parseInt(str);
					
			p3.jouer(false, choix, p3.getMatrice());
			System.out.println(p3);
			
			if(p3.estFinJeu(false,p3.getMatrice())) 
			{
				System.out.println( " Vous avez gagnÃ©  !\n");
				break;
			}

	
		
	
		}while(true);

	}

}





