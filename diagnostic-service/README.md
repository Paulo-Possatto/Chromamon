# Diagnostic Service
The **Diagnostic Service** is a microservice of the ChromaMon project responsible for processing chromatographic analysis data and applying diagnostic methods, such as **IEEE**, **Duval**, **Rogers**, and **KGA**, to identify possible faults or trends in transformers. Diagnostic results are stored in a **PostgreSQL** database for consultation and history.

#

### 🛠️ Features
- Receive chromatographic analysis data via messaging (RabbitMQ).
- Process the data using the methods:
    - **IEEE** (International Electrotechnical Commission)
    - **Duval Triangle**
    - **Rogers Ratio**
    - **Key Gas Analysis (KGA)**
- Save the results in the PostgreSQL database, organized by transformer and analysis date.
- Allow historical queries of diagnostic results by transformer.

#

### 📚 Technologies Used
- Java 17
- Spring Boot 3.4.1
- RabbitMQ (messaging)
- PostgreSQL (results storage)
- Spring Data JPA (persistence)
- Spring Batch (data processing)
- Docker (containerization)
- JUnit (unit testing)

#

### 🚀 How to run the service
#### Prerequisites
- **Java 17**
- **Docker** and **Docker Compose**
- **RabbitMQ** and **PostgreSQL** configured and running (via Docker Compose or locally)

#### Steps
1. Clone the repository:
    ```bash
    git clone https://github.com/Paulo-Possatto/Chromamon.git
    cd diagnostic-service
    ```

2. Configure the `application.yml` file with the connection details for RabbitMQ and PostgreSQL:
    ```yaml
    spring:
        datasource:
            url: jdbc:postgresql://<host>:<port>/chromamon
            username: <db_user>
            password: <db_password>
        rabbitmq:
            host: <rabbitmq_host>
            username: <rabbitmq_user>
            password: <rabbitmq_password>
    ```
3. Compile and run the service locally:
    ```bash
    ./mvnw clean install
    java -jar target/diagnostic-service-<version>.jar
    ```
4. Run the service with Docker:
    ```bash
    docker build -t diagnostic-service:latest .
    docker run -p 8080:8080 --name diagnostic-service diagnostic-service:latest
    ```

#

### 🧪 Tests
Run the unit and integration tests using:
```bash
./mvnw test
```

# 

### 📦 Docker Compose
To run the service along with its dependencies (RabbitMQ and PostgreSQL):

1. Bring up the services with Docker Compose:
    ```bash
    docker-compose up -d
    ```
2. Make sure the service is running by going to: http://localhost:8080

#

### 📂 Project structure
```perl
diagnostic-service/
├── src/main/java
│ ├── com.chromamon.diagnostic
│ │ ├── config/ # Spring configurations (RabbitMQ, PostgreSQL)
│ │ ├── controller/ # REST endpoints (if applicable)
│ │ ├── service/ # Business logic for diagnostics
│ │ ├── repository/ # Persistence interfaces with the database
│ │ ├── model/ # JPA entities
│ │ └── batch/ # Batch processing settings (Spring Batch)
├── src/test/java # Unit and integration tests
├── Dockerfile # Docker configuration for the service
├── docker-compose.yml # Docker Compose configuration for the complete environment
└── pom.xml # Maven configuration
```

#

### 📖 Diagnostic methods
1. **IEEE**
    - Applies defined limits to identify faults based on gas concentrations.
2. **Duval Triangle**
    - Analyzes relationships between hydrocarbon concentrations to categorize faults.
3. **Rogers Ratio**
    - Calculates ratios between gases for detailed diagnosis.
4. **Key Gas Analysis (KGA)**
    - Focuses on the main gases associated with critical failures.

#

### 🛡️ Security
This service uses best practices to secure connections to RabbitMQ and PostgreSQL, such as:

- Environment variables for credentials.
- Secure connections to external services.

#

### 🤝 Contributions
Contributions are welcome! Follow the steps in the [contributing](../CONTRIBUTING.md) file for more details.

# 

### 📝 License
This project is licensed under the [Apache License](../LICENSE)