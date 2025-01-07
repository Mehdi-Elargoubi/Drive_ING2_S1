const byte LIGNES = 4; //nombre de lignes du clavier matriciel
const byte COLONNES = 3; //nombre de colonnes du clavier matriciel

// **** a regler ! ****
const byte brocheLigne[LIGNES] = {12,10,9,8};//numeros de broche des lignes du clavier (=entrées) ; 1ere broche = fil de gauche du clavier << deplie >> face a soi
const byte brocheColonne[COLONNES] = {4,3,2};   //numeros de broche des colonnes du clavier (=sortie) ; derniere broche = fil de droite 
// ********************

const char keys[LIGNES][COLONNES] = {
  {'1','2','3'},
  {'4','5','6'},
  {'7','8','9'},
  {'*','0','#'}
};



//***************************************************************************************
//  initialisation du clavier : gestion des lignes de port auxquelles il a ete branche
//***************************************************************************************
void initClavier()
{
  int i;
  // direction des sorties
  for (i=0;i<COLONNES;i++)
  {
    pinMode(brocheColonne[i], OUTPUT);
    digitalWrite(brocheColonne[i], HIGH);
  }
  // direction dess entrées
  for (i=0;i<LIGNES;i++)
    pinMode(brocheLigne[i], INPUT_PULLUP);
}


//*********************************************************************************************
//identifie la touche pressée (code ASCII du caractere correspondant) ou absence de touche (0)
//*********************************************************************************************
char detecteTouche()
{
  for (byte i=0; i<COLONNES; i++)
  {
    digitalWrite(brocheColonne[i], LOW); //activation de la colonne, pour lecture de ses touches
    for (byte j=0; j<LIGNES;j++)
    {
        if ( digitalRead(brocheLigne[j]) == LOW )
        {
          digitalWrite(brocheColonne[i], HIGH); //ne pas oublier de desactiver la colonne avant de quitter
          return keys[j][i];
        }
    }
    digitalWrite(brocheColonne[i], HIGH); //desactivation de la colonne
  }
  return 0;
}


//*****************************************************************************************
//identifie nouvel appui (=action de l'enfoncer) sur une touche (code ASCII du caractere)
//  ou absence de nouvel appui (0)
//*****************************************************************************************
char litTouche()
{
  static char ancienne;
  
  char nouvelle = detecteTouche();
  if (nouvelle!=ancienne)
  {
    ancienne = nouvelle;
    return nouvelle;
  } 
  else
    return 0;
}
