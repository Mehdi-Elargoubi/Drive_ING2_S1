package modele;
import java.util.ArrayList;

/**classe Situation, elle represente un noeud de l'arbre des etats
 * @author emmanuel adam
 * @version mai 2012*/
public class Situation {

	/**nom de la situation*/
	private String nom;

	/**nb d'instances*/
	private static int nbInstances = 0;

	/**no de l'instance*/
	private int noInstances;

	/**no de colonne*/
	private int noColonne;

	/**indique si l'etat est un etat en mode Max*/
	private boolean max = true;

	/** liste des etats accessibles a partir de l'etat courant*/
	private ArrayList<Situation> successeurs;

	/**indique si l'etat/la situation est une feuille de l'arbre*/
	private boolean feuille;

	/**indique si l'etat/la situation est une feuille définitive de l'arbre
	 * (i.e. impossible de créer des successeurs a cette situation)*/
	private boolean close;

	/**h = heuristique, estimation de la valeur de la situation*/
	private int h;

	/**grille de jeu correspondant a la situation*/
	private int[][] matriceJeu;

	/**constructeur par defaut*/
	public Situation()
	{
		noInstances = nbInstances++;
		nom=""+noInstances;
		h = 0;
		matriceJeu = new int[6][6];
		successeurs = new ArrayList<Situation>();
	}

	/**constructeur initialisant l'estimation h 
	 * @param _h estimation de la situation*/
	public Situation(int _h)
	{
		this();
		h = _h;
	}

	/**constructeur initialisant l'estimation h et le type de noeud
	 * @param _h estimation de la situation
	 * @param _estMax determine si la valeur de la situation doit etre maximisee ou non*/
	public Situation(int _h, boolean _estMax)
	{
		this(_h);
		max = _estMax;
	}

	/**constructeur initialisant l'estimation h, le type de noeud, et la position du noeud
	 * 	@param _h estimation de la situation
	 * @param _estMax determine si la valeur de la situation doit etre maximisee ou non
	 * @param _estFeuille determine si la situation est finale dans l'arbre
	 */
	public Situation(int _h, boolean _estMax, boolean _estFeuille)
	{
		this(_h, _estMax);
		feuille = _estFeuille;
	}

	/**fonction evaluant la situation courante; calcul le 'h'*/
	void evaluer()
	{
		int valeur = -2*troisPionsPossiblesLigne(TypeJoueur.JOUEUR);
		valeur += troisPionsPossiblesLigne(TypeJoueur.MACHINE);
		valeur += -2*troisPionsPossiblesColonne(TypeJoueur.JOUEUR);
		valeur += troisPionsPossiblesColonne(TypeJoueur.MACHINE);

		int ligne = -2*troisPionsAlignesLigne(TypeJoueur.JOUEUR);
		ligne += troisPionsAlignesLigne(TypeJoueur.MACHINE);
		ligne += -2*troisPionsAlignesColonne(TypeJoueur.JOUEUR);
		ligne += troisPionsAlignesColonne(TypeJoueur.MACHINE);

		valeur += ligne;
		
		if(ligne!=0) close = true;

		h = valeur;

//		System.out.println("/////// evaluation de feuille : " + this);
//		FenetreP3 f = new FenetreP3(nom + ":"+h, matriceJeu);
//		f.setVisible(true);
	}

	/**fonction qui retourne la valeur d'une ligne<br>
	 * si 2 pions de type tj alignes et accolles a une case vide -> val = 200 <br>
	 * si 2 pions de type tj alignes et accolles a deux cases vides -> val = 400 <br>
	 * si 1 pion de type tj accolle a une case vide -> val = 30 <br>
	 * si 1 pion de type tj accolle a deux cases vides -> val = 60 
	 * */
	private int troisPionsPossiblesLigne(TypeJoueur tj)
	{
		int retour = 0;
		int valJoueur = tj.getType();
		// balayage des lignes
		StringBuffer strLineB = new StringBuffer();
		for(int i=0; i<5 ; i++)
		{
			strLineB.delete(0, strLineB.length());
			if(matriceJeu[i][5]>0)
				for(int j=0; j<5 ; j++)
				{
					if (matriceJeu[i][j] == 0)
					{
						if ((i==0) || (i>0 && matriceJeu[i-1][j] != 0)) strLineB.append('0');
						else 	strLineB.append('X');
					}
					else
						strLineB.append(matriceJeu[i][j]);
				}

			String danger1 = ("0"+valJoueur)+valJoueur;
			String danger2 = (""+valJoueur)+valJoueur + "0";
			String danger3 = (""+valJoueur)+"0"+valJoueur;
			String strLine = strLineB.toString();
			if(strLine.indexOf(danger1)!=-1) retour += 200;
			if(strLine.indexOf(danger2)!=-1) retour += 200;
			if(strLine.indexOf(danger3)!=-1) retour += 200;
			String possibleDanger1 = ("0"+valJoueur);
			String possibleDanger2 = (""+valJoueur) + "0";
			if(strLine.indexOf(possibleDanger1)!=-1) retour += 30;
			if(strLine.indexOf(possibleDanger2)!=-1) retour += 30;

		}
		return retour;
	}

	

	
	/**fonction qui retourne la valeur d'une colonne<br>
	 * si 2 pions de type tj alignes et accolles a une case vide -> val = 100 <br>
	 * si 1 pion de type tj accolle a une case vide -> val = 30 <br>
	 * */
	private int troisPionsPossiblesColonne(TypeJoueur tj)
	{
		int retour = 0;
		int valJoueur = tj.getType();
		// balayage des lignes
		for(int j=0; j<5; j++)
		{
			boolean contigue=false;
			boolean trouve = false;
			int nbJetonsDansColonne = matriceJeu[5][j];
//			System.out.println(this.nom +  "nb jetons dans la colonne = " + nbJetonsDansColonne);
			if (nbJetonsDansColonne>0)
				for(int i=0; i<5; i++)
				{
					if (matriceJeu[i][j] == 0)
					{
						if(contigue) retour += 200;
						else if(trouve) retour += 30;
						trouve = contigue = false;
					}
					else
						if (matriceJeu[i][j] == valJoueur)
						{
							if (trouve)  {contigue = true; trouve = false;} 
							else trouve = true;
						}
						else
							trouve = contigue = false;
				}
		}

		return retour;
	}


