# Projet L3 serveur web

Le but de ce projet est de permettre aux étudiants de L3 informatique d'appliquer les bonnes pratiques de conception orientée objet et les patrons de conception vus en cours dans le cadre d'un projet en Java.

Une partie des étudiants va faire son stage de L3 avec un cadriciel web. D'où la nécessité de comprendre comment fonctionne un serveur web, en concevant pas à pas un serveur web dynamique en Java.

## Le travail à réaliser

Le projet se déroule en plusieurs étapes :

- Tout d'abord, il s'agit de créer d'un serveur web permettant de répondre à la requête HTTP GET, en servant des contenus statiques (page HTML, images, css, js). Le port d'écoute (80 par défaut) et le répertoire contenant les données à servir devront être paramétrables.  
  - Attention à bien implémenter la réponse HTTP (qui est traitée par un navigateur classique).
  - Attention aux types MIME pour les différents types de ressources.
  - On utilisera le patron de conception proxy pour permettre d'éviter d'afficher les images.
  - On utilisera ImageIO pour manipuler les fichiers images.

- Dans un second temps, il s'agit d'ajouter le support des pages dynamiques, en utilisant le cadriciel [freemarker](http://freemarker.apache.org).  
  - On supportera la requête HTTP GET pour récupérer les valeurs d'un formulaire.
  - Ces valeurs seront disponibles au niveau des templates
  - On utilisera un cookie d'une durée de 15 minutes pour gérer des sessions utilisateur : les valeurs des formulaires seront disponibles dans la session.



## Quelles sont les difficultés ?

- Partie 1 : bien créer un thread pour répondre à chaque requête HTTP, pour supporter une charge minimale (un nombre de connexions simultanées).

- Partie 2 : comprendre le fonctionnement de freemarker.

- En général : bien comprendre le protocole HTTP et ses subtilités.


## Evaluation du projet 

10 points de fonctionnalités et 5 points de conception (application des principes et patrons de conception vus en cours).

Fonctionnalités : 10 points

- [ ] servir une page web html simple sur un port et un chemin donné (3 points)
  - [ ] Les pages sont servies en parallèle (utilisation des threads Java)
  - [ ] Les pages qui existent sont affichées
  - [ ] Les pages qui n'existent pas retournent un code d'erreur 404
  - [ ] Si aucune ressource explicite n'est demandée, on cherche `index.html`
  - [ ] le port et le chemin des pages web sont précisés en ligne de commande
- [ ] servir une page web html contenant une image (formats jpg ou png)
- [ ] servir une page web html contenant un fichier de style (css) et du javascript (.js)
- [ ] implémenter le patron de conception proxy pour ne pas afficher les images lors de la navigation (on mettra un X rouge à la place de l'image)
- [ ] Déposer un cookie d'une durée de 15 minutes dans le navigateur, le récupérer si il existe
- [ ] afficher une page web à l'aide d'un template freemarker et des données en dur dans l'application
- [ ] afficher une page web à l'aide d'un template freemarker et des données qui viennent d'un formulaire dans un contexte global (le même pour toutes les connexions)
- [ ] afficher une page web à l'aide d'un template freemarker et des données qui viennent d'un formulaire dans un contexte de session (une session spécifique pour chaque utilisateur)


Conception : 5 points

*La conception dépend des fonctionnalités réalisées. Plus il y a de fonctionnalités, plus la conception est difficile. La note de conception ne peut pas excéder la moitié de la note de fonctionnalité. Par exemple, un étudiant qui a réalisé la moitié des fonctionnalités ne pourra pas avoir plus de 2,5/5 en conception.*

La note de conception prend en compte les critères suivants :

- Application des principes fondamentaux de programmation orientée objet
- Conception orientée objet (patrons de conception, principes vus en cours et TP)
- Respect des conventions de codage en java
- Explication de la conception

## A propos du travail de groupe

Vous allez utiliser GitLab pour réaliser votre projet en binôme. La réalité du travail des deux binômes sera vérifiée par l'existence de *commits* (validations) des deux membres du binôme. Une absence de commits sera considérée comme une absence de travail d'un membre du binôme.

## Utilisation de ChatGTP ou copilot

Les exemples proposés pour tester certaines fonctionnalités ont été générés par ChatGTP. Vous êtes libres d'utiliser ChatGTP ou copilot pour vous aider à réaliser ce projet. Cependant, vous devez être capable d'expliquer le travail réalisé. Il parait préférable d'utiliser la documentation officielle de Java ou une simple requête sur un moteur de recherche qui vous donne des sources dont vous pouvez vérifier la fiabilité.

## Proposition de calendrier d'avancement pendant les séances TP

- Semaine 7 : analyse d'une requête HTTP GET, et réponse pour des fichiers HTML simples en utilisant les threads Java.
- Semaine 8 : ajout du support des images, des fichiers css et js.
- Semaine 9 : implémentation du patron de conception proxy pour ne pas charger les images, affichage d'une page freemarker.
- Semaine 10 : gestion des formulaires, ajout de la notion de session.

## Configuration de git à la faculté des sciences

La première fois que vous utilisez git, il faut vous identifier auprès du client git en fournissant une adresse email et un nom :

```
$ git config --global user.email prenom_nom@ens.univ-artois.fr
$ git config --global user.name "Prenom Nom"
```

Lorsque vous êtes à la faculté des sciences, il faut indiquer à git que l'on utilise un proxy.

Il faut soit déclarer une variable d'environnement `https_proxy` :

```
export https_proxy=http://cache-etu.univ-artois.fr:3128
```

Cette solution est globale et indépendante de git. Elle est obtenue directement
sous Ubuntu si les serveurs mandataires sont correctement configurés.

L'autre solution est de définir le proxy dans la configuration de git.

```
$ git config --global http.proxy http://cache-etu.univ-artois.fr:3128
```

De plus, comme nous utilisons une connexion https, il faut augmenter la taille des messages HTTP :

```
$ git config --global http.postBuffer 524288000
```

Enfin, pour éviter de taper trop souvent votre mot de passe, vous pouvez indiquer à git de garder
vos identifiants dans un cache (pendant 15 minutes).

```
$ git config --global credential.helper cache
```

## Indices pour la semaine 1

Pour réaliser le travail de la première semaine, voici des sources d'information fiables :

- La page Wikipedia du [protocole HTTP](https://fr.wikipedia.org/wiki/Hypertext_Transfer_Protocol)
- L'utilisation de la classe [Thread](https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html) dans le tutoriel Java
- La classe utilitaire [Files](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html) en Java
