#!/bin/sh

echo '---------------------------------'
echo 'go to eureka server'
cd ./eureka-server/
echo 'deploying and creating service for eureka-server'
kubectl apply -f deployment.yml;kubectl apply -f service.yml;
echo '---------------------------------'

echo '---------------------------------'
echo 'Zipkin Server - deployment and service'
kubectl apply -f zipkin-kube.yml;
echo '---------------------------------'

echo '---------------------------------'
echo 'go to ratings-data-service'
cd ../ratings-data-service/
echo 'deploying ratings-data'
kubectl apply -f deploy.yml
echo '---------------------------------'

echo '---------------------------------'
echo 'go to movie-info-service'
cd ../movie-info-service/
echo 'deploying movie-info'
kubectl apply -f deploy.yml
echo '---------------------------------'

echo '---------------------------------'
echo 'go to movie-catalog-service'
cd ../movie-catalog-service/
echo 'deploying movie-catalog'
kubectl apply -f deploy.yml
echo '---------------------------------'

echo '---------------------------------'
echo 'creating service for movie-catalog'
kubectl apply -f service.yml
echo '---------------------------------'
cd ../
sh status.sh
