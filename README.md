# Chatop

Une application permettant de connecter les utilisateurs avec des propriétaires pour discuter et réserver des locations.

## Description

Chatop est une plateforme de location et de messagerie qui permet aux utilisateurs de :

- Se connecter via email et mot de passe pour accéder à leur compte.
- Envoyer des messages aux propriétaires de biens immobiliers.
- Visualiser les locations disponibles.
- Créer des locations

L'application utilise **Spring Boot** pour le backend et **Angular** pour le frontend.

## Prérequis

Avant de commencer, assurez-vous d'avoir les outils suivants installés :

- [Java 21+](https://adoptium.net/)
- [Node.js 18+](https://nodejs.org/)
- [Angular 14+](https://angular.io/cli)
- Une base de données MySQL. (Le script se trouve ici : https://github.com/RayGriffon/Developpez-le-back-end-en-utilisant-Java-et-Spring/blob/main/ressources/sql/script.sql)

## Installation

L'application à cloner se trouve ici : https://github.com/RayGriffon/Developpez-le-back-end-en-utilisant-Java-et-Spring
La base de donnée utilise le port 3306.

Il faut configurer en variable d'environnement les trois données suivantes :
- Pour la BDD : username (DATABASE_USERNAME), password (DATABASE_PASSWORD)
- Pour le token : la secret key (JWT_SECRET)

Dans le fichier application.properties se trouve les variables configurables suivantes :
- Pour l'upload d'images : le lien où elles sont enregistrées : **picture/upload**
- Le lien vers la base de donnée : **spring.datasource.url**

## Architecture

- **Backend** : Spring Boot, avec JPA pour la gestion des entités et MySQL comme base de données.
- **Frontend** : Angular.
- **Authentification** : JWT pour sécuriser les endpoints.

## Utilisation

La documentation swagger est accessible ici : http://localhost:3001/api/swagger-ui/index.html

Après avoir démarrer la partie angular (npm start), vous pouvez accéder à l'application ici : http://localhost:4200/
