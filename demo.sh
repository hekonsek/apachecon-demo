#!/bin/bash

cowsay Building the demo!

cowsay Stopping and cleaning existing Docker containers...
# Clean existing Docker processes
docker stop $(docker ps -q)
docker rm amqbroker
docker rm mongodb

cowsay Starting Docker containers of MongoDB and ActiveMQ...
# Start MongoDB server & AMQ broker
docker run -d -p 27017:27017 --name mongodb dockerfile/mongodb
docker run -d -v /tmp/amqbroker:/var/activemq --name amqbroker fabric8/fabric8-mq:2.0.24

cowsay Building microservices...
# Build images for Camel routes
mvn clean install

cowsay Starting microservices...
# Start containers with Camel routes
docker run -d -p 18080:18080 --link amqbroker:amqbroker apachecon/apachecon-demo-router-inendpoint:1.0-SNAPSHOT
docker run -d --link mongodb:mongodb --link amqbroker:amqbroker apachecon/apachecon-demo-router-processor:1.0-SNAPSHOT

cowsay "Demo is up and running. Go get'em tiger!"