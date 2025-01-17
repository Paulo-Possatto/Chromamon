# Transformer Service
The Transformer Service is a microservice in the **Chromamon** project responsible for managing transformer data and their locations (substations). It allows you to create, edit, delete and consult transformers, as well as associating them with substations.

#

### 🛠️ Features
- **Manage transformers**:
    - Create, edit and delete transformer information.
    - Consult detailed transformer data.
- **Manage locations**:
    - Store information about the substations where transformers are located.
    - Associate transformers with specific substations.

#

### 📚 Technologies Used
- **Java 17**
- **Spring Boot 3.4.1**
- **PostgreSQL (data storage)**
- **Hibernate (object-relational mapping)**
- **Spring Data JPA (persistence)**
- **Docker (containerization)**
- **JUnit (unit testing)**

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
    cd transformer-service  
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
    java -jar target/transformer-service-<version>.jar  
    ```

4. Run the service with Docker:
    ```bash
    docker build -t transformer-service:latest .  
    docker run -p 8081:8081 --name transformer-service transformer-service:latest  
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
Make sure the service is running by going to: http://localhost:8081

#

### 📂 Project structure
```perl
transformer-service/  
├── src/main/java  
│ ├── com.chromamon.transformer  
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

### 📝 Main endpoints
#### Transformers
| Method | Endpoint | Description |
| :----: | :------: | :---------: |
|GET | `/api/transformers` | Lists all transformers.
|POST | `/api/transformers` | Creates a new transformer.
|GET | `/api/transformers/{id}` | Returns information about a specific transformer.
|PUT | `/api/transformers/{id}` | Updates an existing transformer.
|DELETE | `/api/transformers/{id}` | Removes a transformer.

#### Locations (Substations)
| Method | Endpoint | Description |
| :----: | :------: | :---------: |
|GET | /api/locations | Lists all substations.
|POST | /api/locations | Create a new substation.
|GET | /api/locations/{id} | Returns information about a specific substation.
|PUT | /api/locations/{id} | Updates an existing substation.
|DELETE | /api/locations/{id} | Removes a substation.

For more details about the endpoints, check the Swagger file.

# 

### 🛡️ Security
- The connection settings use environment variables to ensure security.
- We recommend using secure connections (SSL/TLS) for the database.

#

### 🤝 Contributions
Contributions are welcome! See the [contributing](../CONTRIBUTING.md) file for more details.

#

### 📝 License
This project is licensed under the [Apache License](../LICENSE)