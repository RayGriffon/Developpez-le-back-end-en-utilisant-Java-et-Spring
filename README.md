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
- Une base de données MySQL .

## Installation

L'application à cloner se trouve ici : https://github.com/RayGriffon/Developpez-le-back-end-en-utilisant-Java-et-Spring
La base de donnée utilise le port 3306.

Dans le fichier application.properties se trouve les variables configurables suivantes :
- Pour la BDD : username, password
- Pour le token : la secret key
- Pour l'upload d'images : le lien où elles sont enregistrées

## Architecture

- **Backend** : Spring Boot, avec JPA pour la gestion des entités et MySQL comme base de données.
- **Frontend** : Angular.
- **Authentification** : JWT pour sécuriser les endpoints.

## Utilisation

La documentation swagger est accessible ici : http://localhost:3001/api/swagger-ui/index.html

Après avoir démarrer la partie angular (npm start), vous pouvez accéder à l'application ici : http://localhost:4200/
