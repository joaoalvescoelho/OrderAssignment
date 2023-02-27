# Order Store Assignment

## Description
Objective

Create an API using SpringBoot which can create and retrieve an order.

The application uses SpringBoot framework and MySQL as database.

The application has an openapi yaml (swagger) file and a postman collection, as requested. Both files can be accessed under ```OrderAssignment/src/main/resources/static ```

## Requirements
* Docker
* docker-compose
* SpringBoot
* openapi.yaml


## How to run
After cloning the repo locally

1. cd to docker directory (src/main/docker)
   1. in the docker-compose.yml inside the docker folder is possible to change parameters, such as ports number, in case the ones used in this application are already in use. Change, for example, the ports to "5430:5432";
2. run the command ```docker-compose up``` within the docker folder

Afterwards, use postman test the requests.

It is also possible to access http://localhost:8080/swagger-ui/index.html#/ to test the requests

