package RL;

public class SimulatedAnnealing extends LocalSearchSolver {

    int temperature;

    public SimulatedAnnealing(int boardSize, int temperature) {
        super(boardSize);
        this.temperature = temperature;
        best = new Node(boardSize);
    }

    //@Override
    public void simulatedAnnealing() {
     //TODO


    }
}
