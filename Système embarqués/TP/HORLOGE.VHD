-- generateur d'horloge symetrique pour test
-- parametre generique: demi periode

library IEEE;
use IEEE.std_logic_1164.all;

entity CLOCK is
 generic(DEMI_PERIODE : time);
 port (SORTIE : out std_logic);
end CLOCK;

architecture A_CLOCK of CLOCK is
begin
 general:process
  variable PERIODE :time;
  
  begin
   PERIODE := DEMI_PERIODE * 2;
   SORTIE<='0','1' after DEMI_PERIODE;
   wait for PERIODE;
  end process general;
end A_CLOCK;
           
