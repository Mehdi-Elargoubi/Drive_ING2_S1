%Question 1 :
I = imread('coloredChips.png');

%Question 2 :Dimensions + Composantes
[rows, columns, numColorChannels] = size(I);
disp(['Dimensions de l image : ', num2str(rows), ' x ', num2str(columns)]);
disp(['Nombre de composantes : ', num2str(numColorChannels)]);

%Question 3 : Extraire les composantes R, G et B
R = I(:,:,1);  % Composante Rouge
G = I(:,:,2);  % Composante Verte
B = I(:,:,3);  % Composante Bleue

% Affichage des images
figure;
% Image originale
subplot(2, 2, 1);  % Diviser la fenêtre en 2x2, première image
imshow(I); title('Image Couleur');

% Composante Rouge
subplot(2, 2, 2);  % Deuxième image
imshow(R); title('Composante Rouge');

% Composante Verte
subplot(2, 2, 3);  % Troisième image
imshow(G); title('Composante Verte');

% Composante Bleue
subplot(2, 2, 4);  % Quatrième image
imshow(B); title('Composante Bleue');

% matrices de composantes
R_img = cat(3, R, zeros(size(R)), zeros(size(R)));
G_img = cat(3, zeros(size(G)), G, zeros(size(G)));
B_img = cat(3, zeros(size(B)), zeros(size(B)), B);
% Affichage des composante
figure;
subplot(2,2,1), imshow(I), title('Image RGB');
subplot(2,2,2), imshow(R_img), title('Composante R (Rouge)');
subplot(2,2,3), imshow(G_img), title('Composante G (Vert)');
subplot(2,2,4), imshow(B_img), title('Composante B (Bleu)');


% Question 4 :

names={'pieces jaunes','pieces rouges','pieces vertes','pieces bleues','pieces oranges','stylo','fond'};
tb=10;
%[M,S]=BuildClass(I,tb,names);



%save stat3 M S


disp(M);
disp(S);