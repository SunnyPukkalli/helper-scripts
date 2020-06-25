#!/bin/sh

kubectl delete deployment.extensions/movie-catalog deployment.extensions/movie-info deployment.extensions/ratings-data service/movie-catalog
