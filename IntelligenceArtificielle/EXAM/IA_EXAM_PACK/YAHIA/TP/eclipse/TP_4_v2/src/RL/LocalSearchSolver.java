package RL;

public class LocalSearchSolver  {
 
	private int boardSize;
	

	protected Node best;
    
    public LocalSearchSolver(int boardSize) {
        this.boardSize = boardSize ;
        best = new Node(boardSize);
    }
    

	public int getBoardSize() {
		return boardSize;
	}

	public Node getBest() {
		return best;
	}


    public void hillClimbing() {
    	Node nC = new Node(boardSize);
    	Node bestN = null;
    	
    	System.out.println(nC);
    	
    	while(true) {
    		//System.out.println("hi");
    		bestN=nC.getBestNeighbor();
    		if(bestN.getCost()<nC.getCost())
    			nC=bestN;
    		else
    			break;
    	}
    	
    	//System.out.println("\n"+bestN.getCost()+"\n");
    	//System.out.println(nC.getCost());
    	this.best = nC;
    }
    
    public void simulatedAnnealing(int T0) {
    	Node nC = new Node(boardSize);
    	Node bestN = null;
    	int t = T0, deltaE;
    	
    	System.out.println(nC);
    	
    	while(t>0) {
    		//System.out.println("hi");
    		bestN=nC.getRandomNeighbor();
    		deltaE = bestN.getCost()-nC.getCost();
    		if(deltaE<0) {
    			nC=bestN;
    			if(nC.getCost()<best.getCost())
    				best = new Node(this.boardSize,nC.getQueens());
    		}
    		else {
    			if(Math.random()<Math.exp(-1*Math.abs(deltaE)/t)) {
    				nC = bestN;
    			}
    		}
    			
    		t--;
    	}
    	
    	//System.out.println("\n"+bestN.getCost()+"\n");
    	//System.out.println(nC.getCost());


    }
}