
I=imread('coloredChips.png');

[rows, cols, c] = size(I);
disp(['Dimensions : ' num2str(rows) 'x' num2str(cols)]);
disp(['Nombre de composantes (canaux) : ' num2str(c)]);
% whos I % Meme concept

imshow(I); title('Image Colored Chips');

t_b = 51;  % Taille du bloc de référenc
X = BlocMouse(I, t_b);
figure;
imshow(X);

% Extraire les composantes Rouge, Verte et Bleue du bloc sélectionné
R = X(:,:,1);  % Composante Rouge
G = X(:,:,2);  % Composante Verte
B = X(:,:,3);  % Composante Bleue

% Afficher le bloc et les histogrammes dans la même figure
figure
% Afficher le bloc image
subplot(2, 2, 1);
imshow(X);
title('Bloc Image X');
% Afficher l'histogramme de la composante Rouge (R)
subplot(2, 2, 2);
imhist(R);
title('Histogramme de la composante R');
% Afficher l'histogramme de la composante Verte (G)
subplot(2, 2, 3);
imhist(G);
title('Histogramme de la composante G');
% Afficher l'histogramme de la composante Bleue (B)
subplot(2, 2, 4);
imhist(B);
title('Histogramme de la composante B');

% question 6
  % a)
C=CorrImage(I,X);

%Afficher le score C avec imshow
figure;
subplot(1,3,1);
imshow(I);

subplot(1,3,2);
imshow(C, []); % Affiche la corrélation avec l'échelle de couleur automatique
title('Score de Corrélation avec imshow');
% Afficher le score C avec mesh

subplot(1,3,3);
mesh(C);  % Affiche la corrélation sous forme de surface 3D
title('Score de Corrélation avec mesh');

% b)
%Lorsque vous effectuez cette détection par corrélation, vous vous attendez à voir des valeurs
% élevées de la matrice de corrélation dans les zones où le bloc correspond à des régions 
% similaires de l'image. Si le bloc X représente un élément particulier de l'image, alors 
% la corrélation sera la plus élevée dans la zone où ce même élément apparaît dans l'image.

% question 7:
seuil = 0.85;
[P, Cs] = DetectPic(C, seuil);

disp('Coordonnées des pics détectés :');
disp(P);

% Affichage de l'image Cs
figure;
imshow(Cs);
title('Image du score seuillée Cs');
imshow(I);
hold on;
plot(P(:,2), P(:,1), 'r+', 'MarkerSize', 10, 'LineWidth', 2); % Marquer les pics
title('Positions des pics dans l''image originale');
% Coordonnées des pics : Les positions dans P indiquent les endroits où le bloc de référence 
% est le plus similaire à des régions dans l'image d'origine. Ces positions devraient 
% correspondre à des zones similaires au bloc de référence.

% Cs - Image seuillée : Cs affiche uniquement les scores qui ont dépassé le seuil (0.99 ici). 
% Les zones très similaires au bloc de référence apparaîtront sous forme de points ou de 
% régions marquées, tandis que les autres zones seront supprimées ou réduites en intensité.


% Seuil de 0.99 : Très strict, seules les régions très proches du bloc de référence seront 
% identifiées.
% Seuil de 0.95 puis 0.90 : En abaissant le seuil, davantage de régions seront considérées 
% comme correspondantes. Cela peut faire apparaître des pics supplémentaires mais aussi des 
% régions moins similaires.

% Conclusion
% En ajustant le seuil, vous pouvez affiner la détection pour qu'elle soit plus ou moins 
% stricte. Un seuil élevé donne des correspondances très précises mais peut manquer des 
% correspondances potentielles, tandis qu'un seuil plus bas permet de trouver davantage 
% de correspondances, y compris celles moins similaires.

% Question 8 :
% Calcul de la carte de corrélation normalisée
C = CorrImage(I, X, 1);  % '1' active la normalisation dans CorrImage

% Affichage de la carte de corrélation normalisée
figure;
subplot(1,3,1);
imshow(C);
title('Carte de corrélation normalisée');
subplot(1,3,2);
mesh(C);
title('Corrélation normalisée - Vue 3D');
subplot(1,3,3);
[P, Cs] = DetectPic(C, seuil);
imshow(Cs);

% Pourquoi la normalisation ? Elle rend la méthode plus efficace même si certaines parties 
% de l'image sont plus sombres ou plus claires, car elle évite d'être influencée par la 
% luminosité.

% En conclusion, la corrélation normalisée permet une recherche plus précise du bloc dans
% l'image, car elle ne dépend pas des variations d'intensité.