# Polydoctor backend MEP

## Description

Projet fullstack 5A - MEP `v0.1 alpha`

> Accéder au projet sur [Docker Hub](https://hub.docker.com/repository/docker/heatsinkru/polydoctor-backend/general)

## Installation

### Copie du dépôt en local

```shell
git clone https://github.com/heatsinkru/polydoctor-backend.git && \
cd polydoctor-backend
```

### Effectuer un build-from-source en local

```shell
./gradlew build
```

> Le fichier `.jar` se site à l'emplacement `build/libs/covid-api-0.0.1-SNAPSHOT.jar`

#### Étape bonus

##### Il est possible d'exécuter l'application localement, il faut cependant disposer d'une installation de Java ainsi que d'une base de données postgresql.

Lancement de l'application `.jar`

```shell
java -jar build/libs/covid-api-0.0.1-SNAPSHOT.jar
```

> L'application sera alors disponible sur votre navigateur à l'adresse http://localhost:8080/

### Démarrage du docker en local

```shell
docker compose up --build -d
```

> L'application sera alors disponible sur votre navigateur à l'adresse http://localhost:8080/


### Installation depuis le dépôt DockerHub

```shell
docker pull heatsinkru/polydoctor-backend:latest && \
docker run heatsinkru/polydoctor-backend
```

> L'application sera alors disponible sur votre navigateur à l'adresse http://localhost:8080/

### Utilisation d'une pipeline Jenkins

#### Etape 1 : Installation et configuration de Jenkins

> Pour en savoir plus, consultez : https://github.com/jredel/jenkins-compose.git

#### Etape 2 : Création d'un compte DockerHub, d'un dépôt "polydoctor-backend" public et récupération des informations de connexion

> Lien vers le dépôt DockerHub : https://hub.docker.com/repository/docker/heatsinkru/polydoctor-backend/general

#### Etape 3 : Ajout des identifiants DockerHub dans Jenkins

> Se rendre dans le chemin : `Dashboard > Manage Jenkins > Credentials > System > Global credentials`, puis ajouter vos identifiants sous l'ID `docker-hub`

#### Etape 4 : Création d'une pipeline

##### Ajout du code suivant dans le script : 

```groovy
node {
  def app

  try {
    stage("Clone repository"){
      git url: 'https://github.com/heatsinkru/polydoctor-backend.git', branch: 'main'
    }

    stage("Build image"){
      app = docker.build("heatsinkru/polydoctor-backend")
    }

    stage("Test image"){
      test = sh 'curl -Is http://localhost:8080/public/centers | head -1'

      if(test == "HTTP/1.1 200 OK"){
        sh 'echo "Tests passed"'
      } else {
        sh 'echo "Tests failed"'
        currentBuild.result = 'FAILURE'
      }
    }

    stage("Push image"){
      docker.withRegistry('https://registry.hub.docker.com', 'docker-hub') {
        app.push("0.1-${env.BUILD_NUMBER}")
        app.push("latest")
      }
    }
  } catch (e) {
    echo 'Error occurred during build process!'
    echo e.toString()
    currentBuild.result = 'FAILURE'
  }
}
```

#### Etape 5 : Lancement du script

> 2 images docker sont alors publiées sur le dépôt avec les tags `latest` et `0-1-{build}`

### Vérification du fonctionnement

#### Récupération de la liste des centres de vaccination

##### Requête :

- Endpoint : `/public/centers/`
- Methode : `GET`

##### Résultat :

- Status : `200`
- Data :

```json
[
  {
    "id": 1,
    "name": "Centre des Congrès Prouvé",
    "address": "2 bis Place Thiers",
    "city": "Nancy",
    "postalCode": "54000",
    "rdvs": []
  },

  ...
]
```

> Par défaut, seul le centre créé à l'initialisation sera affiché

#### Ajout d'un centre de vaccination

##### Requête :

- Endpoint : `/public/center/`
- Methode : `POST`
- Data :

```json
{
    "name": "Polytech Nancy",
    "address": "8 rue Jean Lamour",
    "city": "Vandoeuvre-lès-Nancy",
    "postalCode": "54500"
}
```

##### Résultat :

- Status : `201`

## Auteur du travail de MEP

- `Samuel DITTE-DESTRÉE`

## Auteurs du backend

- `Samuel DITTE-DESTRÉE`
- `Theo RUSINOWITCH`
- `Lucie GARNIER`