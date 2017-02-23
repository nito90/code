## Overview

The project implements the logic to load a file with information of vehicles and execute some tasks. It is a maven/Spring Boot project and uses Swagger for visual interaction for REST APIs. 

## Prerequisites
- JDK 1.8 (Oracle)
- Maven 3

## Installation 

```
mvn clean install
```
## Deployment
```
mvn spring-boot:run
```

## Url to interact with Swagger
```
http://localhost:8080/swagger-ui.html
```

## The format of the file should be

```json
{
    "Search": {
        "VehicleList": [
            {
                "sipp": "CWMR",
                "name": "Kia Ceed Estate",
                "price": 311.03,
                "supplier": "Alamo",
                "rating": 7.8
            }
        ]
    }
}
```
