public class Main {
    public static void main(String[] args) {
        int [][] grille={{7,2,4},{5,0,6},{8,3,1}};
        Noeud n1 = new Noeud(grille,null,3,0);
        System.out.println("");
        System.out.println(n1);
        System.out.println(n1.calculerH1());
        System.out.println(n1.calculerH2());
        System.out.println(n1.successeur());
    }

}