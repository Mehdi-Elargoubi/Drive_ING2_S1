-- fonction de conversion
-- entier --> bit_vector
-- bit_vector --> entier

library IEEE;
use IEEE.std_logic_1164.all;

package CONV_PKG is
function ENTIER_TO_BIT(N: in INTEGER) return std_logic_vector;
function BIT_TO_ENTIER(V: in std_logic_vector) return INTEGER;
end CONV_PKG;

package body CONV_PKG is
	---------------------
function ENTIER_TO_BIT(N: in INTEGER) return std_logic_vector is
  variable V:std_logic_vector(3 downto 0);
  variable X:INTEGER:=0;
begin
 X:=N;
 for I in V'REVERSE_RANGE loop
  if X rem 2=0 then
   V(I):='0';
  else
   V(I):='1';
  end if;
  X:=X/2;
end loop;
return V;
end ENTIER_TO_BIT;
	----------------------- 
function BIT_TO_ENTIER(V: in std_logic_vector) return INTEGER is
  variable N:INTEGER:=0;
begin
 for I in V'RANGE loop
  N:=N*2;
  if V(I)='1' then
              N:=N+1;
  end if;
 end loop;
 return N;
end BIT_TO_ENTIER;
END CONV_PKG;

	
