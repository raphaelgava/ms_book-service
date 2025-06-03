# FROM openjdk:8-jdk-alpine
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]
FROM openjdk:21
ARG BOOK_JAR_FILE=ms_book-service/target/*.jar
#ARG BOOK_JAR_FILE_URL=./components/ms_book-service/target/*.jar
COPY ${BOOK_JAR_FILE} app.jar
#RUN if [ -f ${BOOK_JAR_FILE} ]; then \
#        echo ${BOOK_JAR_FILE} \
#        cp ${BOOK_JAR_FILE} app.jar; \
#    else \
#        echo ${BOOK_JAR_FILE_URL} \
#        cp ${BOOK_JAR_FILE_URL} app.jar; \
#    fi
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.edg=file:/dev/./urandom","-jar","/app.jar"]

# Command to run docker: C:\WINDOWS\system32> docker run hello-world
# Command to run: C:\Users\Raphael\IdeaProjects\microservices> docker build -t microservice:0.0.1-SNAPSHOT .
# Command to check: PS C:\Users\Raphael\IdeaProjects\microservices> docker images
# REPOSITORY                                TAG                                                                           IMAGE ID       CREATED              SIZE
# microservice                              0.0.1-SNAPSHOT                                                                fc2415048a40   About a minute ago   1.03GB
# https://hub.docker.com/
# Command to tag for dockerhub (also to create lasted version): C:\Users\Raphael\IdeaProjects\microservices> docker tag microservice:0.0.1-SNAPSHOT raphaelgava1/microservice:0.0.1-SNAPSHOT
# Command to check: C:\Users\Raphael\IdeaProjects\microservices> docker images
# REPOSITORY                                TAG                                                                           IMAGE ID       CREATED          SIZE
# microservice                              0.0.1-SNAPSHOT                                                                fc2415048a40   12 minutes ago   1.03GB
# raphaelgava1/microservice                 0.0.1-SNAPSHOT                                                                fc2415048a40   12 minutes ago   1.03GB
# Command to login: C:\Users\Raphael\IdeaProjects\microservices> docker login docker.io
# Authenticating with existing credentials... [Username: raphaelgava1]
# Command to send image to docker: PS C:\Users\Raphael\IdeaProjects\microservices> docker push raphaelgava1/microservice:0.0.1-SNAPSHOT
# The push refers to repository [docker.io/raphaelgava1/microservice]
# Command to run docker: C:\Users\Raphael\IdeaProjects\microservices> docker run -p 80:80 raphaelgava1/microservice:0.0.1-SNAPSHOT
# -p host:docker (ports)
# Command to validate dockers running: C:\Users\Raphael\IdeaProjects\microservices> docker container ls
# Command to check log in real time: docker logs -f 'id'
# Command to close: docker container stop 'id'
# Command to search docker: C:\Users\Raphael\IdeaProjects\microservices> docker search mysql
# NAME                   DESCRIPTION                                     STARS     OFFICIAL
# mysql                  MySQL is a widely used, open-source relation…   15790     [OK]
# bitnami/mysql          Bitnami container image for MySQL               134
# circleci/mysql         MySQL is a widely used, open-source relation…   31
# Command do inspect the images: docker image inspact 'id'
# Command to read docker-compose.yml and run: docker compose up -d
# Command to close compose: docker compose down
# Command to debug: docker events
# Command to check the process : docker top 'id'
# Command of statistics: docker stats
# Command to run process with memory and cpu limited: docker run -p 80:80 -d -m 512m --cpu-quota 80000 raphaelgava1/microservice:0.0.1-SNAPSHOT
# Command : docker system df
# Command to list network: C:\Users\Raphael\IdeaProjects\microservices> docker network ls
#NETWORK ID     NAME                       DRIVER    SCOPE
#6bab9e832d81   bridge                     bridge    local
#22edb9bc6516   host                       host      local
#e45befd17820   microservices_default      bridge    local
#4bab983453e4   microservices_my_network   bridge    local
#73a602f76b8e   none                       null      local
# Command to inspect: C:\Users\Raphael\IdeaProjects\microservices> docker network inspect microservices_my_network
#[
#    {
#        "Name": "microservices_my_network",
#        "Id": "4bab983453e49b5afb9b24784a7635ad376273e1320a80ff59a89664dafc1852",
#        "Created": "2025-05-20T17:05:58.13690862Z",
#        "Scope": "local",
#        "Driver": "bridge",
#        "EnableIPv4": true,
#        "EnableIPv6": false,
#Zipkin
#Zipkin is a distributed tracing system. It helps gather timing data needed to troubleshoot latency problems in service architectures
# Command to install: C:\Users\Raphael\IdeaProjects\microservices> docker run -p 9411:9411 openzipkin/zipkin:latest
# Command to acess: http://localhost:9411/zipkin/