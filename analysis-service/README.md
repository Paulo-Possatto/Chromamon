# Chromamon Analysis Service

This directory contains the Analysis service of the **ChromaMon** project, which is responsible for storing, observing and adding chromatographic data from transformers.

## Overview

The **Analysis Service** is part of the Chromamon project ecosystem and is responsible for:

- Storing the history of chromatographic analyses associated with transformers.
- Making observations on the growth of gas concentrations over time.
- Adding new chromatographic analysis data to the system.

## Technologies Used

- **Java 17**: Base language of the project.
- **Spring Boot 3.4.1**: Main development framework.
- **PostgreSQL**: Relational database for the analysis.
- **RabbitMQ**: Used for asynchronous communication between services.
- **Docker**: Container for the service.

## Project structure
```perl
analysis-service/ 
├── src/ 
│    ├── main/ 
│    │    ├── java/com/chromamon/analysis/ 
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
- **PostgreSQL** Database (can be a docker image)

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/Paulo-Possatto/Chromamon.git
    cd Chromamon/analysis-service
    ```
2. Configure the environment variables in the `application.yml` file.

    - Configuration for the PostgreSQL database.

    - Configuration for the RabbitMQ.  

3. Install the dependencies of the project with Maven and test it:
    ```bash
    ./mvnw clean install
    ./mvnw test
    ```

### Running the Service
#### Locally
Start the service with Maven:

```bash
./mvnw spring-boot:run
```
#### Using Docker
1. Build the Docker image:
    ```bash
    docker build -t chromamon/analysis-service:latest .
    ```
2. Run the container:
    ```bash
    docker run -p 8081:8080 --name analysis-service chromamon/analysis-service:latest
    ```

#### Integration with other services
- The Analysis Service communicates with the Diagnostic Service via RabbitMQ to consume new chromatographic analysis events.
- Make sure that RabbitMQ is configured correctly.

### Main Endpoints
| Method | Endpoint | Description |
| :----: | :------: | :---------: |
| POST | `/analysis` | Add a new chromatographic analysis. |
| GET | `/analysis/history` | Get the analysis history. |
| GET | `/analysis/observations` | Observe concentration growth. |

### Tests
Run automated tests:

```bash
./mvnw test
```

### Contributing
1. Create a new branch:
    ```bash
    git checkout feature
    git checkout -b my-feature
    ```
2. Make your changes and commit:
    ```bash
    git commit -m “Description of change”
    ```

3. Push to your branch:
    ```bash
    git push origin my-feature
    ```

4. Open a [Pull Request](https://github.com/Paulo-Possatto/Chromamon/pulls) in the repository.

For any question, feel free to see the [Contributing](../CONTRIBUTING.md) documentation

## License

This project is licensed under the [Apache License](../LICENSE)