#!/bin/bash

echo 'Starting infoUni application as a stack' 

source "docker.env"

docker stack deploy --compose-file docker-stack-infoUni-9012.yml infoUni-9012
