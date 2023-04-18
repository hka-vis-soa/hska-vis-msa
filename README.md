# Verteilte Systeme Labor 
## Summer semester 2023
## Overview

## Required Installation

- Java 17 or higher
- Gradle
- Docker
- Docker-compose

## Microservices

[TODO]

## Getting Started

In every Microservice's application.properties file the spring profile has to be selected to run locally or via docker container.

The following steps are required for a deployment via docker containers.

1. Navigate to the local folder where the Studrive application is located.
2. `./gradlew build`
3. `docker-compose up`

To stop the application simply write `docker-compose` down

## Add more microservices
1. Create a new directory in the root directory **hska-vis-msa**.
2. Add the new directory name to the **settings.gradle** file in the root directory
3. Add the **build.gradle** file to the new directory
4. Add the path src/main/java
5. Now you can rebuild for example the user-service with a resource folder... 


## Useful tools

**pgadmin4**: Admin Interface for PostgreSQL database. 

Can be found here http://localhost:5050/. 

To access the admin view of the database, login with **admin@eshop.de** and **admin**. After that click **Add new server**. Enter a name and switch to the **connection** tab. There you need to check the IP address of the postgres container with the command `docker inspect postgresdb`, at the end of the command output you will find the **IPAddress**. Enter the username **root** and password **root** and click **Save** to log in.   
