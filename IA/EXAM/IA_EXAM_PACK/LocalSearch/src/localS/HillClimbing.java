
package  localS;

public class HillClimbing extends SolverNQueens {

    
    public HillClimbing(int boardSize) {
        super(boardSize);
        best = new Node(boardSize);
    }

    @Override
    public void solve() {
     //TODOS

        Node next = best.getBestNeighbor();

        while ( next != null && next.getCost() < best.getCost() ) {
         best = next;
         next = best.getBestNeighbor();

     }

    }
}
