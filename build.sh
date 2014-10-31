#!/bin/bash

mvn clean install
mvn -f apachecon-demo-router/pom.xml install docker:build

docker stop $(docker ps -q)
docker rm amqbroker

docker run -d -v /tmp/amqbroker:/var/activemq --name amqbroker fabric8/fabric8-mq:hk.1
docker run -d -p 18080:18080 --link amqbroker:amqbroker apachecon/apachecon-demo-router:1.0-SNAPSHOT