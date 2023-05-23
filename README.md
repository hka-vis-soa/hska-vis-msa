# Verteilte Systeme Labor 
## Summer semester 2023
## Overview

## Required Installation

- Java 17 or higher
- Gradle
- Docker
- Docker-compose

## Microservices

- [category-service](category-service): Management of categories
- [product-service](product-service): Management of products

## Swagger UI

API documentation is provided via Swagger UI.
- [category-service](category-service):   http://localhost:8000/swagger-ui/index.html
- [product-service](product-service):     http://localhost:8001/swagger-ui/index.html

To create a Swagger UI for future microservices, the [openapi-config](openapi-config) module must be imported to the respective service.

## Getting Started

In every Microservice's application.properties file the spring profile has to be selected to run locally or via docker container.

The following steps are required for a deployment via docker containers.

1. Navigate to the local folder where the application is located.
2. `./gradlew build`
3. `docker-compose up`

To stop the application simply write `docker-compose down`.

## Kubernetes

To deploy a kubernetes cluster, use `kubectl apply -Rf k8s-config`.

## Add more microservices
1. Create a new directory in the root directory **hska-vis-msa**.
2. Add the new directory name to the **settings.gradle** file in the root directory
3. Add the **build.gradle** file to the new directory
4. Add the path src/main/java
5. Now you can rebuild for example the [category-service](category-service) with a resource folder... 

## Build docker image & push it to Docker Hub
* Create
[Docker Hub](https://hub.docker.com/) account.

* Log into your created docker hub account on the command line via `docker login`

* Assign your account name to the `DOCKER_ACCOUNT` environment variable.

* Run the `docker-build.sh` shell script. It builds and pushes the docker images
to the docker hub.

## Useful tools

**pgadmin4**: Admin Interface for PostgreSQL database. 

Can be found here http://localhost:5050/. 

To access the admin view of the database, login with **admin@eshop.com** and **admin**. Then click **Add new server**. Enter a name and switch to the **connection** tab. You can use the postgres container name **postgresdb** as the Host name/address. Finally, enter the username **admin** and password **admin** and click **Save** to login.   
