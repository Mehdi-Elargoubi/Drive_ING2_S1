package puissance3;

public class Noeud {
	
	private int [][]matrice;
	private boolean max;
	private int h;
	
	public Noeud(boolean max,int[][]matrice) {
		
		this.matrice=matrice;
		this.max=max;
			
	}

	public int[][] getMatrice() {
		return matrice;
	}

	public boolean isMax() {
		return max;
	}

	public int getH() {
		return h;
	}
	
	
	public void setH(int h) {
		this.h = h;
	}

	public String toString() {
		
		
		for (int i = 0; i < matrice.length; i++) { 
            for (int j = 0; j < matrice.length; j++) {
            	 
                System.out.print(matrice[i][j] + "\t"); 
            } 
            System.out.println(); 
        } 
				
	return "";
	}
	 
	 public int troisPionsAlignesLigne(boolean typeJoueur ) {
	        String motif=" ";
	        if(typeJoueur) 
	        	motif="111";
	        else 
	        	motif="222";
	        for (int i = 0; i < matrice.length; i++) {
	            String ligne="";
	            for (int j = 0; j < matrice[i].length; j++) {
	                ligne += matrice[i][j];
	                if(ligne.contains(motif) ){
	                    return 1000;
	                }
	            }
	        }
	        return 0;
	    }
	 
	 public int troisPionsAlignesColonne(boolean typeJoueur) {
	        
		    String motif="";
	       
	        if(typeJoueur) motif="111";
	        else motif="222";
	        
	        for(int i=0;i<matrice.length;i++) {
	        	 String colonne="";
	        for (int j = 0; j < matrice[i].length; j++) {
	            
	            colonne += matrice[j][i];
	            
	            if(colonne.contains(motif)) {
	            	
	                return 1000;
	            }
	        }
	        }
	        return 0;
	    }
	 

	    public int troisPionsPossiblesLigne(boolean typeJoueur) {
	        int resultat=0;
	        int jeton=0;
	        if(typeJoueur) jeton=1;
	        else jeton=2;
	        for (int i = 0; i < matrice.length; i++) {
	            String ligne ="";
	            for(int j=0;j<matrice[i].length;j++) 
	            
	            
	            ligne+=matrice[i][j];
	            
	                if(jeton == 1) {
	                if(ligne.contains("0"+jeton+jeton)) {
	                    resultat = resultat +200;
	                }
	                if(ligne.contains(jeton + "0" + jeton) ) {
	                    resultat = resultat +200;
	                }
	                if(ligne.contains(jeton+jeton+"0")  ){
	                    resultat = resultat +200;
	                }
	                if(ligne.contains(jeton+"0")) {
	                    resultat = resultat +30;
	                }
	                if(ligne.contains("0" +jeton)) {
	                    resultat = resultat +30;
	                }
	            }

	            if(jeton == 2) {
	                if(ligne.contains("0"+jeton+jeton)) {
	                    resultat = resultat +200;
	                }
	                if(ligne.contains(jeton + "0" + jeton) ) {
	                    resultat = resultat +200;
	                }
	                if(ligne.contains(jeton+jeton+"0")  ){
	                    resultat = resultat +200;
	                }
	                if(ligne.contains(jeton+"0")) {
	                    resultat = resultat +30;
	                }
	                if(ligne.contains("0" +jeton)) {
	                    resultat = resultat +30;
	                }
	            }
	        
	        }
	        return resultat;
	    }



	   public int troisPionsPossiblesColonne(boolean typeJoueur) {
	        int resultat=0;
	        int jeton=0;
	        if(typeJoueur) jeton=1;
	        else jeton=2;
	        for (int i=0;i<matrice.length;i++) {
	        	
	        	String colonne="";
	        for (int j = 0; j < matrice[i].length; j++) 
	            
	            colonne+=matrice[j][i];
	 
	                if(colonne.contains("0"+ jeton +jeton )) {
	                    resultat = resultat + 200;
	                }
	                if(colonne.contains("0" + jeton) ) {
	                    resultat = resultat + 30;
	                }
	        
	        }
	        return resultat;
	    }
	   
	    
	    public int evaluer() {
	    	
	    	this.h= -2*troisPionsAlignesLigne(false)
	    			+ troisPionsAlignesLigne(true)
	    			-2*troisPionsAlignesColonne(false)
	    			+ troisPionsAlignesColonne(true)
	    			-2*troisPionsPossiblesLigne(false)
	    			+ troisPionsPossiblesLigne(true)
	    			-2*troisPionsPossiblesColonne(false)
	    			+troisPionsPossiblesColonne(true);
	   
	    
	    return h;
	    }

	

}
