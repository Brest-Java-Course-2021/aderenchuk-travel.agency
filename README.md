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
for start soap-app...
```
cd soap-app/target
java -jar soap-app.jar


```

This starts application and serves up your project on [http://localhost:8080](http://localhost:8080) for web-app, [http://localhost:8088](http://localhost:8088) for rest-app and
[http://localhost:8090](http://localhost:8090) for soap-app.

## SOAP-APP 
Schema at : [http://localhost/ws/tours.xsd](http://localhost/ws/tours.xsd)

## Swagger
Swagger json documentation can be accessed at : [http://localhost:8088/v2/api-docs](http://localhost:8080/v2/api-docs) \
Swagger UI can be accessed at : [http://localhost:8088/swagger-ui/](http://localhost:8080/swagger-ui/)

## Fake generator
Fake data for clients can be accessed at : http://localhost:8088/fakerClients

## Import/Export Excel
Import clients data can be accessed at : http://localhost:8088/import/excel
Export clients data can be accessed at : http://localhost:8088/export/excel

## Run application with docker-compose and postgresql database
From the same directory as your root pom.xml, type:

```
docker-compose -f docker-compose.yml up
```

The PostgreSql database can be accessed at: http://localhost:5432

The rest-app can be accessed at: http://localhost:8088

The web-app can be accessed at: http://localhost:8080

To start/stop app use:
```
docker-compose -f docker-compose.yml start
docker-compose -f docker-compose.yml stop or (Ctrl + C)
```
To stop it and remove the container, run:
```
docker-compose -f docker-compose.yml down
```
