% I: image
% K: nombre de classes à étudier
% N: nombre d'échantillons par classe
% TB: taille du bloc

function [M,S]=BuildStatImages(I,TB,names)

% names={'pieces_jaunes','pieces_rouges','pieces_vertes','pieces_bleues',...
% 'pieces_oranges','stylo','fond'};

M=[];S=[];
for k=1:size(names,2)
    
    feat=BlocMouse1(I,TB,names{k});
    [feat_m,feat_s]=BlocStat(feat,TB);
    
    M=[M,feat_m(:,end)];
    S=[S,feat_s(:,end)];
end

% Moyennes=array2table(M,'VariableNames',names)
% Ecart_Types=array2

function feat=BlocMouse1(I,taille_bloc,name)

info.n_band=size(I,3);
info.t_bloc=taille_bloc;
info.n_row=size(I,1);
info.n_col=size(I,2);

figure(1),clf;imshow(I)
hold on
title(name);

button=1;
X=[];Y=[];feat=[];iter=1;
while 1

    [x,y,button]=ginput(1);
    x=round(x);
    y=round(y);
    if button==3,break;end
    AffBloc([y,x],info.t_bloc);
    text(x,y,num2str(iter));  
    feat=[feat,BuildBlocFeat(I,[y,x],info)];
    iter=iter+1;
end

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
r=floor(t/2);

cj=center;
 feat=[];
for k=1:n_band
   Ik=I(:,:,k);
   x=Ik(cj(1)-r:cj(1)+r-1,cj(2)-r:cj(2)+r-1);
   feat=[feat;x(:)];
end
%table(S,'VariableNames',names)

function [feat_m,feat_s]=BlocStat(feat,t_b)
feat_m=[];
 feat_s=[];
    X=[] ;x=[];
 for k=1:size(feat,2)
     X=feat(:,k);
     X=reshape(X,t_b*t_b,3);
     feat_m=[feat_m,mean(double(X))'];
     feat_s=[feat_s,std(double(X))'];
     x=[x;X];
 end
 feat_m=[feat_m,mean(double(x))'];
 feat_s=[feat_s,std(double(x))'];