% 1) Création de l'image
I = [10 2 1 1 10 2 6;
     7 5 5 4 4 4 3;
     7 2 0 3 2 1 8;
     6 3 2 0 0 3 3;
     5 3 2 7 0 3 5];

% 2) Calcul de la dynamique
% La dynamique d'une image est définie par la différence entre la valeur maximale et la valeur minimale
dynamique = max(I(:)) - min(I(:));
I_min = min(I(:));
I_max = max(I(:));
% 3) Calcul de la valeur moyenne et de la variance
moyenne = mean(I(:)); % Moyenne des valeurs de l'image
variance = var(I(:)); % Variance des valeurs de l'image

% 4) Calcul de l'histogramme et de l'histogramme cumulé
h = histcounts(I, 0:10); % Histogramme avec des niveaux de gris entre 0 et 10
H = cumsum(h);           % Histogramme cumulé

% Affichage des résultats
disp('Histogramme h :');
disp(h);
disp('Histogramme cumulé H :');
disp(H);

% Pour visualiser l'histogramme et l'histogramme cumulé
figure;
subplot(1, 2, 1);
bar(0:9, h);
title('Histogramme h');

subplot(1, 2, 2);
stairs(0:9, H);
title('Histogramme cumulé H');


% 5) Vérification avec la fonction hist et affichage avec un pas de 1
dynamique = I_min:I_max; % Crée la plage de valeurs dynamiques
figure;
hist(I(:), dynamique); % Utilise la fonction hist avec un pas de 1
title('Vérification de l''histogramme avec la fonction hist');
xlabel('Niveaux de gris');
ylabel('Nombre de pixels');
grid on;

%---------------------------------------
% 6) Algorithme pour calculer l'histogramme et l'histogramme cumulé

% Initialisation de l'histogramme
h_manual = zeros(1, I_max - I_min + 1);

% Calcul de l'histogramme
for i = 1:numel(I)
    niveau = I(i) - I_min + 1; % Calcul de l'index basé sur I_min
    h_manual(niveau) = h_manual(niveau) + 1;
end

% Calcul de l'histogramme cumulé
H_manual = zeros(1, length(h_manual));
H_manual(1) = h_manual(1);
for k = 2:length(h_manual)
    H_manual(k) = H_manual(k-1) + h_manual(k);
end

% Affichage de l'histogramme et de l'histogramme cumulé
figure;
subplot(1, 2, 1);
bar(I_min:I_max, h_manual, 'FaceColor', 'b');
title('Histogramme calculé manuellement');
xlabel('Niveaux de gris');
ylabel('Nombre de pixels');

subplot(1, 2, 2);
stairs(I_min:I_max, H_manual, 'LineWidth', 2);
title('Histogramme cumulé calculé manuellement');
xlabel('Niveaux de gris');
ylabel('Nombre cumulé de pixels');
grid on;
%---------------------------------------
