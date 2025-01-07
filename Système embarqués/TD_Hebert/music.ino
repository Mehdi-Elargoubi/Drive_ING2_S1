
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
unsigned int indNote; //prochaine note a jouer


//***************************************************************************************
// initialisation du timer 2 qui va produire le son sur la broche de sortie associee a
//   la comparaison A (= broche 11)
//***************************************************************************************
void initT2music() { //génère un LA à 442Hz à cause de l'approximation

// a remplir intégralement
}

//***************************************************************************************
//  joue la note de la frequence demandee, sans gestion de duree
//***************************************************************************************
void joueNote(double frequence)
{
   // a completer : 1 seule instruction
   // OCR2A = ;
}


//***************************************************************************************
//  joue un silence (que l'on peut assimiler a une note de frequence nulle), sans
//    gestion de duree
//***************************************************************************************
void silence()
{
  // a completer : 1 seule instruction
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
}
