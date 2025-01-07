% Charger l'image
img = imread('pout.tif');
imshow(img); title('Image originale');

% Afficher l'histogramme de l'image originale
figure;
subplot(1,2,1);
imshow(img); title('Image originale');
subplot(1,2,2);
imhist(img); title('Histogramme de l image originale');

% Appliquer l'égalisation d'histogramme pour améliorer le contraste
img_contraste = histeq(img);
figure;
subplot(1,2,1);
imshow(img_contraste); title('Image avec contraste amélioré');

subplot(1,2,2);
imhist(img_contraste); title('Histogramme de l image avec contraste ameliore');


figure,
subplot(1,2,1);
J= imadjust(img);
imshow(J);
subplot(1,2,2);
imhist(J);