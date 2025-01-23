
public class Main {
    public static void main(String[] args){
        int[][] grille = {{2, 0, 5},{1, 6, 3},{4, 7, 8}};
        Noeud n1 = new Noeud(null, 3, 0, grille);
        // System.out.println("Etat initial: ");
        // System.out.println(n1);
        
        Astar A = new Astar();
        System.out.println(" ");
        A.cheminComplet(A.algoAstar(n1));

    }
}