!/bin/sh

if [ -z "$DOCKER_ACCOUNT" ]; then
    echo "Variable DOCKER_ACCOUNT required. Use 'export DOCKER_ACCOUNT=my_account'"
    exit 1
fi

docker build --tag=category-service category-service
docker tag category-service $DOCKER_ACCOUNT/category-service:latest
docker push $DOCKER_ACCOUNT/category-service

docker build --tag=product-service product-service
docker tag product-service $DOCKER_ACCOUNT/product-service:latest
docker push $DOCKER_ACCOUNT/product-service