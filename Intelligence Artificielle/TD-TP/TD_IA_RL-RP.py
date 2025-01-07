import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

#Tâche 1 :
###### Exploitez la base de données "possum.csv". Préparez cette base de données de manière à ce qu’elle soit correctement nettoyée.
###### Puis affichez les données des descripteurs ciblés

# Charger la base de données
df = pd.read_csv('possum.csv')

# Afficher les premières lignes du DataFrame pour vérifier son contenu
print(df.head())

# Vérification des informations sur les colonnes
print(df.info())

# Affichage des descripteurs ciblés : footlength et earconch
df_descriptors = df[['footlgth', 'earconch']]
print(df_descriptors.head())

# Vérification des valeurs manquantes
print(df_descriptors.isnull().sum())

# Suppression des lignes avec des valeurs manquantes
df_clean = df_descriptors.dropna()

# Vérification
print(df_clean.isnull().sum())

print(df_clean.head())
print(df_clean.dtypes)

# Visualisation de la relation entre footlength et earconch avec un scatter plot
footlgth = df_clean['footlgth']
earconch = df_clean['earconch']
plt.scatter(footlgth, earconch)
plt.title('Relation entre footlgth et earconch')
plt.xlabel('Longueur d''empreinte')
plt.ylabel('Taille pavillon de l''oreille')
plt.show()