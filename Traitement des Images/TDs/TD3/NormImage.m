function [I] = NormImage(I)
C = size(I,3);
for i=1:C
    x=double(I(:,:,i));
    x=(x-mean(x(:)))/std(x(:));
    I(:,:,i)=x;
end
