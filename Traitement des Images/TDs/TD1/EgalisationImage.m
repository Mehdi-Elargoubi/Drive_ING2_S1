function J = EgalisationImage(I)
    
[N,M] = size(I);

J = zeros(N,M);
[h, H] = histo(I,0:255);

Hn = H./(N*M)

for i= 0:255
    J(I==2) = 255 * Hn(i+1);
end

J = uint8(J);

end
