import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception{

   
    int boardSize = 4;

	System.out.println("N = " + boardSize);
	int [] t1 = {0,3,0,2};
	int [] t2 = {2,0,2,1};
	int [] t3 = {2,0,1,0};
	int [] t4 = {3,1,1,3};
	
	
	ArrayList<Chromosome> pop = new ArrayList<Chromosome>();
	pop.add(new Chromosome(4,t1));
	pop.add(new Chromosome(4,t2));
	pop.add(new Chromosome(4,t3));
	pop.add(new Chromosome(4,t4));
	
	GeneticAlgo solver = new GeneticAlgo(pop);
	solver.solve();
	
	/*System.out.println("N = " + boardSize);
	GeneticAlgo solver = new GeneticAlgo(boardSize);
	solver.solve();
	solver.best.show();*/


         

    }
}
