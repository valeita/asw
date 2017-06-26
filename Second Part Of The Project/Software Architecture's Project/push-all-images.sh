#!/bin/bash

source "docker.env"

# DOCKER_REGISTRY=localhost:5000
DOCKER_REGISTRY=swarm.inf.uniroma3.it:5000

docker push ${DOCKER_REGISTRY}/eureka-server-img-9012
docker push ${DOCKER_REGISTRY}/course-img-9012
docker push ${DOCKER_REGISTRY}/faculty-img-9012
docker push ${DOCKER_REGISTRY}/university-img-9012
docker push ${DOCKER_REGISTRY}/infouni-img-9012