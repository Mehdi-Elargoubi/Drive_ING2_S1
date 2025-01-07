function ShowClass(I,Id,sub)
[m,n,c]=size(I);
Id=reshape(Id,[m,n]);
I=double(I);
class=unique(Id);
for k=1:length(class)
    subplot(sub(1),sub(2),k)
    Ib=(Id==class(k));
    J(:,:,1)=I(:,:,1).*double(~Ib)+double(Ib)*128;
    J(:,:,2)=I(:,:,2).*double(~Ib)+double(Ib)*128;
    J(:,:,3)=I(:,:,3).*double(~Ib)+double(Ib)*128;
    imshow(uint8(J));
end