#!/bin/sh
kubectl create deployment category --image=docker.io/$DOCKER_ACCOUNT/category-service:latest --port=8000
kubectl expose deployment category --port 8000 --port 5432
kubectl create deployment product --image=docker.io/$DOCKER_ACCOUNT/product-service:latest --port=8001
kubectl expose deployment product --port 8001 --port 5432