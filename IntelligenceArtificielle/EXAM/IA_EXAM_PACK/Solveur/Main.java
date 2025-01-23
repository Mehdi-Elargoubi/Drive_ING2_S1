

public class Main {
    public static void main(String[] args) {
        String[][] matrice = {{"X","O","O"},{"O","-","O"},{"O","O","X"}};
        Noeud noeud = new Noeud(true,matrice);
        Solveur solveur = new Solveur();
        Coup coup = solveur.alph_beta(noeud,Integer.MIN_VALUE,Integer.MAX_VALUE,10);

        System.out.println(coup.getLigne()+" "+coup.getColonne());
    }

}
