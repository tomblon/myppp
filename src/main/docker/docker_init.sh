#!/usr/bin/env bash

check=$( sudo docker ps -a -f "name=mypp-postgresql" | tail -n +2 )
if [ -z "$check" ]; then
  docker build -t skysurferone/postgres_mypp .
  docker run --name mypp-postgresql -d -p 5432:5432 skysurferone/postgres_mypp
fi

docker start mypp-postgresql
