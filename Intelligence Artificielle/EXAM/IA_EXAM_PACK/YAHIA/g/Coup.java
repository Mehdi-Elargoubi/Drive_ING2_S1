package Alpha_Beta;

public class Coup {
	
	//sera appeler par AlphaBeta 
	private int eval; //evaluation du noeud par evaluer
	private int colonne; //num de meilleur noeud a jouer
	
	
	
	public Coup(int val, int c) {
		eval = val;
		colonne = c;
	}



	public int getEval() {
		return eval;
	}



	public void setEval(int eval) {
		this.eval = eval;
	}



	public int getColonne() {
		return colonne;
	}



	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	
	

}
