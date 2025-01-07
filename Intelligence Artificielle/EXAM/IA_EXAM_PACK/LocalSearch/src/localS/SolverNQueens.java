
package  localS;

abstract class SolverNQueens {

    protected int boardSize;
    protected Node best;
    

    public SolverNQueens(int boardSize) {
        this.boardSize = boardSize;
       
        if (boardSize<4)
            throw new UnsupportedOperationException("No of N should be more than 3 to solve the problem");
    }

    

    protected boolean isSolvedPossition(Node s) {
        if (s.getCost() ==0) {
            return true;
        }
        return false;
    }
    
    abstract public void solve();

    public Node getBest() {
        return best;
    }
}
