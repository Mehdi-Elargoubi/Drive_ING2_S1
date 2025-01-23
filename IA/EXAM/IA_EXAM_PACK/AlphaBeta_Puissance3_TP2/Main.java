public class Main {
    public static void main(String[] args) {
        int[][] m = new int[5][5];

        m[0][0] = 1;
        m[0][1] = 2;
        m[0][3] = 2;
        m[1][1] = 1;
        Noeud n = new Noeud(true,m);
        System.out.println(n);
        System.out.println("troisPionsPossiblesLigne(true)=" + n.troisPionsPossiblesLigne(true));
        System.out.println("troisPionsPossiblesLigne(false)=" + n.troisPionsPossiblesLigne(false));
        System.out.println("troisPionsPossiblesColonne(true)=" + n.troisPionsPossiblesColonne(true));
        System.out.println("troisPionsPossiblesColonne(false)=" + n.troisPionsPossiblesColonne(false));
        System.out.println("evaluation=" + n.evaluer());

    }

}
