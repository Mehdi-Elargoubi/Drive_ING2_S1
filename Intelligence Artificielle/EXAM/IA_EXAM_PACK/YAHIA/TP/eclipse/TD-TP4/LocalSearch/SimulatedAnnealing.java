

public class SimulatedAnnealing extends SolverNQueens {

    int temperature;

    public SimulatedAnnealing(int boardSize, int temperature) {
        super(boardSize);
        this.temperature = temperature;
        best = new Node(boardSize);
    }

    @Override
    public void solve() {
     //TODO


    }
}
