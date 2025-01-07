
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
            int[][] matrice = {
                {2, 1, 0},
                {2, 1, 0},
                {0, 1, 0},
        };
  
        Noeud nd = new Noeud(matrice, true);

        Morpion m = new Morpion();
        //Coup coup = p3.alpha_beta(nd,-10000,10000,2);
        //System.out.println(coup);

        int[][] cp= new int[3][3];

        Scanner sc = new Scanner(System.in);
        Noeud n =  new Noeud(m.getMatriceJeu(),true);
        int player = 1;
        while (!m.finJeu()) {
            clearConsole();
            System.out.println(m);
            if(player==1){
                m.copieMatrice(m.getMatriceJeu(),cp);
              Morpion m1=new Morpion(m.getMatriceJeu());
                Coup c = m1.alpha_beta(new Noeud(cp,true),Integer.MIN_VALUE ,Integer.MAX_VALUE,6);
                System.out.println(c.getEval()+", x="+c.getX()+", y="+c.getY());
                m.jouer(true,c.getX(),c.getY());
                System.out.println(m);
                player =2;
                if(n.gagne(true)){
                    System.out.println("IA a gagné");
                }
            }
            else{
              
                System.out.print("Votre coup X: ");
                int x = sc.nextInt();
               System.out.print("Votre coup Y: ");
               int y = sc.nextInt();
                m.jouer(false, x,y);
                System.out.println(m);
                player=1;
                if(n.gagne(false)){
                    System.out.println("Vous avez gagné...");
                }
            }
        }
    }

    public  static void clearConsole() {
        try {

            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            // Handle any exceptions.
            System.out.println(e.getLocalizedMessage());

        }
    }

}