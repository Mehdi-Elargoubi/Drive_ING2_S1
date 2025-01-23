


public class Main {

    public static void main(String[] args) throws Exception{


	System.out.println("N = " + 4);
	GeneticAlgo solver = new GeneticAlgo(4);
	solver.solve();
	solver.best.show();
         

    }
}
