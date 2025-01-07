import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler


df = pd.read_csv("iris.csv")
print(df.shape) #taille de ma bd format 150 lignes , 6 colonnes
print(df.head()) # affiche les 5 premiers 
print(df.describe()) # description de la base de donnee  une fleure a des sepal et petal (longeur et largeur)  la derniere colonne est catégorielle( chaine de caracteristique)
print(df.info()) # donne des infos sur ma data frame , le type de chaque colonne 
print(df['Species'].unique()) # compter les occurrences des différentes valeurs dans une colonne.
print(df['Species'].value_counts()) # compter les occurrences des différentes valeurs dans une colonne.
# combien de classe : 3
# nbr de categoris de ma colonne target : 3 print(df['Species'].unique())
#les carteristiques descriptif 4 les features longeur largeueru du petal longueur largeru de sepal 
#combien dexemple : 150
#combien dexemple de chaque classe : 50 50 50 
#comment sont organise les exemples : les donnes ne sont pas mélonger , la proch etape : de prendre la data set et de la partager , 80% apprentissage 20% test
#on doit mélonger les donnes puis les separer , il a une fonction qui fait les deux au meme temps 

#récuperer les classe

features = ['SepalLengthCm','SepalWidthCm','PetalLengthCm','PetalWidthCm']
target = 'Species'

X = df[features]
y = df[target]

#standardiser les features
scaler = StandardScaler() # standariser les données
X_scaled = scaler.fit_transform(X) #calcule la moyen et lecart type de chaque colonne et transforme chaque valeur - la moyenne sur / l'ecart type

#découper les donées e, X_train et X_test , y_train , y_test
X_train , X_test , Y_train , Y_test = train_test_split(X_scaled,y,test_size = 0.8 , random_state = 0) # test size 20%  , random_state mélonger les données

print(X_train.shape)
print(X_test.shape)

from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import accuracy_score
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.metrics import confusion_matrix

knn = KNeighborsClassifier(n_neighbors=3) #nombre des plus proches voisins
knn.fit(X_train , Y_train)
#calculer la précision de mon classifieur knn sur les donnés d'apprentissage
print(knn.score(X_train,Y_train)*100)

#ou bien

Y_pred = knn.predict(X_test)
print(accuracy_score(Y_test,Y_pred))

classes = df['Species'].unique()

Y_pred = knn.predict(X_test)
matrice_confusion = confusion_matrix(Y_test , Y_pred)
print("Matrice de confusion : ")
print(matrice_confusion)
#afficher la matrice de confusion sous forme de heatmap

plt.figure(figsize=(8,6))
sns.heatmap(matrice_confusion , annot=True , fmt="d" , cmap ="Blues", xticklabels=classes , yticklabels =classes)

plt.xlabel("predictions")
plt.ylabel("vraies étiquettes")
plt.title("Matrice de confusion")
plt.show()