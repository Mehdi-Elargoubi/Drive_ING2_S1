function [Icb,ID,centers]=Cb(I,M,E)

[m,n,c]=size(I);
score=Vraisemblance(I,M,E);

Rep=[];
 for j=1:size(score,1)
     [u,v]=max(score(j,:));
     ID(j,1)=v(1);
     R(j,1)=M(1,v(1));
     G(j,1)=M(2,v(1));
     B(j,1)=M(3,v(1));
 end
 
Icb(:,:,1)=reshape(R,m,n);
Icb(:,:,2)=reshape(G,m,n);
Icb(:,:,3)=reshape(B,m,n);
Icb=Icb/255;
centers=M;




function [score]=Vraisemblance(I,M,S)

R=I(:,:,1);R=double(R(:)');
G=I(:,:,2);G=double(G(:)');
B=I(:,:,3);B=double(B(:)');
 
 for k=1:size(M,2)
   
     V1=(R-M(1,k))/(sqrt(2)*S(1,k));
     V2=(G-M(2,k))/(sqrt(2)*S(2,k));
     V3=(B-M(3,k))/(sqrt(2)*S(3,k));
     D=-(V1.^2+V2.^2+V3.^2)-log(prod(S(:,k)));
     score(:,k)=D';
 end