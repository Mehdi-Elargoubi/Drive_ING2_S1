
import java.util.Random;

public class Chromosome implements Comparable<Chromosome>{
	
	
	private int q[];           /* tableau des reines*/
	private int boardSize;     /* la taille du tableau*/
	private int cost;        /* le nombre de paires de reines qui s'attaquent*/
	private double fitness;				/* l'évaluation du chromosome */		
	private boolean selected;		 /* = true si le chromosome est sélectionné pour la reproduction*/		
	private double selectionProbability;  /* probabilité de selection*/
	
	Random randomGenerator = new Random();
	
	
	public Chromosome(int n) {
		boardSize = n;
		q = new int[boardSize];
		fitness = 0.0;
		cost = 0;
		selected = false;
		selectionProbability = 0.0;
		
		for (int i = 0; i < boardSize; i++) {
	            q[i] = randomGenerator.nextInt(boardSize);
	        
	     }
		
		 calculateCost();
	           
	      
	}
	
	  public Chromosome(int boardSize, int q[]) {
	    	        
	        this.boardSize = boardSize;
	        this.q = new int[boardSize];
	        System.arraycopy(q, 0, this.q, 0, boardSize);
	        cost = 0;
	        calculateCost();
	    }

	public void calculateCost() {
	   	cost =0;

    	for (int i = 0; i < boardSize; i++) {
    		for (int j = i+1; j < boardSize; j++) {
    			if ((q[i] == q[j])||(Math.abs(q[i] - q[j])==Math.abs(i-j)))
    	    	cost++;
    	
    		}
    	}

    }

	
	public int getCost() {
	       
	        return cost;
	    }

	public int[] getQueens() {
	        return q;
	    }
	public int getQueen(int i)
	{
		return q[i];
	}
	
	public void setQueen(int i, int j)
	{
		 q[i]=j;
		 calculateCost();
	}
	
	
	public double getFitness() {
		return fitness;
	}
	
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	
	
	public boolean isSelected() {
		return selected;
	}
	
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
	public double getSelectionProbability() {
		return selectionProbability;
	}
	
	
	public void setSelectionProbability(double selectionProbability) {
		this.selectionProbability = selectionProbability;
	}
	

	public int getMaxLength() {
	   return boardSize;
	}

	@Override
	public int compareTo(Chromosome c) {
		return this.getCost() - c.getCost();
	}
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chromosome other = (Chromosome) obj;
		if (boardSize != other.boardSize)
			return false;
		if (cost != other.cost)
			return false;
		
		return true;
	}

	public String toString() {
	        String s  ="";
	     
	        int q[] = this.q;
	       
	       
	        
	        for (int i = 0; i < boardSize; i++) {
	       
	            s+= " " + this.q[i];
	        }
	        s+=" cost = " + this.getCost() ;
	        s+=" selection fitness = " + this.getFitness() ;
	        s+=" selection proba = " + this.getSelectionProbability() ;
	        s+=" selected = " + this.isSelected() ;
	    
	        return s;
	    }
	
	
	 public void show() {
	        System.out.println("Total Cost of " + this.getCost());
	     
	        int q[] = this.q;
	       
	        System.out.println();
	        
	        for (int i = 0; i < boardSize; i++) {
	       
	            for (int j = 0; j < boardSize; j++) {
	           
	               if (q[j] == i) {
	                    System.out.print("Q\t");
	                } else {
	                    System.out.print("*\t");
	                }
	            }

	            System.out.println();
	        }
	    }
}