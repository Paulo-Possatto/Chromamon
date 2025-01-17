# Result Service
The Result Service is a microservice of the **Chromamon** project, responsible for storing and managing the most relevant transformer diagnostic data. It centralizes information such as the current condition, date of next analysis, degree of importance and other crucial observations, allowing quick and efficient queries for operational decisions.

#

### 🛠️ Functionalities
- Results Management:
    - Store the most relevant information from chromatographic diagnostics.
    - Update and consult data such as:
        - Current condition of the transformer.
        - Recommended date for the next analysis.
        - Degree of importance based on the diagnoses.
        - Additional notes.
- Centralized consultation:
    - Provide APIs to quickly access the results of the diagnostics.

# 

### 📚 Technologies Used
- **Java 17**
- **Spring Boot 3.4.1**
- **PostgreSQL** (data storage)
- **Hibernate** (object-relational mapping)
- **Spring Data JPA** (persistence)
- **Docker** (containerization)
- **JUnit** (unit testing)

#

### 🚀 How to run the service
#### Prerequisites
- **Java 17**
- **Docker** and **Docker Compose**
- **PostgreSQL** configured and running (via Docker Compose or locally)

#### Steps
1. Clone the repository:
    ```bash
    git clone https://github.com/Paulo-Possatto/Chromamon.git  
    cd result-service  
    ```
2. Configure the `application.yml` file with the connection details for PostgreSQL:
    ```yaml
    spring:  
    datasource:  
        url: jdbc:postgresql://<host>:<port>/chromamon  
        username: <db_user>  
        password: <db_password>  
    ```
3. Compile and run the service locally:
    ```bash
    ./mvnw clean install  
    java -jar target/result-service-<version>.jar  
    ```
4. Run the service with Docker:
    ```bash
    docker build -t result-service:latest .  
    docker run -p 8082:8082 --name result-service result-service:latest  
    ```

#

### 🧪 Testing
Run the unit and integration tests using:
```bash
./mvnw test  
```

#

### 📦 Docker Compose
To run the service along with its dependencies (PostgreSQL):

1. Upload the services with Docker Compose:
    ```bash
    docker-compose up -d  
    ```
Make sure the service is running by going to: http://localhost:8082

#

### 📂 Project Structure
```perl
result-service/  
├── src/main/java  
│ ├── com.chromamon.result  
│ │ ├── config/ # Spring settings (PostgreSQL)  
│ │ ├── controller/ # REST endpoints  
│ │ ├── service/ # Business logic  
│ │ ├── repository/ # Persistence interfaces with the database  
│ │ └── model/ # JPA entities  
├── src/test/java # Unit and integration tests  
├── Dockerfile # Docker configuration for the service  
├── docker-compose.yml # Docker Compose configuration for the complete environment  
└── pom.xml # Maven configuration  
```

#

### 📝 Endpoints
| Method | Endpoint | Description |
| :----: | :------: | :---------: |
|GET | /api/results | Lists all stored results.
|POST | /api/results | Creates a new result for a diagnosis.
|GET | /api/results/{id} | Returns detailed information for a specific result.
|PUT | /api/results/{id} | Updates an existing result.

#

### 🛡️ Security
- Environment variables are used to configure sensitive information.
- It is recommended to use secure connections (SSL/TLS) to the database.

#

### 🤝 Contributions
Contributions are welcome! See the [contributing](../CONTRIBUTING.md) file for more details.

#

### 📝 License
This project is licensed under the [Apache License](../LICENSE)