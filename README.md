## Projet Info : NetComics

# Objectif du projet :

L'objectif du projet est de crée une application de gestion et de recherche de Comics. 
Deux modes principaux d'utilisation sont possibles. ( Alice et Bob )

1 ) Mode Alice (Recherche simple) :

Sans compte il est possible de rechercher des informations diverses sur :
- des personnages
- des comics 
- des séries de comics
En cliquant sur l'image on peut avoir des informations beaucoup plus détaillés sur la recherche éffectuée.

2 ) Mode Bob (Gestion de la collection) :

Avec un compte en plus de toutes les facultés précédemment évoquées, il est possible de gérer sa collection et d'y ajouter des comics. Il est également possible de mettre à jour leur statut ( lu, possédé ou les deux).

3) Les suggestions / Recommandations :

Pour ces deux modes de fonctionnement l'utilisateur à accès à des suggestions orientés :

Si il n'est pas connecté il a des suggestions basé sur les derniers ajout de l'application et les comics les plus tendances sur l'application.

Si il est connecté les suggestions et que sa collection n'est pas vide, il aura également des suggestions basé sur ses goûts.



# Installation de la Base de données :
Après avoir téléchargé notre projet vous constaterez qu'il ne fonctionne pas. 
En effet, pour mener à bien l'accomplissement de ce projet nous avons du utilisé une base de données locale.
Il faut donc l'installer la BDD afin de pouvoir faire fonctionner l'application.
Voici le tuto d'installation :

1) Installation de MySQL Workbench :
 https://dev.mysql.com/downloads/installer/ 

2) Commande :

CREATE DATABASE prinfo7;
USE prinfo7;
CREATE USER 'prinfo'@'localhost' IDENTIFIED BY 'prinfo';
GRANT ALL PRIVILEGES ON *.* TO 'prinfo'@'localhost';

3) Configuration :

On appuie sur le + et on rempli les champs de la manière suivante :

![alt Capture d'ecran du remplissage](https://i.ibb.co/njbDYGB/Capture-d-cran.png)

4) Importation :

Dans la partie "Navigator" on clique sur "DataImport".
On ecrit le chemin jusqu'à l'endroit ou se situe le projet on va ensuite dans BDD, on clique sur "Load Folder Contents" et on coche "prinfo".
Enfin on clique sur "Start Import" et c'est tout !

![alt Capture fenetre](https://i.ibb.co/Gxyc947/Capture2.png)

L'application est maintenant fonctionnelle

# Contributors:
- Valentine GILLES
- Justine BARRANGER-CANDAS
- Cyril BIER
- Alexi DESVIGNES-NOUVEL
- Nathan Prud'homme
- Sarah-Marie JULES
