//-------------------------------------------------------------
//fichier clavier.ino
//-------------------------------------------------------------


const byte LIGNES = 4; //nombre de lignes du clavier matriciel
const byte COLONNES = 3; //nombre de colonnes du clavier matriciel

// **** a regler ! ****
const byte brocheLigne[LIGNES] = {11,10,9,8};//numeros de broche des lignes du clavier (=entrées) ; 1ere broche = fil de gauche du clavier << deplie >> face a soi
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





//-------------------------------------------------------------
//fichier music.ino
//-------------------------------------------------------------


// parametres reglables
const unsigned int DUREE_NOTE_NOIRE_MS = 500; // duree en ms d'une note << noire >> (= de duree 1)
const unsigned int DUREE_SILENCE_FIN_NOTE_MS = 50; // duree en ms d'un silence post-note jouee (INUTILISÉE au début)

// data : morceau a jouer en boucle

const double FREQ[] = {349.228, 415.305, 349.228, 349.228, 466.164, 349.228, 311.127, 
                349.228, 523.251, 349.228, 349.228, 554.365, 523.251, 415.305, 
                349.228, 523.251, 698.456, 349.228, 311.127, 311.127, 261.626, 391.995, 349.228};
const double DUREE[] = {1, 0.75, 0.5, 0.25, 0.5, 0.5, 0.5,
                  1, 0.75, 0.5, 0.25, 0.5, 0.5, 0.5,
                  0.5, 0.5, 0.5, 0.25, 0.5, 0.25, 0.5, 0.5, 2.5};

// nombre de notes du morceau
const unsigned int NB_NOTES = sizeof(FREQ)/sizeof(double); // nombre de notes du morceau


// autres variables globales
unsigned int indNote = 0; //prochaine note a jouer


void desactive_T0()
{
	bitClear(TCCR0B,0);
	bitClear(TCCR0B,1);
	bitClear(TCCR0B,2);
}

//***************************************************************************************
// initialisation du timer 2 qui va produire le son sur la broche de sortie associee a
//   la comparaison A (= broche 11)
//***************************************************************************************
void initT2music() { //génère un LA à 440Hz à cause de l'approximation

// a remplir intégralement
  
  noInterrupts(); // interdiction temporaire de toutes les interruptions
  
  TCCR2A = 0;
  TCCR2B = 0;
  TIMSK2 = 0; 
  
  bitSet(TCCR2A, WGM21); // mode CTC du timer 2
  bitSet(TCCR2B, CS22); // connexion au signal d’horloge prédivisé par 128
  bitSet(TCCR2B, CS20); // connexion au signal d’horloge prédivisé par 128
  OCR2A = 140; // fréquence initiale proche de 442 Hz (un la ! cf. dernière version du TP)
  
  // ici, pas besoin d’interruptions !! 
  // bitSet(TIMSK2, OCIE2A); // autorisation de l’interruption de comparaison A
  
  //TCNT2 = 0; // initialisation du registre de comptage
  
  bitSet(TCCR2A, COM2A0); // mise en place du « toggle » de la broche OC2A = broche 11
  						  // pas besoin de forcer le bit COM2A1 à 0, cf. ligne 34
  
  pinMode(11, OUTPUT); // orientation de la broche OC2A en sortie
  
  interrupts(); // levée de l’interdiction des interruptions
}

//***************************************************************************************
//  joue la note de la frequence demandee, sans gestion de duree
//***************************************************************************************
void joueNote(double frequence)
{
   // a completer : 1 seule instruction
   OCR2A = 62500/frequence - 1; //62500 = 16e6/(2*128)
}


//***************************************************************************************
//  joue un silence (que l'on peut assimiler a une note de frequence nulle), sans
//    gestion de duree
//***************************************************************************************
void silence()
{
  // a completer : 1 seule instruction
  OCR2A = 0; // plus petite durée de cycle => plus petite période du signal audio
             // => plus grande fréquence possible (=ultrason, inaudible)
}


//***************************************************************************************
// renvoie la duree de la prochaine note jouee (index=indNote)
//***************************************************************************************
unsigned int dureeNoteMs()
{
    return DUREE_NOTE_NOIRE_MS*DUREE[indNote];
}


//***************************************************************************************
//  - joue la prochaine note, sans gestion de duree, et passe a la suivante
//  - de plus, apres chaque note du morceau, joue un silence tres court supplementaire pour
//    marquer leur attaque (ainsi 2 notes consecutives de meme frequence seront discernables)
//***************************************************************************************
void joueNoteMorceau()
{
  joueNote(FREQ[indNote++]);
  if (indNote>=NB_NOTES)
    indNote = 0;
}


//***************************************************************************************
//  repart au debut du morceau
//***************************************************************************************
void resetMorceau()
{
  // a completer : 1 seule instruction
  indNote = 0; //retour au début
}





//-------------------------------------------------------------
// fichier principal
//-------------------------------------------------------------

unsigned int duree;
unsigned long int debutDerniereNote;
unsigned long int debutDerniereLectureClavier;

void setup() {
  Serial.begin(9600);
  initClavier();
  initT2music();
}



void loop() {
  // changement de note
  if ( (millis()-debutDerniereNote) >= duree )
  {
    duree = dureeNoteMs();
    joueNoteMorceau();
    debutDerniereNote = millis();
  }
  if ( (millis()-debutDerniereLectureClavier) >= 50 )
  {
    char touche = litTouche();
    if (touche)
        Serial.println(touche);
    debutDerniereLectureClavier = millis();
  }
  
}
