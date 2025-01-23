package Alpha_Beta;
// veuillez ignorer les commentaire qui sont a l' interieur des fonctions c'etaient juste pour les debugages
// pour jouer un coup il faut ajouter un attribut ligne/ car le jeu tic tac toe on joue a n'importe quelle case vide
public class Coup {
	int eval;
	int colonne;
	int ligne;
	
	public Coup(int val, int c, int l) {
		eval=val;
		colonne=c;
		ligne=l;
	}

	public int getEval() {
		return eval;
	}

	public int getColonne() {
		return colonne;
	}
	public int getLigne() {
		return ligne;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
