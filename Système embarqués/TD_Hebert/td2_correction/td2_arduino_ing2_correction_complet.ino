const byte LIGNES = 4; //nombre de lignes du clavier matriciel
const byte COLONNES = 3; //nombre de colonnes du clavier matriciel

// **** a regler ! ****
const byte brocheLigne[LIGNES] = {12,10,9,8};//numeros de broche des lignes du clavier (=entrees) ; 1ere broche = fil de gauche du clavier << deplie >> face a soi
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
 /* SOLUTION HAUT NIVEAU

  int i;
  // direction des sorties
  for (i=0;i<COLONNES;i++)
  {
    pinMode(brocheColonne[i], OUTPUT);
    digitalWrite(brocheColonne[i], HIGH); //désactivation des 3 colonnes
  }
  // direction des entrees
  for (i=0;i<LIGNES;i++)
    pinMode(brocheLigne[i], INPUT_PULLUP);
*/

    //SOLUTION BAS NIVEAU : plus court, mais n'utilise pas les tableaux constants !!

    DDRD |= (0b111)<<2; //direction des fils-colonnes : sorties
    DDRB &= ~(0b1111); //direction des fils-lignes : entrees
    PORTB |= 0b1111;   //resistance de tirage sur les fils-lignes

    PORTD |=(0b111)<<2; //désactivation des 3 colonnes
}


//*********************************************************************************************
//identifie la touche pressee (code ASCII du caractere correspondant) ou absence de touche (0)
//*********************************************************************************************
char detecteTouche()
{
  for (unsigned char i=0; i<COLONNES; i++) //unsigned char : entier 8 bits non signe (=byte)
  {
    //digitalWrite(brocheColonne[i], LOW); //activation de la colonne, pour lecture de ses touches
    PORTD &= ~(1 << brocheColonne[i]); //activation de la colonne i, pour lecture de ses touches
    delay(1); // on laisse le temps aux tensions de se stabiliser
    for (unsigned char j=0; j<LIGNES; j++)//test des 4 touches de la colonne (1 touche par ligne)
    {
        //if ( digitalRead(brocheLigne[j]) == LOW )
        if (not (PINB & (1<<brocheLigne[j]%8)))
        {
          //digitalWrite(brocheColonne[i], HIGH); //ne pas oublier de desactiver la colonne avant de quitter
          PORTD |= (1 << brocheColonne[i]); 
          return keys[j][i];
        }
    }
    //digitalWrite(brocheColonne[i], HIGH); //desactivation de la colonne
    PORTD |= (1 << brocheColonne[i]); 
  }
  return 0;
}


//*****************************************************************************************
//identifie nouvel appui (=action de l'enfoncer) sur une touche (code ASCII du caractere)
//  ou absence de nouvel appui (0)
//*****************************************************************************************
char litTouche()
{
  static char ancienne; //variable globale accessible uniquement dans litTouche()
                        //=code de la derniere touche detectee (0 si pas de touche detectee)
  
  char nouvelle = detecteTouche();
  if (nouvelle!=ancienne)
  {
    ancienne = nouvelle;
    return nouvelle;
  } 
  else
    return 0;
}

void setup()
{
    Serial.begin(9600);
    initClavier();
}

void loop()
{
    char c = litTouche();
    if (c)
        Serial.println(c);
}
