#!/bin/bash

# Clean existing Docker processes
docker stop $(docker ps -q)
docker rm amqbroker
docker rm mongodb

# Start MongoDB server & AMQ broker
docker run -d -p 27017:27017 --name mongodb dockerfile/mongodb
docker run -d -v /tmp/amqbroker:/var/activemq --name amqbroker fabric8/fabric8-mq:hk.1

# Build images for Camel routes
mvn clean install

# Start containers with Camel routes
docker run -d -p 18080:18080 --link amqbroker:amqbroker apachecon/apachecon-demo-router-inendpoint:1.0-SNAPSHOT
docker run -d --link mongodb:mongodb --link amqbroker:amqbroker apachecon/apachecon-demo-router-processor:1.0-SNAPSHOT
