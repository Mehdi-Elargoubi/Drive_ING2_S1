% I: image
% K: nombre de classes à étudier
% N: nombre d'échantillons par classe
% TB: taille du bloc

function [M,S]=BuildClass(I,TB,names)

% names={'pieces_jaunes','pieces_rouges','pieces_vertes','pieces_bleues',...
% 'pieces_oranges','stylo','fond'};

M=[];S=[];
for k=1:size(names,2)
    
    feat=BlocMouse(I,TB,names{k});
    [feat_m,feat_s]=BlocStat(feat,TB);
    
    M=[M,feat_m(:,end)];
    S=[S,feat_s(:,end)];
end

% Moyennes=array2table(M,'VariableNames',names)
% Ecart_Types=array2table(S,'VariableNames',names)