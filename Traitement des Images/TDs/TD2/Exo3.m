I = imread('ULCO.jpg');
figure

subplot(2,3,1);
imshow(I);
title('Original');

H3 = fspecial('average', 3);
H5 = fspecial('average', 5);
H7 = fspecial('average', 7);
H9 = fspecial('average', 9);
H11 = fspecial('average', 11);

subplot(2,3,2);
J3 = imfilter(I, H3);
imshow(J3); title('Average Filter 3');

subplot(2,3,3);
J5 = imfilter(I, H5);
imshow(J5); title('Average Filter 5');

subplot(2,3,4);
J7 = imfilter(I, H7);
imshow(J7); title('Average Filter 7');

subplot(2,3,5);
J9 = imfilter(I, H9);
imshow(J9); title('Average Filter 9');

subplot(2,3,6);
J11 = imfilter(I, H11);
imshow(J11); title('Average Filter 11');

%---------Filtre Gaussien

figure

subplot(2,2,1);
imshow(I);
title('Original');

Hg1 = fspecial('gaussian', 5, 1);
Hg5 = fspecial('gaussian', 5, 5);
Hg9 = fspecial('gaussian', 9, 5);

subplot(2,2,2);
Jg1 = imfilter(I, Hg1);
imshow(Jg1); title('Gaussian Filter 1');

subplot(2,2,3);
Jg5 = imfilter(I, Hg5);
imshow(Jg5); title('Gaussian Filter 5');

subplot(2,2,4);
Jg9 = imfilter(I, Hg9);
imshow(Jg9); title('Gaussian Filter 9');