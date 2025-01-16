# Contribution Guide
Thank you for your interest in contributing to the Chromamon project! This document describes the guidelines and steps for contributing in an effective and organized manner.

## Contents
1. [How can I help?](#how-can-i-help)
2. [Setting up the local environment](#setting-up-the-local-environment)
3. [Reporting problems](#reporting-problems)
4. [Creating new features](#creating-new-features)
5. [Code style](#code-style)
6. [Testing your changes](#testing-your-changes)
7. [Sending a Pull Request](#sending-a-pull-request)
8. [Code of Conduct](#code-of-conduct)

 
## How can I help?
You can contribute to the project in the following ways:

Reporting bugs or problems.
Suggesting new features.
Improving the documentation.
Submitting code fixes.
Helping to review Pull Requests from other contributors.

## Setting up the local environment
Follow these steps to set up the project locally:

Clone the repository:

```bash
git clone https://github.com/Paulo-Possatto/Chromamon.git
cd Chromamon
```

### 1. Manual configuration:

### Required tools:
- Docker (To run the PostgreSQL and RabbitMQ images)
- Postman or Insomnia (For API testing)

### Initiate frontend locally:

To start the frontend (Angular 19) locally, you must follow these steps:
1. Make sure you have node installed:
    ```bash
    node -v
    ```

2. Open the frontend directory:
    ```bash
    cd frontend
    ```

3. Install the dependencies:
    ```bash
    npm install
    ```

4. Start the Angular development server:
    ```bash
    ng serve
    ```

Angular will be available in the browser at http://localhost:4200.

### Initiate backend service(s) locally:

1. Navigate to each directory of the Java services:
    - API Gateway:
        ```bash
        cd api-gateway
        ```
    - Analysis Service:
        ```bash
        cd analysis-service
        ```
    - Diagnostic Service:
        ```bash
        cd diagnostic-service
        ```
    - Result Service:
        ```bash
        cd result-service
        ```
    - Transformer Service:
        ```bash
        cd transformer-service
        ```
2. Compile and execute the services:
    ```bash
    mvn spring-boot:run
    ```
3. For each backend service, you need to look the port that the service is using, you can find it in the application.yml file of the service, then, look for the following property:
    ```yaml
    port: {portNumber}
    ```
    So then you can test in the following format:
    `http://localhost:{portNumber}`

### Integration:
Make sure Angular is configured to make calls to the correct backend. Normally, you'll set the API endpoints in the `environment.ts` file:
```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:{portNumber}'
};
```

### 2. Using Docker Compose:
With Docker Compose, you can start both the frontend and backend services together with a single command.

You can check the [Docker Compose](./docker-compose.yml) file and make sure the ports are correct for each service, like this example:
```yaml
version: "3.8"
services:
    frontend:
        build:
            context: ./frontend
            dockerfile: Dockerfile
        ports:
            - "4200:80"
        networks:
            - app-network
    analysis-service:
        build:
            context: ./analysis-service
            dockerfile: Dockerfile
        ports:
            - "8080:8080"
        environment:
            SPRING_PROFILES_ACTIVE: "dev"
        networks:
            - app-network
    # Other services (Transformer, PostgreSQL, RabbitMQ...)
```

### Steps:
1. Make sure each service has a Dockerfile (Java and Angular).
2. Start all services:

    ```bash
    docker-compose up --build
    ```
3. The frontend will be available at http://localhost:4200 and the backend services in the configured ports.

## Reporting problems
1. Check if the problem has already been reported on the [Issues page](https://github.com/Paulo-Possatto/Chromamon/issues).
2. If not, create a new Issue and provide:
    - A clear description of the problem.
    - Steps to reproduce the error.
    - The expected behavior and the current behavior.
    - Relevant logs or error messages (if applicable).

## Creating new features
1. **Open an Issue**: Before you start, describe your idea to validate that it is in line with the project's objectives.
2. After approval, follow the steps below:
    - Create a specific branch:

        ```bash
        git checkout -b feature/your-feature-name
        ```
    - Add your feature and update the documentation if necessary.
    - Make sure you include tests for your feature.

## Code style
Follow the code standards defined for the project:

- **Linting**: Run the linter before submitting your contribution:

    ```bash
    npm run lint
    ```
- **Formatting**: Make sure the code follows the ESLint style.
- **Commit structure**: Use commit messages in the format:

    ```txt
    type: short description (maximum 50 characters)

    Detailed body of the commit message (optional).
    ```
    Examples of types:
    - `feat`: Adds a new feature.
    - `fix`: Fixes a bug.
    - `docs`: Updates documentation.

## Testing your changes
1. Make sure all tests pass:

    ```bash
    npm run test
    ```
2. Add new tests to cover your changes.
3. Manually test the functionality, if applicable.

## Sending a Pull Request
1. Synchronize your branch with the main branch:

    ```bash
    git checkout main
    git pull origin main
    git checkout feature/new-functionality
    git rebase main
    ```

2. Send your branch to the remote repository:

    ```bash
    git push origin feature/new-feature
    ```

3. Create the Pull Request:

    - Go to [Pull Requests](https://github.com/Paulo-Possatto/Chromamon/pulls).
    - Click on "New Pull Requests"
    - Include a clear description of the changes and how to test them.

4. Wait for a review from a project maintainer.

## Code of Conduct
This project follows the [Code of Conduct for Collaborators](./CODE_OF_CONDUCT.md). By participating, you agree to respect these principles.
