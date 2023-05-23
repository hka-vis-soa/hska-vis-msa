#!/bin/sh

#if [ -z "$DOCKER_ACCOUNT" ]; then
#    echo "Variable DOCKER_ACCOUNT required. Use 'export DOCKER_ACCOUNT=my_account'"
#    exit 1
#fi

# DO NOT USE THIS FILE! (outdated and incomplete)
# Use
#   kubectl apply -f microservices.yaml
# instead.

kubectl create deployment postgres --image=postgres:latest --port 5432
kubectl expose deployment postgres --target-port 5432

kubectl create deployment pgadmin --image=dpage/pgadmin4:latest --port 80
kubectl expose deployment pgadmin --target-port 5050

kubectl create deployment category-service --image=hska-vis-msa-category-service:latest --port 8080
kubectl expose deployment category-service --target-port 8000

kubectl create deployment product-service --image=hska-vis-msa-product-service:latest --port 8080
kubectl expose deployment product-service --target-port 8001 

kubectl create deployment web-shop-db-image --image=mavogel/hska-vis-web-shop-db-image:latest --port 3306
kubectl expose deployment web-shop-db-image --target-port 3306

kubectl create deployment legacywebshop --image=mavogel/hska-vis-legacywebshop:latest --port 8080
kubectl expose deployment legacywebshop --target-port 8888