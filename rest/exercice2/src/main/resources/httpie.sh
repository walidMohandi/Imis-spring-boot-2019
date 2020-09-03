#!/usr/bin/env bash
# HOST
# deploy : export HOST=192.168.47.164:8080
# ou pour test deploy local
export HOST=localhost:8080


# Récupération de la liste de tous les enseignants
http GET $HOST/ade/enseignants
