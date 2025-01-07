I = imread('ULCO.jpg');
%imshow(I)

I_bruit = imnoise(I,'salt & pepper',0.05);
%imshow(I_bruit);

% Appliquer un filtre moyenneur de taille 9
H_moyenne = fspecial('average', 9); % filtre moyenneur
figure; title('Filtre Moyenneur');
J = imfilter(I_bruit, H_moyenne); % Appliquer le filtre

% Afficher l'image originale, l'image bruitée et l'image filtrée
subplot(1, 3, 1), imshow(I), title('Image originale');
subplot(1, 3, 2), imshow(I_bruit), title('Image bruitée');
subplot(1, 3, 3), imshow(J, []), title('Image filtrée');


%%%%%%%%% Filtre Gaussien --------------------------------------------------
% Appliquer un filtre gaussien de taille 3x3 et écart-type 1
H_gaussian = fspecial('gaussian', [3 3], 1); % Filtre gaussien
figure; title('Filtre Gaussienne');
J_gaussian = imfilter(I_bruit, H_gaussian); % Appliquer le filtre gaussien

% Afficher l'image originale, l'image bruitée et l'image filtrée
subplot(1, 3, 1), imshow(I), title('Image originale');
subplot(1, 3, 2), imshow(I_bruit), title('Image bruitée');
subplot(1, 3, 3), imshow(J_gaussian, []), title('Image filtrée avec filtre gaussien');


%%%%%%%%% Filtre Mediane--------------------------------------------------
% Appliquer un filtre médian de taille 3x3
I_gray = rgb2gray(I); % Convertir l'image origine en niveaux de gris
I_bruit_gray = rgb2gray(I_bruit); % Convertir l'image bruitée en niveaux de gris

figure; title('Filtre Mediane');
J_m = medfilt2(I_bruit_gray, [3 3]); % Appliquer le filtre médian sur l'image bruitée

% Afficher l'image originale, l'image bruitée et l'image filtrée
subplot(1, 3, 1), imshow(I_gray), title('Image originale');
subplot(1, 3, 2), imshow(I_bruit_gray), title('Image bruitée');
subplot(1, 3, 3), imshow(J_m, []), title('Image filtrée');


%Filtre moyenne : En utilisant un filtre de moyenne de taille variable, vous pouvez observer 
% l'impact sur la suppression du bruit. Cependant, ce filtre aura tendance à flouter l'image,
% surtout si la taille de la fenêtre est grande. Il peut ne pas être assez efficace pour éliminer 
% les points noirs et blancs du bruit "sel et poivre", tout en réduisant les détails de l'image.

%Filtre gaussien : Ce filtre est plus performant que le filtre moyen pour conserver les détails 
% tout en lissant les zones bruitées. Cependant, il peut aussi flouter les bords de l'image et 
% n'est pas toujours aussi efficace qu'un filtre médian pour le bruit "sel et poivre".

% Le filtre médian est généralement plus efficace pour supprimer le bruit "sel et poivre" 
% que les filtres précédents. En ajustant la taille de la fenêtre, vous pouvez trouver un bon 
% compromis entre suppression du bruit et conservation des détails.


% Conclusion :
% Filtres de moyenne et gaussiens : Moins efficaces pour le bruit "sel et poivre", 
% ils peuvent flouter les détails de l'image et ne suppriment pas toujours complètement le bruit.

% Filtre médian : Le meilleur choix pour le bruit "sel et poivre", surtout avec des tailles 
% de fenêtres adaptées. Il conserve mieux les détails et élimine le bruit de manière plus ciblée.