% Question 4 :
I = imread('coloredChips.png');

% Question 5-a :
K=7;
[A,ID,centers]=Km(I,K);


% Afficher l'image originale
subplot(1, 2, 1);
imshow(I);title('Image Originale');
    
% Afficher l'image de classification
subplot(1, 2, 2);
imshow(A); title(['Classification par k-moyennes avec K = ', num2str(K)]);
    
% Afficher les centres des clusters pour information (optionnel)
%disp(['Centres des clusters pour K = ', num2str(K), ':']);
%disp(centers);

% Définir les valeurs de K à utiliser pour la classification
K_values = [3, 6, 7, 10];

% Boucle pour exécuter la classification pour chaque valeur de K
for i = 1:length(K_values)
    K = K_values(i);  % Nombre de clusters
    
    % Effectuer la classification par k-moyennes
    [A, ID, centers] = Km(I, K);
    
    % Afficher l'image originale et le résultat de la classification
    figure;
    
    % Afficher l'image originale
    subplot(1, 2, 1);
    imshow(I);
    title('Image Originale');
    
    % Afficher l'image de classification
    subplot(1, 2, 2);
    imshow(A);
    title(['Classification par k-moyennes avec K = ', num2str(K)]);
    
    % Afficher les centres des clusters pour information (optionnel)
    %disp(['Centres des clusters pour K = ', num2str(K), ':']);
    %disp(centers);
end

% Question 5-b)
% Une classification "parfaite" n’est pas garantie même avec une valeur élevée de KK 
% parce que le k-moyennes repose sur des valeurs de couleur moyenne et non sur 
% des connaissances sur les objets ou les formes.

% Les limites de k-moyennes incluent la sensibilité aux variations d'éclairage 
% et aux couleurs similaires. Par exemple, si l'image contient deux objets de couleurs 
% proches, ces objets risquent d’être regroupés sous le même cluster.


% Question 5-c)
% Plus le nombre de clusters KK augmente, plus la classification devient précise. 
% Avec un KK trop faible (comme 3), les couleurs sont mal séparées. Un K trop élevé (10) 
% peut trop détailler l’image, créant des divisions inutiles. Le meilleur compromis se situe
% autour de K=7, où la classification est équilibrée et les objets sont bien séparés.


% Question 5-d)
load('stat5.mat');
[A_bayes, ID_bayes, centers_bayes] = Cb(I, M, S);
% -Classification par k-moyennes pour différentes valeurs de K

ID_km_K3 = Km(I, 3);   % Classification pour K = 3
ID_km_K6 = Km(I, 6);   % Classification pour K = 6
ID_km_K7 = Km(I, 7);   % Classification pour K = 7
ID_km_K10 = Km(I, 10); % Classification pour K = 10

% Aplatir les matrices d'ID pour les rendre des vecteurs 1D
ID_bayes = ID_bayes(:);
ID_km_K3 = ID_km_K3(:);
ID_km_K6 = ID_km_K6(:);
ID_km_K7 = ID_km_K7(:);
ID_km_K10 = ID_km_K10(:);

% 5) Calculer le pourcentage d'erreur pour chaque valeur de K
function error_percentage = calculate_error(ID_bayes, ID_km)
    % Comparer les étiquettes entre la méthode Bayes et la méthode K-moyennes
    num_mistakes = sum(ID_bayes ~= ID_km);  % Compter les erreurs
    total_pixels = numel(ID_bayes);         % Total de pixels
    error_percentage = (num_mistakes / total_pixels) * 100; % Pourcentage d'erreur
end

% Calculer l'erreur pour chaque valeur de K
error_K3 = calculate_error(ID_bayes, ID_km_K3);
error_K6 = calculate_error(ID_bayes, ID_km_K6);
error_K7 = calculate_error(ID_bayes, ID_km_K7);
error_K10 = calculate_error(ID_bayes, ID_km_K10);

% Affichage des résultats
disp(['Pourcentage d erreur pour K=3 : ', num2str(error_K3), '%']);
disp(['Pourcentage d erreur pour K=6 : ', num2str(error_K6), '%']);
disp(['Pourcentage d erreur pour K=7 : ', num2str(error_K7), '%']);
disp(['Pourcentage d erreur pour K=10 : ', num2str(error_K10), '%']);

% 6) Afficher les images originales et les classifications obtenues
figure;
subplot(2, 2, 1);
imshow(I); 
title('Image Originale');

subplot(2, 2, 2);
imshow(label2rgb(ID_bayes)); 
title('Classification Bayésienne');

subplot(2, 2, 3);
imshow(label2rgb(ID_km_K3));
title('K-Moyennes (K=3)');

subplot(2, 2, 4);
imshow(label2rgb(ID_km_K6));
title('K-Moyennes (K=6)');