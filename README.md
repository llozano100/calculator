# README #

This README would normally document whatever steps are necessary to get your application up and
running.

# Requirements to install :

* Java 17 openjdk-17
* Docker
* Docker compose
* Maven
* Postman

# Install requirements with brew

* brew install openjdk@17
* brew install docker
* brew install docker-compose
* brew install maven

# Building

* mvn clean install

# Building the docker image

* docker-compose build
* List docker images
* docker image ls

# Running application with docker-compose

* docker-compose up
* Use the qa dev yml to connect the postgres  connection
* Swagger http://localhost:50315/swagger-ui/index.html

# Api

* GET  http://localhost:8080/api/health-check
* Swagger http://localhost:8080/swagger-ui/index.html

# Running application locally

* Use the dev yml to connect h2 memory database .All the jdbc properties are located there.
* there is a test controller that is commented because you do not need to wait 1 hour for the cron
  job to populate data into h2 database


### Contribution guidelines ###

* Writing tests
* Coverage : 56 %
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact