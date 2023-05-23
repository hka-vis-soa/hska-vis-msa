#!/bin/sh
docker build --tag=category-service category-service
docker tag category-service $DOCKER_ACCOUNT/category-service:latest
docker push $DOCKER_ACCOUNT/category-service

docker build --tag=product-service product-service
docker tag product-service $DOCKER_ACCOUNT/product-service:latest
docker push $DOCKER_ACCOUNT/product-service