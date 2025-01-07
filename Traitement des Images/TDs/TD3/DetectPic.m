function [P,C_seuil]=DetectPic(C,seuil)
M=max(C(:));
C_seuil=(C>=M*seuil);

L = bwlabel(C_seuil);
% figure(6)
% imshow(L)
% hold on
% pause
lab=unique(L);
lab(1)=[];

P=[];
for k=1:length(lab)
    
    I=(L==lab(k));
    I=I.*C;
    [i,j]=find(I==max(I(:)));
    
   P=[P;[mean(j),mean(i)]];
   %plot(mean(j),mean(i),'or','MarkerFaceColor','r','MarkerSize',4)
   
    
end
