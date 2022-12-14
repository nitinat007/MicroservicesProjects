# Instructions

## Running Project

* Command ``mvn spring-boot:run ``

## Access Swagger UI

* URL http://localhost:9090/swagger-ui.html

## Create Local RMQ (for testing)

* To spawn local RMQ ``docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.10-management``
* Access: http://localhost:15672/  Cred: guest/guest