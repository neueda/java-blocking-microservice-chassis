# Java Blocking Microservice Chassis
## Table of contents
- [Getting started](#getting-started)
  - [Requirements](#requirements)
  - [Usage](#usage)
- [Dependencies](#dependencies)
  - [Web Framework](#web-framework)
  - [Development Tools](#development-tools)
  - [Persistence](#persistence)
  - [Data Migration](#data-migration)
  - [Monitoring](#monitoring)
  - [API Documentation](#api-documentation)
  - [Testing](#testing)
  - [Containerization](#containerization)

- [Contributing](#contributing)
- [License](#license)

## Getting Started

### Requirements
#### Software
- [JDK 16](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html)
- [intellij IDE](https://spring.io/guides/gs/intellij-idea/)
- [Maven](https://maven.apache.org/download.cgi)
- [Spring Initializer to generate project and its dependencies](https://start.spring.io/)
- [Docker Engine](https://docs.docker.com/engine/install/)

### Usage
Run the restful application server:
```shell
$ docker compose up --build -d
```

Stop the restful application server:
```shell
$ docker compose stop chassis
```

Stop and remove containers and networks:
```shell
$ docker compose down
```
### Dependencies
#### Web Framework
- [Spring Web](https://start.spring.io/)
#### Development Tools
- [Lombok](https://projectlombok.org/features/all)
- [Spring Boot Dev Tools](https://docs.spring.io/spring-boot/docs/2.5.3/reference/html/using.html#using.devtools)
- [H2 database](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.sql.h2-web-console)
#### Persistence
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
#### Data Migration
- [Liquibase](https://docs.liquibase.com/home.html)
#### Monitoring
- [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.5.3/reference/html/actuator.html)
- [Spring Cloud Sleuth](https://spring.io/projects/spring-cloud-sleuth)
#### API Documentation
- [SpringDoc OpenAPI](https://springdoc.org)
#### Testing
- [Spring Boot Test](https://docs.spring.io/spring-framework/docs/5.3.9/reference/html/testing.html)
- [TestContainers](https://www.testcontainers.org/)
#### Containerization
- [Layered Jar](https://docs.spring.io/spring-boot/docs/2.5.3/maven-plugin/reference/htmlsingle/#packaging.layers)
## Contributing
- [GitHub Issues](https://github.com/neueda/java-blocking-microservice-chassis/issues)
## License
- [License](https://github.com/neueda/java-blocking-microservice-chassis/blob/master/LICENSE)
