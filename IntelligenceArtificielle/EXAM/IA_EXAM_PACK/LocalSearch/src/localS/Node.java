package  localS;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import  java.lang.Math;

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
        this.q = q;
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
         //TODO 
        int c=0;

        for (int i = 0; i < boardSize; i++) {
            for (int j = i+1; j < boardSize; j++) {
                if(q[i]==q[j] || Math.abs(i-j) == Math.abs(q[i]-q[j])){
                    c++;
                }
            }
        }

        cost = c;

    }


   //System.arraycopy(q,0,this.a,0,borderSize)

    int [] copyQ(){
        int[]  newQ = new int[boardSize];
        for (int i = 0; i < boardSize; i++) {
            newQ[i] = q[i];
        }
        return  newQ;
    }
     
    public Node getBestNeighbor() {

        //TODO
        // boardSize * (boardSize-1) voisins
        ArrayList<Node> neighbors = new ArrayList<Node>();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {

                if(q[i]!=j){
                    int[]  newQ = copyQ();
                    newQ[i] = j;
                    neighbors.add(new Node(boardSize,newQ));
                }
            }

        }

        //System.out.println("nb nbrs ="+neighbors.size());

        if(neighbors.size()==0){
            return  null;
        }
        Node bestN = neighbors.get(0);

        for (Node n: neighbors) {
            //System.out.println("cst="+n.getCost()+" cst b ="+bestN.getCost());
            if( n.getCost()<bestN.getCost()){
                bestN = n;
            }
        }
        //System.out.println("cont fin ="+bestN.getCost());
    	 return bestN;
      	 
    }
    
    public Node getRandomNeighbor() {
      
         //TODO
        Random r= new Random();
        int i = r.nextInt(boardSize);
        int j = r.nextInt(boardSize);

        while(q[i]==j){
            j = r.nextInt(boardSize);
        }
        int [] newQ = copyQ();
        newQ[i]=j;

        Node n = new Node(boardSize,newQ);
    	
    	 return n;
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
