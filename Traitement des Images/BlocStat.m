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