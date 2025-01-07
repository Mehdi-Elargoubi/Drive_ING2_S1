
function feat=BlocMouse(I,taille_bloc,fig)

info.n_band=size(I,3);
info.t_bloc=taille_bloc;
info.n_row=size(I,1);
info.n_col=size(I,2);

if nargin<3,fig=1;end

figure(fig),
clf;
imshow(I)
hold on

button=1;
iter=1;
while 1

    [x,y,button]=ginput(1);
    x=round(x);
    y=round(y);
    if button==3,break;end
    AffBloc([y,x],info.t_bloc);
    text(x,y,num2str('ref'));  
    feat{iter}=BuildBlocFeat(I,[y,x],info);
    iter=iter+1;
end

if size(feat,2)==1,feat=uint8(feat{1});end

function AffBloc(center,t_bloc)

c=center;
t=t_bloc;
r=round(t/2);

for k=1:size(c,1)
 rectangle('Position',[c(k,2)-r,c(k,1)-r,t,t],'EdgeColor','r')
end

function [feat]=BuildBlocFeat(I,center,info)

n_band=info.n_band;
t=info.t_bloc;
r=(t-1)/2;

cj=center;
 feat=[];
for k=1:n_band
   Ik=I(:,:,k);
   x=Ik(cj(1)-r:cj(1)+r,cj(2)-r:cj(2)+r);
   %feat=[feat;x(:)];
   feat(:,:,k)=x;
end
   
