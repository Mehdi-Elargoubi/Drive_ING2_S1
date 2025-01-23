package RL;


public class Main {

    public static void main(String[] args){
	
   
    int q[] = {0,1,0,3}; 

   
    Node n = new Node(4,q);
    
    System.out.println(n);
    
    Node best =  n.getBestNeighbor();
    
    System.out.println(best);
   
    
    Node randomNeihgbor =  n.getRandomNeighbor();
    
    System.out.println(randomNeihgbor);
    
   
     
     

 /*  LocalSearchSolver solver = new LocalSearchSolver(8);
   System.out.println("N = " + 8);
	System.out.println("Hill Climbing approach");
	System.out.println(solver.getBest());
	solver.simulatedAnnealing();
	System.out.println(solver.getBest());*/
  

         

    }
}
