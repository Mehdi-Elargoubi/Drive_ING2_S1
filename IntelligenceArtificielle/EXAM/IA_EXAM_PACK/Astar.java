import java.util.ArrayList;
import java.util.Comparator;

public class Astar {
    ArrayList<Noeud> listeOuverte;
    ArrayList<Noeud> listeFermee;

    Astar(){
        listeOuverte = new ArrayList<>();
        listeFermee = new ArrayList<>();
    }

    public Noeud algoAstar(Noeud n){
        ArrayList<Noeud> tmpSuccesseurs = new ArrayList<>();
        Noeud noeudCourant = null;
        int index = -1;

        // 1
        listeOuverte.add(n);

        while (!listeOuverte.isEmpty()) {

            //2
            listeOuverte.sort(Comparator.comparing(Noeud::f1));
            noeudCourant = listeOuverte.get(0);
            
            if (noeudCourant.estUnEtatFinal()) {
                System.out.println("\nprocessing is done\n");
                break;
            }

            // 3
            listeFermee.add(noeudCourant);
            listeOuverte.remove(0);

            // 4
            tmpSuccesseurs.addAll(noeudCourant.successeurs());

            //5
            for (Noeud s : tmpSuccesseurs) {
                if (!listeFermee.contains(s)) {
                    index = listeOuverte.indexOf(s);
                    if (index == -1) listeOuverte.add(s);
                    else if (s.f1() < listeOuverte.get(index).f1()) {
                        listeOuverte.remove(index);
                        listeOuverte.add(s);
                    }
                }
            }

            // tmpSuccesseurs.clear();
            // System.out.println("Liste ouverte: ");
            // for (Noeud noeud : listeOuverte) {
            //     System.out.println(noeud);
            //     System.out.println("---- h1=" + noeud.h1() + " f1=" + noeud.f1());
            // }
            System.out.print("*");
        }
        return noeudCourant;
    }


    public ArrayList<Noeud> cheminComplet(Noeud noeud){
        ArrayList<Noeud> chemin = new ArrayList<>();
        if (noeud.getPere() == null) chemin.add(noeud);
        else {
            while (noeud != null) {
                chemin.add(0, noeud);
                noeud = noeud.getPere();
            }
        }
        for (Noeud noeud2 : chemin) {
            System.out.println(noeud2);
            System.out.println("\n#############\n");
        }
        return chemin;
    }
}