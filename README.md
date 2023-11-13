# Polydoctor backend MEP

## Description

#### Polydoctor backend `v0.1 alpha`
#### Projet fullstack 5A - MEP

## Installation

### Copie du dépôt en local

```shell
git clone https://github.com/heatsinkru/polydoctor-backend.git && \
cd polydoctor-backend
```

### Démarrage du docker

```shell
docker compose up --build -d
```

> L'application sera alors disponible sur votre navigateur à l'adresse http://localhost:8080/

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