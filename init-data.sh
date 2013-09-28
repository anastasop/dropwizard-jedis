#!/bin/sh

curl -H"Content-Type: text/plain" -d"Balotelli,Kaka" http://localhost:8080/teams/Milan
curl -H"Content-Type: text/plain" -d"Iniesta, Messi" http://localhost:8080/teams/Barcelona
