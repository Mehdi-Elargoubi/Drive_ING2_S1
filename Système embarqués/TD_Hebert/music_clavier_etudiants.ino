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
}