	/**
	 * calcule si 3 pions sont alignes en ligne
	 * @param tj type du joueur dont il faut verifier s'il a trois pions alignes dans une ligne
	 * @return 0 si pas d'alignement, 1000 si trois pions sont alignes
	 * */
	int  troisPionsAlignesLigne(TypeJoueur tj)
	{
		int retour = 0;
		boolean trouve = false;
		int valJoueur = tj.getType();
		// balayage des lignes
		for(int i=0; i<5 && !trouve; i++)
		{
			int contigue = 0;
			trouve = false;
			if(matriceJeu[i][5]>0)
				for(int j=0; j<5 && !trouve; j++)
				{
					if  (matriceJeu[i][j] != valJoueur) contigue = 0;
					else contigue ++;
					trouve = (contigue==3);
				}
		}
		if(trouve)
		{
			retour = 1000;
			feuille = true;
		}
		if(trouve) afficheMatrice();
		return retour;
	}

	/**
	 * calcule si 3 pions sont alignes en colonne
	 * */
	int  troisPionsAlignesColonne(TypeJoueur tj)
	{
		int retour = 0;
		boolean trouve = false;
		int valJoueur = tj.getType();
		// balayage des colonnes
		for(int j=0; j<5 && !trouve; j++)
		{
			int contigue = 0;
			trouve = false;
			if(matriceJeu[5][j]>0)
			for(int i=0; i<5 && !trouve; i++)
			{
				if  (matriceJeu[i][j] != valJoueur) contigue = 0;
				else contigue ++;
				trouve = (contigue==3);
			}
		}
		if(trouve) retour = 1000;
		if(trouve) afficheMatrice();
		return retour;
	}


	/**fonction calculant la valeur de l'etat 
	 * A MODIFIER
	 * @return estimation de la valeur de l'etat courant*/
	public int getHeuristique()
	{
		return h;
	}

	public void addSuccesseur(Situation s)
	{
		successeurs.add(s);
		s.nom = nom+"."+s.nom;
	}



	/**@return vrai si l'etat est une feuille */
	public boolean estFeuille(TypeJoueur tj){
		return feuille || close;
	}



	/**
	 * @param liste the successeurs to set
	 */
	public void setSuccesseurs(ArrayList<Situation> successeurs) {
		this.successeurs = successeurs;
	}

	/**
	 * @param index index of the successeur
	 * @return the successeur no i
	 */
	public Situation getSuccesseurs(int index) {
		Situation retour = null;
		if(successeurs!=null && index<successeurs.size())
			retour = successeurs.get(index);
		return retour;
	}

	/**
	 * @return the successeurs
	 */
	public ArrayList<Situation> getSuccesseurs() {
		return successeurs;
	}

	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(int h) {
		this.h = h;
	}

	/**
	 * @return the matriceJeu
	 */
	public int[][] getMatriceJeu() {
		return matriceJeu;
	}

	/**
	 * @param matriceJeu the matriceJeu to set
	 */
	public void setMatriceJeu(int[][] matriceJeu) {
		this.matriceJeu = matriceJeu;
	}

	/**
	 * @return the estMax
	 */
	public boolean isMax() {
		return max;
	}

	/**
	 * @param estMax the estMax to set
	 */
	public void setMax(boolean estMax) {
		this.max = estMax;
	}

	public String toString()
	{
		String retour = " S"+nom+", h="+h+", est max = "+max+"---";
		if (!successeurs.isEmpty())
		{
			retour += "\n::::: J'ai "+successeurs.size()+" fils :::::";
			for (Situation s:successeurs)
				retour += s.nom + "(" + s.h + ") ; ";			
		}
		retour += "\n ";
		return retour;
	}

	/**affiche la matrice associee a la situation sur la console*/
	private void afficheMatrice()
	{
		String retour = "";
		for(int i=4; i>=0; i--)
		{
			retour += "\n|";
			for(int j=0; j<5; j++)
			{
				retour += matriceJeu[i][j]+"|";
			}
		}
		retour += "\n------------\n";
		System.out.println(retour);
	}


	/**
	 * @return the feuille
	 */
	public boolean isFeuille() {
		return feuille || close;
	}

	/**
	 * @param feuille the feuille to set
	 */
	public void setFeuille(boolean feuille) {
		this.feuille = feuille;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the close
	 */
	public boolean isClose() {
		return close;
	}

	/**
	 * @param close the close to set
	 */
	public void setClose(boolean close) {
		this.close = close;
	}
	/**
	 * @return the noColonne
	 */
	public int getNoColonne() {
		return noColonne;
	}

	/**
	 * @param noColonne the noColonne to set
	 */
	public void setNoColonne(int noColonne) {
		this.noColonne = noColonne;
	}

}



