import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgo {


private int POPULATION_SIZE;	 // taille de la population			  
private int MAX_ITERATIONS;       // nombre maximum d'itérations		           
private double MUTATION_RATE;     // taux de mutation
private int   OFFSPRING_PER_GENERATION ; // nombre d'enfants générés à chaque génération
private int MIN_SELECT;              // nombre minimum d'individus sélectionné pour générer la prochaine génération


private ArrayList<Chromosome> population; // population

Chromosome best ;    // le meilleur individu de la population

Random randomGenerator = new Random();

public GeneticAlgo(int boardSize) {
 
    
	/* initialisation des paramètres*/
	   
	POPULATION_SIZE = 4;
	MAX_ITERATIONS = 1;
	OFFSPRING_PER_GENERATION = 4;
	MUTATION_RATE = 0.01;
	MIN_SELECT = 4; 
		
	
	/* initialisation de la population avec des chromosomes aléatoires*/
	population = new ArrayList<Chromosome>();
	
	for(int i= 0; i< POPULATION_SIZE;i++)
	{
	  Chromosome c = new Chromosome(boardSize);
	  population.add(c);
	}

	/* trier la population*/
	Collections.sort(population);
}



public void solve() {
	 
	 int iter =0;
	 int boardSize = population.get(0).getMaxLength();
	 best = population.get(0);
	 while(iter <=MAX_ITERATIONS && best.getCost()!=0)
	 {
		 
		 /* calculer  la fonction fitness de tous les individus*/			 
		 calculateFitness();
 
		 /* sélectionner les individus qui participent au croisement*/
		rouletteSelection();
		
		for(Chromosome c : population)
			System.out.println(c);
		
		System.out.println("\n\n");

	 
		for(int i = 0; i < OFFSPRING_PER_GENERATION; i++) {
       

			/* choisir parmi les individus sélectionnés le premier parent */
			Chromosome parent1 = selectParent(null);

			/* choisir pami les individus sélectionnés le deuxième parent qui doit être différent du premier*/
			Chromosome parent2 = selectParent(parent1);
   
			/* initialiser les chromosomes enfants*/
			Chromosome child1 = new Chromosome(boardSize);
			Chromosome child2 = new Chromosome(boardSize);

			/* effectuer le croisement de parent1 et parent2. Le résultat est retourné dans child1 et child2*/
			doCrossover(parent1, parent2, child1, child2);


			/* effectuer une mutation*/
			if (Math.random()<MUTATION_RATE)
			      	doMutation(child1);
			
			if (Math.random()<MUTATION_RATE)
			  		doMutation(child2);


			/* ajouter les enfants à la population*/
			population.add(child1);
			population.add(child2);
       
  
        } 
	 
	 
		 
		 /* trier la population et récupérer le best*/
		 
		 Collections.sort(population);
		 if(best.getCost()>population.get(0).getCost()) iter =0;
		 best = population.get(0);
		 
		 iter ++;
 
		 /*réinitailiser la populatiopn : select = false */
		 reset();
		 
		 
	 }
	 
	 
	 
 }
 
 

 

	public void calculateFitness() {
	
		int populationSize = population.size();
		Chromosome chromo = null;
		double bestScore = 0;
		double worstScore = 0;

		
		worstScore = Collections.max(population).getCost();
    		
		bestScore = Collections.min(population).getCost();

		for(int i = 0; i < populationSize; i++) {
			chromo = population.get(i);
			chromo.setFitness((worstScore - chromo.getCost()) * 100.0 / (worstScore - bestScore));
		}   
		
		
	    double total = 0;
        for(int i = 0; i < populationSize; i++) {												
            total += population.get(i).getFitness();
           
        }
        
        total *= 0.01;															

        for(int i = 0; i < populationSize; i++) {
            chromo = population.get(i);
            chromo.setSelectionProbability(chromo.getFitness() / total);			 
        }
        
	}
	
	
	
	
	
	public void rouletteSelection() {
   	 	int j = 0;
        int populationSize = population.size();
      
        double selTotal = 0.0;
        double rouletteSpin = 0.0;
        Chromosome thisChromo = null;
        Chromosome thatChromo = null;
        boolean done = false;
   
        for(int i = 0; i < MIN_SELECT; i++) {										
            rouletteSpin = randomGenerator.nextInt(100);
            j = 0;
            selTotal = 0;
            done = false;
            while(!done) {
                thisChromo = population.get(j);
                selTotal += thisChromo.getSelectionProbability();
                if(selTotal >= rouletteSpin) {
					 if(j == 0) {
					    thatChromo = population.get(j);
					 } else if(j >= populationSize - 1) {
					     thatChromo = population.get(populationSize - 1);
					 } else {
					     thatChromo = population.get(j-1);
					 }
					thatChromo.setSelected(true);
					done = true;
                } else {
                    j++;
                }
            }
        }
	}
	
	public void reset() {
		
		int populationSize = population.size();
		for(int i = 0; i < populationSize; i++) {
			population.get(i).setSelected(false);;
			
		}   
	}
	
	



	public Chromosome selectParent(Chromosome p) {
		
		//TODO
    	
        Chromosome parent = null;
      
 

        return parent;    	
    }    	
	
	
	
	public void doCrossover(Chromosome parent1, Chromosome parent2, Chromosome child1, Chromosome child2)
	{
		//TODO
	
	}
	

	private void doMutation(Chromosome child1) {
	
	//TODO
		
		
	
}








}
