% Charger l'image et les paramètres Bayésiens
I = imread('coloredChips.png');
load('stat5.mat');  % M et S sont chargés

% Classification Bayésienne pour obtenir le vecteur ID_bayes
[~, ID_bayes, ~] = Cb(I, M, S);

% Initialisation du tableau pour stocker les erreurs pour chaque K
Ks = [3, 6, 7, 10];
erreurs = zeros(size(Ks));

% Boucle sur chaque valeur de K pour comparer les classifications
for i = 1:length(Ks)
    K = Ks(i);
    
    % Classification par k-moyennes
    [~, ID_km, ~] = Km(I, K); 
    ID_km = ID_km(:);  % Aplatir le résultat pour correspondre à ID_bayes
    
    % Vérifier que les tailles correspondent
    if numel(ID_bayes) ~= numel(ID_km)
        error('Les tailles de ID_bayes et ID_km ne correspondent pas.');
    end
    
    % Calculer le pourcentage d’erreur pour la valeur actuelle de K
    erreurs(i) = (sum(ID_bayes ~= ID_km) / numel(ID_bayes)) * 100;
end

% Afficher les résultats
for i = 1:length(Ks)
    disp(['Pourcentage d’erreur pour K = ', num2str(Ks(i)), ' : ', num2str(erreurs(i)), '%']);
end
