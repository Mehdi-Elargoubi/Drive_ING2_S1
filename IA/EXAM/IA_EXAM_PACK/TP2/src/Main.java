import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // int[][] matrice = {{0,0,0,0,0}, {0,0,0,0,0}, {0,0,0,0,0}, {0,1,0,0,0}, {1,2,0,2,0}};
        // Noeud n = new Noeud(true, matrice);
        // System.out.println(n);
        // System.out.println("troisPionsPossiblesLigne(true) = " + n.troisPionsPossiblesLigne(true));
        // System.out.println("troisPionsPossiblesLigne(false) = " + n.troisPionsPossiblesLigne(false));
        // System.out.println("troisPionsPossiblesColonne(true) = " + n.troisPionsPossiblesColonne(true));
        // System.out.println("troisPionsPossiblesColonne(false) = " + n.troisPionsPossiblesColonne(false));
        // n.evaluer();
        // System.out.println("evaluer = " + n.getH());

        Puissance3 jeu = new Puissance3();

        Scanner scanner = new Scanner(System.in);


        System.out.println("Choisir le joueur a commencer( 1 Humain ou 0  Ordi) :");
        int joueur = scanner.nextInt();
        boolean typeJ = (joueur == 1) ? true : false;

        while(!jeu.estFinJeu(typeJ,jeu.getMatriceJeu()) && !jeu.estFinJeu(!typeJ,jeu.getMatriceJeu())){
            if(typeJ){
                System.out.println("Entrer le numero de la colonne : ");
                int colonne = scanner.nextInt();
                while(!jeu.jouer(typeJ,colonne,jeu.getMatriceJeu())){
                    System.out.println("Case non dispo veuillez entrer une autre colonne : ");
                    colonne = scanner.nextInt();
                }
                typeJ = !typeJ;
                System.out.println(jeu);


            }else {
                Noeud n = new Noeud(typeJ, jeu.getMatriceJeu());
                Coup meilleurCoup = jeu.alpha_beta(n,Integer.MIN_VALUE,Integer.MAX_VALUE,4);
                jeu.jouer(typeJ,meilleurCoup.getColonne(), jeu.getMatriceJeu());
                typeJ = !typeJ;
                System.out.println(jeu);

            }

        }

    }

}