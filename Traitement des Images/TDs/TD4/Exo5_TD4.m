% Charger l'image
I = imread('coloredChips.png');

% Définir les valeurs de R à tester
Rs = [0.1, 0.2, 0.3, 0.4, 0.5];

for i = 1:length(Rs)
    R = Rs(i);
    
    % Appliquer la classification par mean shift
    [A, ID, centers] = Ms(I, R);
    
    % Afficher l'image originale et l'image classifiée
    figure;
    subplot(1, 2, 1);
    imshow(I);
    title('Image originale');
    
    subplot(1, 2, 2);
    imshow(A);
    title(['Image classifiée par mean shift, R = ', num2str(R)]);
end

% Question b :
% Non, la qualité dépend de R :
%    Petit R : Détails élevés mais plus de petits clusters (peut être bruité).
%    Grand R : Zones plus grandes et simples, mais peut mélanger des couleurs proches.

% Question c :
% Comment la classification change-t-elle avec R ?
%   Quand R augmente : Les couleurs se regroupent davantage.
%   Quand R est petit : Il y a plus de détails, mais c'est parfois moins net.

% ==> Un bon choix de RR est important : petit RR pour plus de détails, grand RR pour plus de simplicité.


%%%%%%%% Calcul d'Erreur

load('stat5.mat');  % M et S pour la classification Bayésienne

% Classification Bayésienne pour obtenir le vecteur ID_bayes
[~, ID_bayes, ~] = Cb(I, M, S);

% Définir les valeurs de R à tester pour le mean shift
Rs = [0.1, 0.2, 0.3, 0.4, 0.5];
erreurs_mean_shift = zeros(size(Rs));

% Boucle sur chaque valeur de R pour comparer les classifications
for i = 1:length(Rs)
    R = Rs(i);
    
    % Classification par mean shift
    [~, ID_ms, ~] = Ms(I, R);
    ID_ms = ID_ms(:);  % Aplatir pour correspondre à ID_bayes
    
    % Vérification des tailles
    if numel(ID_bayes) ~= numel(ID_ms)
        error('Les tailles de ID_bayes et ID_ms ne correspondent pas.');
    end
    
    % Calculer le pourcentage d’erreur pour la valeur actuelle de R
    erreurs_mean_shift(i) = (sum(ID_bayes ~= ID_ms) / numel(ID_bayes)) * 100;
end

% Affichage des résultats
for i = 1:length(Rs)
    disp(['Pourcentage d’erreur pour R = ', num2str(Rs(i)), ' : ', num2str(erreurs_mean_shift(i)), '%']);
end
