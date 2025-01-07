

public class LocalSearchSolver  {
 
	private int boardSize;
	

	private Node best;
    
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
    
    
    }
    
    public void simulatedAnnealing() {
      


    }
}