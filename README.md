# Chromamon

Chromamon is a monolithic-based system designed to analyze and process chromatographic data from transformer insulating oil in electrical substations. The system provides comprehensive analysis, diagnostics, results and reporting capabilities.

## Project Structure

```

```

## Architecture Overview

Each service follows the Hexagonal Architecture (Ports and Adapters) pattern:

### Core Layers

1. **Domain Layer** (`domain/`)
   - Contains business logic and entities
   - Independent of external concerns
   - Pure Java/business rules

2. **Application Layer** (`application/`)
   - Contains use cases and port definitions
   - Orchestrates the flow of data
   - Defines interfaces (ports) for external dependencies

3. **Infrastructure Layer** (`infrastructure/`)
   - Contains technical details and implementations
   - Adapters for external services/databases
   - Framework-specific code

### Ports and Adapters

- **Input Ports**: Interfaces defining the operations the application offers
- **Output Ports**: Interfaces defining the operations the application needs
- **Input Adapters**: Implementations of input ports (e.g., REST controllers, Kafka consumers)
- **Output Adapters**: Implementations of output ports (e.g., database repositories, external services)

### Benefits of Hexagonal Architecture

- Clear separation of concerns
- Domain logic isolation
- Easy to test (domain logic can be tested without infrastructure)
- Flexible and maintainable
- Easy to change implementations (e.g., switch databases)
- Protection against external changes

## System Architecture

The project is structured into multiple microservices, each with its specific responsibility and database:

### Microservices

#### Analysis Service
- Reads and processes chromatographic data from XLSX files
- Performs data enrichment and enumeration
- Stores processed data in PostgreSQL
- Technologies: Java 21, Spring Boot 3, PostgreSQL

#### Diagnostic Service
- Processes analysis data using various chromatographic analysis methods. For now, it's the following:
  - IEEE Method
  - Rogers Method
  - Duval Triangle Method
- Stores diagnostic results in MongoDB
- Technologies: Java 21, Spring Boot 3, MongoDB

#### Transformer Service
- Manages transformer-related information:
  - Lifecycle data
  - Location information
  - Condition monitoring
- Stores transformer data in MySQL
- Technologies: Java 21, Spring Boot 3, MySQL

#### Exporter Service
- Generates PDF reports based on transformer and analysis data
- Uses JasperSoft Studio for report template design
- Stores document metadata in MongoDB
- Technologies: Java 21, Spring Boot 3, MongoDB, JasperSoft Studio

#### API Gateway
- Manages API calls to all Chromamon services
- Provides centralized routing and load balancing
- Technologies: Java 21, Spring Boot 3

### Technology Stack

#### Backend
- Java 21
- Spring Boot 3.5.0
- Maven
- Multiple databases:
  - PostgreSQL
  - MongoDB
- JasperSoft Studio (for report generation)
- Docker and Docker Compose for containerization

#### Frontend (Future Development)
- Angular framework
- Modern responsive design
- Interactive data visualization

## Getting Started

### Prerequisites
- Docker
- Docker Compose

### Installation and Setup
1. Clone the repository
2. Navigate to the project root directory
3. Run the following command to start all services:
```bash
docker-compose up -d
```

This will:
- Build all service containers
- Set up the required databases
- Configure the network between services
- Start the API Gateway

Detailed configuration and environment variables will be documented in each service's directory.

## Documentation

Documentation for API endpoints (REST/GraphQL) will be added once the API design is finalized.

## Contributing

Star this project on GitHub to show your support! ⭐

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For more information, please visit my [GitHub Profile](https://github.com/Paulo-Possatto)
