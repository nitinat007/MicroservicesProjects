# Instructions

## Running Project

* Command ``mvn spring-boot:run ``

## Access Swagger UI

* URL http://localhost:9090/swagger-ui.html

## Create Local RMQ (for testing)

* To spawn local RMQ ``docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.10-management``
* Access: http://localhost:15672/  Cred: guest/guest

## Docker image of the project

* created docker image by: ```./mvnw compile jib:dockerBuild```
* Run by: ``docker run -d -p8081:9090 dockeridfortesting1/mdss-rmqaas-client:v1``
* Swagger UI: http://<localhost/IP>:8081/swagger-ui.html 

