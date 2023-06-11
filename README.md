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

## Kubernetes with istio and proxy
1. Start the cluster via `minikube start`
2. Open the dashboard via `minikube dashboard` (Optional)

   (Optional block start)

3. [Download istio](https://istio.io/latest/docs/setup/getting-started/#download) and add the environment variable to path
4. Install istio `istioctl install --set profile=demo -y`
5. Enable istio for upcoming deployments `kubectl label namespace default istio-injection=enabled`
6. Run `kubectl apply -f samples/addons` **in the folder where you unzipped the istio download**
7. Build the microservices `./gradlew build`
8. Build the microservice images `./docker-build.sh` (change the dockerhub account in /k8s-config/microservices.yaml if required)

   (Optional block end)

9. Apply the database secrets `kubectl apply -f k8s-config/secret/secret.yaml` (from root path)
10. Start the database `kubectl apply -Rf k8s-config/db`
11. Start the deployments `kubectl apply -Rf k8s-config/deployment`
12. Start the proxy `kubectl apply -Rf k8s-config/proxy` (it is important to wait till phpmyadmin container was created)
13. `kubectl port-forward deployment/apache 8080:80`
14. Start prometheus `kubectl -n istio-system port-forward deployment/prometheus 9090:9090`
15. Start grafana `kubectl -n istio-system port-forward deployment/grafana 3000:3000`
16. Start kiali `kubectl -n istio-system port-forward deployment/kiali 20001:20001`

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
