package modele;
import gui.FenetreP3;

import java.util.ArrayList;

import algo.Resolution;



/**
 * classe permettant la gestion du jeu de Puissance3<br>
 * contient la matrice 5x5, modèle du jeu, constituee d'entiers, <br>
 * permet la creation de l'arbre des situations,<br>
 * ainsi que la detection de fin de jeu
 * @author emmanuel adam
 * */
public class Puissance3 {

	/**matrice representant le jeu; le jeu se joue sur une grille 6x6, <br>
	 * la derniere ligne contient le nb d'elt dans les colonnes,<br>
	 * la derniere colonne contient le nb d'elt dans les lignes,
	 * */
	private int[][] matriceJeu;

	/**number of cells in width */
	public  final int WIDTH = 5;

	/**number of cells in height */
	public  final int HEIGHT = 5;

	/**profondeur de l'arbre de recherche*/
	public final int PROFONDEUR_DE_JEU = 4;

	
	/**interface graphique associee au jeu*/
	FenetreP3 gui;
	
	/**constructeur*/
	public Puissance3()
	{
		matriceJeu = new int[HEIGHT+1][WIDTH+1];
		gui = new FenetreP3(this);
		gui.setVisible(true);
	}

	/**
	 * tour de jeu = jeu de l'humain et jeu de l'ordi (par alphabeta)
	 * @param colonne no de colonne jouee
	 * @return vrai si coup autorise
	 * */
	public boolean tourDeJeu( int colonne)
	{
		boolean retour = true;
		assert (colonne >= 0 && colonne < 5): "pb indice de colonne hors limites";
		retour =jeu(TypeJoueur.JOUEUR, colonne);
		if(retour)
		{
			Situation s = new Situation();
			s.setMatriceJeu(matriceJeu);
			creerArbreSituation(s, PROFONDEUR_DE_JEU);
			int bonneVal = Resolution.alphaBeta(s, Integer.MIN_VALUE, Integer.MAX_VALUE);
			s.setH(bonneVal);

			boolean trouve = false;
			ArrayList<Situation> successeurs = s.getSuccesseurs();
			int nbSuccesseurs = successeurs.size();
			Situation goodSituation = null;

			for(int i = 0; i<nbSuccesseurs && !trouve; i++)
			{
				trouve = (successeurs.get(i).getH() == bonneVal);
				if(trouve) goodSituation = successeurs.get(i);
			}

			if(trouve) retour =jeu(TypeJoueur.MACHINE, goodSituation.getNoColonne());

		}
		
		return retour;
	}
	
	/**
	 * action de jeu 
	 * @param joueur type de joueur
	 * @param colonne no de colonne jouee
	 * @return vrai si coup autorise
	 * */
	public boolean jeu(TypeJoueur joueur, int colonne)
	{
		boolean retour = true;
		assert (colonne >= 0 && colonne < 5): "pb indice de colonne hors limites";
		int nb = matriceJeu[HEIGHT][colonne];
		if (nb>4) retour = false;
		else
		{
			matriceJeu[HEIGHT][colonne]++;
			matriceJeu[nb][WIDTH]++;
			matriceJeu[nb][colonne] = joueur.getType();
			gui.updateJeu(matriceJeu);
			Situation s = new Situation();
			s.setNoColonne(colonne);
			s.setMatriceJeu(matriceJeu);
			retour = !isEndOfGame(s, joueur);
		}		
		return retour;
	}
	
	
	/**determine if it is the end of the game.<br>
	 * i.e. if someone win*/
	private boolean isEndOfGame(Situation s, TypeJoueur joueur)
	{
		boolean gain = false;
		String message = "";
		boolean full = true;
		for(int j=0; j<WIDTH && full; j++)
			full = full && (matriceJeu[HEIGHT][j]==6);
		if(full)
			message += "Toute la grille est remplie !!!";
		if (s.troisPionsAlignesColonne(joueur) !=0 )
		{
			gain = true;
			message +=  "Le joueur " + joueur + " a gagne sur une colonne !\n";
		}
		if (s.troisPionsAlignesLigne(joueur) !=0 )
		{
			gain = true;
			message +=  "Le joueur " + joueur + " a gagne sur une ligne !\n";
		}

		if (gain || full)
		{
			javax.swing.JOptionPane.showMessageDialog(gui, message, "fin du jeu!!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
		}
		return gain;
	}
	
	/**
	 * action de jeu sur matrice passee en parametre
	 * @param joueur type de joueur
	 * @param colonne no de colonne jouee
	 * @param matriceJeu matrice de jeu sur laquelle il faut jouer (<> de la vraie matricede jeu)
	 * @return vrai si coup autorise
	 * */
	public boolean jeu(TypeJoueur joueur, int colonne, int[][] matriceJeu)
	{
		boolean retour = true;
		assert (colonne >= 0 && colonne < 5): "pb indice de colonne hors limites";
		int nb = matriceJeu[5][colonne];
		if (nb>4) retour = false;
		else
		{
			matriceJeu[5][colonne]++;
			matriceJeu[nb][5]++;
			matriceJeu[nb][colonne] = joueur.getType();			
		}
		return retour;
	}
	
	
	/**cree un arbre de situation sur 2 nbNiveaux a partir de la situation du jeu courant
	 * @param s situation a partir de laquelle il faut etendre l'arbre
	 * @param nbNiveaux nb de niveaux de l'arbre restants a creer*/
	void creerArbreSituation(Situation s, int nbNiveaux)
	{
		for (int j=0; j<WIDTH; j++)
		{
			int[][] matriceJeuDeduite = new int[HEIGHT+1][WIDTH+1];
			copieMatrice(s.getMatriceJeu(), matriceJeuDeduite);
			TypeJoueur tj = (s.isMax()?TypeJoueur.MACHINE:TypeJoueur.JOUEUR);
			if (matriceJeuDeduite[HEIGHT][j]<HEIGHT)
			{
				if (jeu(tj, j, matriceJeuDeduite))
				{
					Situation sprim = new Situation(0, !s.isMax());
					sprim.setNoColonne(j);
					sprim.setMatriceJeu(matriceJeuDeduite);
					s.addSuccesseur(sprim);
					if (sprim.troisPionsAlignesColonne(tj) !=0 || sprim.troisPionsAlignesLigne(tj)!=0 ) 
					{
						sprim.setClose(true);
						sprim.evaluer();
					}
					else
					{
						if(nbNiveaux>1)
							creerArbreSituation( sprim, nbNiveaux-1);
						else
						{
							sprim.setFeuille(true);
							sprim.evaluer();
						}
					}  
				}
			}
		}
	}
	
	
	
	/**fonction de recopie de la matrice de jeu 6x5
	 * @param from matrice a recopier
	 * @param to matrice recopiee*/
	private void copieMatrice(int [][]from, int[][]to)
	{
		for(int i=0; i<HEIGHT+1; i++)
			System.arraycopy(from[i], 0, to[i], 0, WIDTH+1);
	}
	
	/**
	 * @return the matriceJeu
	 */
	public int[][] getMatriceJeu() {
		return matriceJeu;
	}

}
