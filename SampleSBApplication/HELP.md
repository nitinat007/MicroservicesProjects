# Getting Started

This is a sample project to tryout various features of Spring boot

-------------
To create the WAR packaging which can be deployed in apache tomcat:

* Add `<packaging>war</packaging>` at same level as groupId
* Run `mvn package`

To create jar file of application

* Run `mvn clean install`

JAR/WAR file can be found at `root>target` folder. Jar can be run directly. War can be deployed to web server

Run application using mvn `"mvn spring-boot:run -Dspring-boot.run.arguments=--person.name=Test"`

-----------
To access WebPage of application access : http://localhost:9090/index after starting from created Jar file.

-----------
Swagger documentation : http://localhost:9090/swagger-ui.html

--------------
To create docker image 
* artifactId in pom.xml must be all small
* run from project home dir: `mvn package docker:build` or `mvn spring-boot:build-image`
* To Push: associate tag, login to account and then push

Docker image of this application pushed to docker hub on 22 nov 2021
Command to pull image: docker pull dockeridfortesting1/spring-boot-app-images:samplesbapplication
To Run: docker run -p 9090:9090/tcp dockeridfortesting1/spring-boot-app-images:samplesbapplication 
-------------

access actuator: http://localhost:9090/manage

---------------

---------------


-----

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

*/