% 1-
% Charger l'image
I = imread('coloredChips.png');

load('stat1.mat');
% Exécuter la classification bayésienne pour obtenir le vecteur ID
[A, ID, centers] = Cb(I, M, S);  % ID est le vecteur des étiquettes de classe pour chaque pixel

% Extraire les composantes R, G et B
R = I(:,:,1);  % Composante rouge
G = I(:,:,2);  % Composante verte
B = I(:,:,3);  % Composante bleue

% Convertir chaque composante en un vecteur colonne
R = R(:);
G = G(:);
B = B(:);

% Matrice X, chaque ligne correspond à un pixel et chaque colonne à une composante couleur (R, G, B)
X = [R, G, B];

disp(['Taille de la matrice X: ', num2str(size(X))]);

% 2-

figure;
colors = ["yellow","red","green","blue","cyan","black","magenta"];
hold on;
for i = 1:7
    idx = (ID == i);
    plot3(X(idx,1), X(idx,2), X(idx,3), colors(i));
end
hold off;
xlabel("Red");
ylabel("Green");
zlabel("Blue");
