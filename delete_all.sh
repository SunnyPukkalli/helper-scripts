#!/bin/sh

kubectl delete deployment.extensions/movie-catalog deployment.extensions/movie-info deployment.extensions/ratings-data service/movie-catalog

kubectl delete deployment.extensions/eureka-server deployment.extensions/zipkin service/eureka-server service/zipkin-server
