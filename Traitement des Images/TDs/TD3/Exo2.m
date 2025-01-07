load('parking.mat');

figure;
subplot(1, 2, 1);
imshow(I);

title('Image du parking (I)');
subplot(1, 2, 2);
imshow(uint8(X));
title('Image de référence du véhicule (X)');

% Corrélation
C = CorrImage(I, X); 
figure;
subplot(1, 2, 1);
imshow(C, []);

title('Score de corrélation (RGB)');
subplot(1, 2, 2);
mesh(C);
title('Score de corrélation (RGB) - 3D');


J = colorspace('rgb->luv', im2double(I)); 
figure;
imshow(J);title('imshow J');
C_luv = CorrImage(J, X,1); 
figure;
subplot(1,3,1);

imshow(C_luv);title('C luv');
subplot(1,3,2);
mesh(C_luv);

subplot(1,3,3);
[P, C_luv_s] = DetectPic(C_luv, 0.5);
imshow(C_luv_s);

