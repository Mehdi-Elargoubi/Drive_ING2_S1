-- generateur de nb 4 bits pour testbench
-- génère une séquence binaire sur 4 bits
-- avec comme parametres disponibles:
-- la valeur initiale: val_init
-- la valeur finale: val_fin
-- l'increment: increment
-- la duree de la valeur: delta_t
--
-- reboucle a la valeur initiale si fin atteinte
--

library IEEE;
use IEEE.std_logic_1164.all;


use work.conv_pkg.all;


entity gene is
generic(val_init,val_fin,increment:integer;
	delta_t:time);
port(sortie:out std_logic_vector(3 downto 0));
end gene;

architecture arch_gene of gene is
  signal sortie_entiere:integer:=val_init;
begin
general:process
	
	begin
	 
	 wait for delta_t;         --maintien val_init
	 if sortie_entiere+increment>val_fin  --increment possible?
		then
		sortie_entiere<=val_init;     --retour val_init   
	 	else
		sortie_entiere<=sortie_entiere +increment; --increment
	 end if; 
	
	end process general;

sortie<=entier_to_bit(sortie_entiere);  --affectation concurente
end arch_gene; 
