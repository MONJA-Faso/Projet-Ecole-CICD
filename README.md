# Projet CI/CD Java

## Description

Projet pédagogique démontrant :

- Héritage en Java
- Tests
- Gestion de version avec Git
- Conteneurisation avec Docker
- Orchestration avec Docker Compose
- Intégration Continue avec Jenkins

## Structure

src/
- Animal.java
- Chien.java
- Main.java

test/
- AnimalTest.java

## Exécution locale

javac src/*.java
java -cp src Main

## Exécution des tests

javac src/*.java test/*.java
java -cp src:test AnimalTest

## Docker

docker build -t projet-cicd .
docker run --rm projet-cicd

## Docker Compose

docker compose up --build