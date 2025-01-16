# Chromamon API Gateway

This directory contains the API Gateway service of the **ChromaMon** project, which is responsible for managing traffic and routing requests between the system's microservices.

## Overview

The **API Gateway** is the gateway for users of the Chromamon system. It acts as an intermediary between clients and internal microservices, offering functionalities such as:
- Routing requests to the correct microservices.
- Load balancing.
- Access control and security.
- Monitoring and logging of requests.
- Implementation of fault tolerance with *circuit breakers*.

## Technologies Used

- **Java 17**: Base language of the project.
- **Spring Boot 3.4.1**: Main development framework.
- **Spring Cloud Gateway**: For implementing the API Gateway.
- **RabbitMQ**: Used for asynchronous communication between services.
- **Resilience4j**: For fault tolerance.
- **Docker**: Container for the service.

## Project structure
```perl
api-gateway/ 
├── src/ 
│    ├── main/ 
│    │    ├── java/com/chromamon/gateway/ 
│    │    └── resources/ 
│    │           └── application.yml 
│    └── test/
├── Dockerfile 
├── pom.xml 
└── README.md
```

## Configuration and Execution

### Requirements

Make sure you have the following tools installed:
- **Java 17**
- **Maven** (for dependency management)
- **Docker** and **Docker Compose** (optional, for running in a container)
- **RabbitMQ** configured in the environment
- **Postman** (For API testing)

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/Paulo-Possatto/Chromamon.git
    cd Chromamon/api-gateway
    ```
2. Install the dependencies of the project with Maven:
    ```bash
    mvn clean install
    ```

### Configuration
Edit the [application.yml](/api-gateway/src/main/resources/application.yml) file to customize routes and service settings. Example of basic configuration:
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: diagnostic-service
          uri: http://diagnostic-service:8080
          predicates:
            - Path=/api/diagnostics/**
        - id: analysis-service
          uri: http://analysis-service:8080
          predicates:
            - Path=/api/analysis/**
  application:
    name: api-gateway
server:
  port: 8080
```

### Executing the service:
* **Locally**:
    ```bash
    mvn spring-boot:run
    ```

* **With Docker**:
    1. **Create the Docker image**:
        ```bash
        docker build -t chromamon-api-gateway .
        ```
    2. **Execute the container**:
        ```bash
        docker run -p 8080:8080 chromamon-api-gateway
        ```

## Contribution
Feel free to contribute improvements to the API Gateway service! Just open a pull request or report a problem in the [Issues](https://github.com/Paulo-Possatto/Chromamon/issues) section.

## License
This project is licensed under the [Apache License](../LICENSE)
