
package  localS;

public class Main {

    public static void main(String[] args) throws Exception{

		/*int q[] = {1,3,1,2};
		Node n = new Node(4,q);
		n.show();
		Node bestN = n.getBestNeighbor();
		bestN.show();

		Node aleaN = n.getRandomNeighbor();
		aleaN.show();*/


	long time;
	SolverNQueens nq;
	   


	System.out.println("N = " + 8);
	System.out.println("Hill Climbing approach");
	nq = new HillClimbing(8);
	nq.best.show();
	time = System.currentTimeMillis();
	nq.solve();
	time = System.currentTimeMillis()-time;
	nq.best.show();
	System.out.println("Total Time taken :" + time);
	
	System.out.println("Simulated Annealing approach");
	nq = new SimulatedAnnealing(8,1000);
	nq.best.show();
	time = System.currentTimeMillis();
	nq.solve();
	time = System.currentTimeMillis()-time;
	nq.best.show();
	System.out.println("Total Time taken :" + time);
         

    }
}
