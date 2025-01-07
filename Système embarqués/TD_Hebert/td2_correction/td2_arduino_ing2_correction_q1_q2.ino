// correction fonction d'initialisation du clavier

void Setup()
{
	Serial.begin(9600);
	init_clavier(); // À NE PAS OUBLIER !
}

void init_clavier()
{
	//initialisation des colonnes (broches 2,3,4 : port D)
	DDRD |= B11100; //3 colonnes en Sortie
	
	//initialisation des lignes (broches 8,9,10,11 : port B)
	DDRB &= ~B1111; //4 lignes en Entrée
	PORTB |= B1111; //4 lignes en pull-up
}


bool etat_touche_1()
{
	PORTD |= B11100; //mise à l'état haut de toutes les colonnes
	PORTD &= ~(1<<4); // mise à l'état bas de la colonne de la touche 1
						//bit 4 du port D correspond à la broche 4
	return not ( PINB & (1<<3) ); // renvoie inverse de l'état de la ligne
								//de la touche 1
								// bit 3 du port B correspond à la broche 11
}
