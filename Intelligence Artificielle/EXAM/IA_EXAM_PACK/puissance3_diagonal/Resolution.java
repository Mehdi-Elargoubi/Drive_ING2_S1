package algo;

import java.util.ArrayList;

import modele.Situation;

/**
 * classe Resolution, elle contient l'algo alphabeta 
 * @author emmanuel adam
 * */
public class Resolution {

	
	/**fonction alphabeta, determine la valeur du noeud/ de la situation s
	 * pour recuperer la situation a prendre, balayer ensuite la liste des successeurs de s 
	 * et prendre la premiere situation ayant la meme estimation
	 * @param s situation, etat 
	 * @param alpha borne minimum
	 * @param beta borne maximum
	 * @return estimation de la situation s en fonction du jeu de l'adversaire*/
	public static int  alphaBeta(Situation s , int alpha, int beta) 
	{
		int  borne, val;
		if (s.isFeuille())
		{
			return s.getHeuristique();
		}
		
		if (s.isMax())
		{
			ArrayList<Situation> successeurs = s.getSuccesseurs();
			borne = alpha;
			for (Situation suc:successeurs)
			{
				val = alphaBeta(suc, borne, beta);
				suc.setH(val);
				 if (val>borne) borne = val;
				 if (borne>=beta) return borne;
			}
		}
		else
		{
			ArrayList<Situation> successeurs = s.getSuccesseurs();
			borne = beta;
			for (Situation suc:successeurs)
			{
				val = alphaBeta(suc, alpha, borne);
				suc.setH(val);
				 if (val<borne) borne = val;
				 if (borne<=alpha) return borne;
			}
		}
		return borne;			
	}
	
}
