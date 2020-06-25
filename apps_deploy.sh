#!/bin/sh

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
echo 'creating service for movie-catalog'
kubectl apply -f service.yml
echo '---------------------------------'

sh status.sh

