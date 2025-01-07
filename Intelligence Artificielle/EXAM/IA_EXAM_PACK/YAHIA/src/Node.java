

import java.util.Random;

class Node {

    
    private int q[];  
    private int boardSize;
    private int cost;
    
    Random randomGenerator = new Random();

    public Node(int boardSize) {
     
        this.boardSize = boardSize;
        q = new int[boardSize];
        cost = 0;
        
        for (int i = 0; i < boardSize; i++) {
            q[i] = randomGenerator.nextInt(boardSize);
        
        }
           
        calculateCost();
        
    }

    public Node(int boardSize, int q[]) {
        this.boardSize = boardSize;
        this.q = new int[boardSize];
        System.arraycopy(q, 0, this.q, 0, boardSize);
        cost = 0;
        calculateCost();
    }



    public int getCost() {
       
        return cost;
    }

    public int[] getQueens() {
        return q;
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
   
     
    public Node getBestNeighbor() {
   
   Node bestNode = new Node(boardSize, q);
   int copy_q[] = new int[boardSize];

   for (int i = 0; i < boardSize; i++) {
   	 for (int j = 0; j < boardSize; j++)
   	 {
   		 
   		 System.arraycopy(this.q, 0, copy_q, 0, boardSize);
   		 if(copy_q[i]==j) continue; 
     	 copy_q[i] = j;
   		 Node node = new Node(boardSize, copy_q);
  
   		 if(node.getCost() < bestNode.getCost())
   		 { 
   			 bestNode = new Node(boardSize,copy_q);
   		   }
   	 }
   }
       return bestNode; 
      
    }
    
     public Node getRandomNeighbor() {
   
   int index = randomGenerator.nextInt(boardSize);
    int copy_q[] = new int [boardSize] ;
   System.arraycopy(this.q, 0, copy_q, 0, boardSize);
   int rVal =0;
   do
   {
   	rVal  = randomGenerator.nextInt(boardSize);
   }while(rVal ==copy_q[index]);
    copy_q[index] = rVal;
    return new Node(boardSize, copy_q); 
    }
 
    
    
    
    
 @Override
	public String toString() {
       String resultat = "Total Cost of " + this.getCost();
     
        int q[] = this.q;
       
       resultat +="\n";
        
        for (int i = 0; i < boardSize; i++) {
       
            for (int j = 0; j < boardSize; j++) {
           
               if (q[j] == i) {
                    resultat += "Q\t";
                } else {
                	resultat += "*\t";
                }
            }


            resultat +="\n";
        }
        return resultat;
    }
    
    
}
