# Chromamon

Welcome to the central repository of the Chromamon project. This monorepo is made up of multiple independent services, which work together to form the complete tool.

## Contents:
1. [Overview](#overview)
2. [Repository structure](#repository-structure)
3. [Requirements](#requirements)
4. [Configuration and initialization](#configuration-and-initialization)
5. [Available services](#available-services)
6. [Contributing](#contributing)
7. [License](#license)

## Overview

Chromamon is a micro-service whose main objective is to carry out chromatographic analysis on the insulating oil of substation transformers.

Its user-friendly UI means that any user can use the tool, regardless of their technical background, just by following the necessary steps that are highlighted right on the landing page.

This repository uses a monorepo approach to organize multiple services that make up the application. Each service is isolated in its own directory and can be run individually or as part of the complete system using Docker.

## Repository structure

```perl 
Chromamon/
├── analysis-service/        # Service: Analysis
│   ├── Dockerfile           # Dockerfile
│   ├── src/                 # Source code: Java 17 / Spring Boot 3.4.1
│   └── ...
├── api-gateway              # Service: API Gateway (RestAPI)
│   ├── Dockerfile           # Dockerfile
│   ├── src/                 # Source code: Java 17 / Spring Boot 3.4.1
│   └── ...
├── diagnostic-service       # Service: Diagnostic
│   ├── Dockerfile           # Dockerfile
│   ├── src/                 # Source code: Java 17 / Spring Boot 3.4.1
│   └── ...
├── frontend                 # Service: Front-end
│   ├── Dockerfile           # Dockerfile
│   ├── src/                 # Source code: Angular 19
│   └── ...
├── result-service           # Service: Results
│   ├── Dockerfile           # Dockerfile
│   ├── src/                 # Source code: Java 17 / Spring Boot 3.4.1
│   └── ...
├── transformer-service      # Service: Transformers
│   ├── Dockerfile           # Dockerfile
│   ├── src/                 # Source code: Java 17 / Spring Boot 3.4.1
│   └── ...
├── shared/                  # Shared code and configurations
│   └── ...
├── docker-compose.yml       # Service orchestration with Docker Compose
└── README.md                # This file

```

## Requirements

Before beginning, make sure the following softwares are installed in your machine:
* [Docker and Docker Compose](https://www.docker.com/)
* [Git](https://git-scm.com/)
* [DBeaver](https://dbeaver.io/) (Optional)

## Configuration and initialization

**1. Clone the Repository**
```bash 
git clone https://github.com/Paulo-Possatto/Chromamon.git
```

**2. Open the project folder**
```bash 
cd Chromamon/
```

**3. Start the services with Docker Compose**
```bash 
docker-compose up --build
```

**4. Access the services**
* Web page: http://localhost:4200
* Database: `localhost:5433` (User: `postgres`, Password: `postgres`)
* RabbitMQ: http://localhost:15672 (User: `guest`, Password: `guest`)

## Available services

| Service | Description |
| :-----: | :---------: |
| Analysis | Service responsible for storing, observing and adding chromatographic data from transformers |
| API Gateway | A mediator between frontend and backend services, acting as a single entry point for various APIs |
| Diagnostic | Service responsible for containing and processing the diagnostic history for each transformer |
| Frontend | The client layer, where the user will make the calls to the APIs and use the tool |
| Result | Service responsible for storing the most relevant diagnostic data, such as current condition, next analysis, degree of importance and observations
| Transformer | Service responsible for processing all transformer-related data, such as power, location, type, load, etc |

## Contributing

Contributions are welcome! Follow these steps to contribute:
1. Fork the repository.
2. Create a new branch for your feature or correction:
    ```bash
    git checkout -b feature/{feature-name}
    ```
3. Do your changes and create clear commits.
4. Send a pull request explaining your changes.

Or you can check the [Contribution Guide](./CONTRIBUTING.md) to get more details.

## License

This project is licensed under the [Apache License](./LICENSE)

