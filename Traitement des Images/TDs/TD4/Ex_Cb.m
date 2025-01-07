% 1) Charger l’image 'coloredChips.png'
I = imread('coloredChips.png');  

% 2) Charger le fichier 'stat1.mat'
load('stat1.mat');  % Chargement des moyennes (M) et des écarts-types (S)

% 3) La classification Bayesienne avec la fonction Cb.m
[A1, ID1, centers1] = Cb(I, M, S); 

% 4) Affichage des résultats 
% a-
% Afficher l'image originale
figure;
subplot(1, 2, 1);
imshow(I); title('Image originale');

% Afficher l’image classifiée (A)
subplot(1, 2, 2);
imshow(A1); title('Image classifiée stat1');

% c-
load('stat3.mat'); % Charger le fichier stat3.mat

% Classification Bayesienne avec trois échantillons par classe
[A3, ID3, centers3] = Cb(I, M, S);

% Affichage de l'image originale et de l'image classifiée (avec 3 échantillons)
figure;
subplot(1, 3, 1);
imshow(I); title('Image Originale');

subplot(1, 3, 2);
imshow(A3); title('Image Classifiée avec stat3');


load('stat5.mat'); % Charger le fichier stat5.mat

% Classification Bayesienne avec cinq échantillons par classe
[A5, ID5, centers5] = Cb(I, M, S);

% Affichage de l'image originale et de l'image classifiée
subplot(1, 3, 3);
imshow(A5); title('Image Classifiée avec stat5');

% Évaluation de la classification : La perfection de la classification dépendra de la 
% représentation des classes par leurs paramètres, qui s'améliore avec plus d'échantillons.

% Évolution de la classification : En passant de stat1 à stat5, la classification devrait 
% devenir plus précise à mesure que le nombre d'échantillons augmente, avec des résultats 
% plus fiables et moins d'erreurs de classification.



% e-
% Comparer ID1 (stat1) et ID3 (stat3)
errors_1_to_3 = sum(ID1(:) ~= ID3(:));  % Nombre de pixels mal classés
total_pixels = numel(ID1);  % Nombre total de pixels

% Calculer le pourcentage d'erreur entre stat1 et stat3
error_percentage_1_to_3 = (errors_1_to_3 / total_pixels) * 100;
disp(['Pourcentage d''erreur entre stat1 et stat3 : ', num2str(error_percentage_1_to_3), '%']);


% Comparer ID3 (stat3) et ID5 (stat5)
errors_3_to_5 = sum(ID3(:) ~= ID5(:));  % Nombre de pixels mal classés

% Calculer le pourcentage d'erreur entre stat3 et stat5
error_percentage_3_to_5 = (errors_3_to_5 / total_pixels) * 100;
disp(['Pourcentage d''erreur entre stat3 et stat5 : ', num2str(error_percentage_3_to_5), '%']);

% la classification avec un seul échantillon par classe est moins précise, car les paramètres
% des classes sont moins représentatifs de la diversité des objets dans l'image. 

% l'augmentation du nombre d'échantillons (de 3 à 5) permet de mieux capturer la variabilité
% des classes et de réduire les erreurs de classification.

