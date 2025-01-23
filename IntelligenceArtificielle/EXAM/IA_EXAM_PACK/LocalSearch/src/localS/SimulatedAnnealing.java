package localS;

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
        Node n0 = best;
        double nb = 0, r = 0;
        int t = temperature;
        while (t > 0) {
            Node n = best.getRandomNeighbor();
            int deltaE = n.getCost() - n0.getCost();
            if (deltaE > 0) {
                n0 = n;
            } else {
                nb = Math.random();
                r = deltaE / (double) t;
                if (nb < Math.exp(r)) {
                    n0 = n;
                }
                System.out.println(nb);
                //System.out.println("t ="+t+" cst ="+n0.getCost()+" rdm_n="+nb+" dlt="+(deltaE)+ "  dlt/t="+r+" exp="+Math.exp(r));

            }

            t-=5;
        }

        best = n0;


    }
}
