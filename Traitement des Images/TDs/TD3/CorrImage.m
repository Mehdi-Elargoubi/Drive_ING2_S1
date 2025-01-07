function [C]= CorrImage(I,X,mode)

if nargin==2,mode=0;end
%tailles
[M,N,nC]=size(I);
T=size(X,1);

%bord
D=(T-1)/2;

%init de la matrice de score
C=ones(M,N);
if mode==1,X=NormImage(X);end
X=double(X(:));

for i=1+D:M-D
    for j=1+D:N-D
        A=I(i-D:i+D,j-D:j+D,:); 
        if mode==1,A=NormImage(A);end
        A=double(A(:));
       C(i,j)=A'*X;
    end
end

C=C/(T*T*nC);


        