[![Java CI with Maven](https://github.com/Brest-Java-Course-2021/aderenchuk-travel.agency/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/Brest-Java-Course-2021/aderenchuk-travel.agency/actions/workflows/maven.yml)
# aderenchuk-travel.agency

This is simple 'Travel agency' web application.

## Requirements

* JDK 11
* Apache Maven

## Build application:
```
mvn clean install
```

## Local tests

From the same directory as your root pom.xml, type:
for start web-app...
```
cd web-app/target
java -jar web-app.jar


```
for start rest-app...
```
cd rest-app/target
java -jar rest-app.jar


```

This starts application and serves up your project on [http://localhost:8080](http://localhost:8080) for web-app and [http://localhost:8088](http://localhost:8088) for rest-app.


## Swagger
Swagger json documentation can be accessed at : [http://localhost:8080/v2/api-docs](http://localhost:8080/v2/api-docs) \
Swagger UI can be accessed at : [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)
