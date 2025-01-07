struct Horloge {                                                                
    unsigned int ms=0, sec=0, min=0;                                            
};                                                                              
                                                                                
volatile Horloge chrono;                                                        
                                                                                
void init_T2()                                                                  
{                                                                               
    noInterrupts();                                                             
                                                                                
    TCNT2 = 0;                                                                  
    TCCR2A = B10;   
    OCR2A = 249; // à définir APRÈS le réglage du mode de comptage
    TCCR2B = B100;                                                                                                                        
    TIMSK2 = B10;                                                               
                                                                                
    interrupts();                                                               
}                                                                               
                                                                                
void maj_chrono()                                                               
{                                                                               
    chrono.ms++;                                                                
    if (chrono.ms>=1000)                                                        
    {                                                                           
        chrono.ms = 0;                                                          
        chrono.sec++;                                                           
        if (chrono.sec>=60)                                                     
        {                                                                       
            chrono.sec = 0;                                                     
            chrono.min++;                                                       
        }                                                                       
    }                                                                           
}                                                                               
                                                                                
ISR(TIMER2_COMPA_vect)                                                          
{                                                                               
    byte SREG_sauv = SREG;                                                                          
    maj_chrono();                                                               
    SREG = SREG_sauv;                                                           
}                                                                               
                                                                                
void affiche_chrono()                                                           
{                                                                               
    Serial.print(chrono.min);                                                   
    Serial.print('\t');                                                         
    Serial.print(chrono.sec);                                                   
    Serial.print('\t');                                                         
    Serial.println(chrono.ms);                                                  
}                                                                               

void setup()                                                                    
{                                                                               
    Serial.begin(9600);                                                         
    init_T2();                                                                  
}     

                                                                                
void loop()                                                                     
{                                                                               
    affiche_chrono();                                                           
}                                                                                                   
