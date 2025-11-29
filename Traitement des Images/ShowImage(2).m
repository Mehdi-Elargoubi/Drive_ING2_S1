function [A]=ShowImage(I,fig,varargin)
val_gray=256;
alpha=0.7;
band=[];
titre='image';
surf=0;
nVarargs = length(varargin);
for k = 1:2:nVarargs
    if strcmp(varargin{k},'gray'),val_gray=varargin{k+1};end
    if strcmp(varargin{k},'band'),band=varargin{k+1};end
    if strcmp(varargin{k},'alpha'),alpha=varargin{k+1};end
    if strcmp(varargin{k},'title'),titre=varargin{k+1};end
     if strcmp(varargin{k},'mesh'),surf=varargin{k+1};end
end

figure(fig)
clf;
if size(I,3)==1
    A=I;
    if surf==0
        if max(I(:))==1,image(double(I)*255),else,image(double(I)); end
        colormap(gray(val_gray));
        title(titre)
        axis image

    else
        mesh(I)
    end
   
end
if size(I,3)==3
    
    if isempty(band),band=[1 2 3];end
    for k=1:length(band),A(:,:,k)=I(:,:,band(k));end
    
    if length(band)==1
        image(double(A))
        colormap(gray(val_gray));
         title(titre)
    else
        image(double(A)/255)
         title(titre)
    end
    axis image

end
if size(I,3)>3
    if isempty(band),band=[4 3 2];end
    for k=1:3,A(:,:,k)=I(:,:,band(k));end
    
    B=double(A);
    I=double(I);
    alpha=max(B(:))/max(I(:));
    M=alpha*max(I(:));
    B=B/M;
    %max(B(:))
    image(B)
     title(titre)
     axis image

end
    