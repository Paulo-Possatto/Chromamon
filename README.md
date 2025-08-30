# Chromamon

Complete system for chromatographic analysis in electrical substation transformers, developed with hexagonal architecture and Spring Boot 3.

## Technologies used

### Backend
- **Java 21** - Main language
- **Spring Boot 3.5.4** - Main framework
- **Maven** - Dependencies management
- **PostgreSQL** - Main database
- **MongoDB** -Auditing database
- **Spring Security + JWT** - Authentication and authorization
- **Flyway** - Database migration

### Frontend
- **Apache Freemarker** - Template engine
- **Bootstrap 5** - Framework CSS
- **Chart.js** - Graphs and visualizations
- **JavaScript ES6+** - Frontend functionalities

### Infrastructure
- **Docker Compose** - Container orchestration
- **Prometheus** - Metrics monitoring
- **Grafana** - Monitoring dashboard
- **WireMock** - API mocking for testing
- **SonarQube** - Code quality analysis

## Pre-requirements

- Java 21+
- Maven 3.9+
- Docker e Docker Compose
- Git

## Installation and Configuration

### 1. Clone the repository

```bash
git https://github.com/Paulo-Possatto/Chromamon
cd Chromamon
```

### 2. Configure the environment variables

Create a `.env` file in the root of the project:

```env
# POSTGRESQL
PG_DATABASE_NAME=<Your DB Name>
PG_DATABASE_PASS=<Your DB Password>
PG_DATABASE_USER=<Your DB User>

# MONGO DB
MG_DATABASE_NAME=<Your MongoDB Name>
MG_DATABASE_PASS=<Your MongoDB Password>

# GRAFANA
GF_ADMIN_USER=<Your grafana user>
GF_ADMIN_PASS=<Your grafana password>

# JWT
JWT_SECRET=<Your JWT Secret>
JWT_EXPIRATION=86400000 <This is set to 24 hours, change it if you want to>

# SERVER
SERVER_PORT=8080 <By default, you can change it if you want to>

# DIRECTORIES
TEMP_DIR=/tmp/chromatography
REPORTS_DIR=/tmp/reports
```

### 3. Start the infrastructure services

```bash
# Start all the containers
docker-compose up -d

# Check if the containers are being executed
docker-compose ps
```

### 4. Start the application

```bash
# Compile the project
mvn clean compile

# Execute the migrations
mvn flyway:migrate

# Execute the application
mvn spring-boot:run
```

### 5. Access the application

- **Main application**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Grafana**: http://localhost:3000 (admin/admin)
- **Prometheus**: http://localhost:9090
- **SonarQube**: http://localhost:9000 (admin/admin)

## Default user

```
Email: admin@chromamon.com
Password: Admin123
```

**⚠️ Important**: Change the default password on the first use.

## System Architecture

### Hexagonal Architecture

The system uses hexagonal architecture (ports and adapters) organized into:

```
src/main/java/com/monolithic/chromamon/
├── shared/                   # Shared module
│   ├── domain/               # Shared domain
│   ├── application/          # Shared services
│   └── infrastructure/       # Shared Infrastructure
├── login/                    # Authentication Service
│   ├── domain/               # Entities and Business Rules
│   ├── application/          # Use Cases
│   └── infrastructure/       # External Adapters
├── analysis/                 # Analysis Service
├── diagnostic/               # Diagnostic Service
├── transformer/              # Transformer Service
├── report/                   # Report Service
└── audit/                    # Auditing service
```

### Layers by service

1. **Domain** - Entities, value objects, and business rules
2. **Application** - Use cases and orchestration
3. **Infrastructure** - Adapters for database, web, etc.

## Permissions System

### Available Roles

- **ADMIN** - Complete system access
- **OPERATIONS** - Looks at the analyses, diagnostics, transformers and get reports
- **MAINTENANCE** - Can read and add analyses and can read and update transformer data
- **ENGINEERING** - Reads analyses, diagnostics and transformers, also can read and create reports
- **PLANNING** - Can read analyses, diagnostics and reports
- **CUSTOMER_SERVICE** - Only can read reports
- **BUSINESS** - Only reads diagnostics and reports
- **MANAGEMENT** - Can read every service, except users
- **ADMINISTRATION** - Can read, create and update users
- **TECHNOLOGY** - Can read and update users, also read auditing
- **ANALYST** - Can read analyses, disgnostic and reports, also create reports
- **SAFETY** - Can read auditing and reports
- **USER** - Only reads reports

### Granular permissions

Each user can have specific permissions per operation:

- `analysis:read`, `analysis:create`, `analysis:update`, `analysis:delete`
- `diagnostic:read`, `diagnostic:create`, `diagnostic:update`, `diagnostic:delete`
- `transformer:read`, `transformer:create`, `transformer:update`, `transformer:delete`
- `report:read`, `report:create`, `report:update`, `report:delete`
- `user:read`, `user:create`, `user:update`, `user:delete`
- `audit:read`, `audit:delete`

### Example

```java
@HasPermission(Permission.ANALYSIS_CREATE)
public Analysis createAnalysis(CreateAnalysisRequest request) {
    // Method Impl
}
```

## Main functionalities

### 1. Transformer Management

- Complete transformer registry
- Analysis history by equipment
- Location and technical data
- Operational status

### 2. Chromatographic Analysis

- Dissolved gas recording (H₂, CH₄, C₂H₂, C₂H₄, C₂H₆, CO, CO₂)
- Multiple analysis methods ([ASTM D3612](https://www.scribd.com/document/272526395/ASTM-D-3612-01?language_settings_changed=English), [IEC 60567](https://webstore.iec.ch/en/publication/70013))
- Analysis history
- Import via Excel/CSV

### 3. Automatic Diagnostics

- **Duval Method** (Triangle and Pentagon)
- **IEEE C57.104** - Gas ratios
- **Rogers Method** - Modified ratios
- **IEC 60599** - Gas interpretation
- Severity levels and recommendations

### 4. Customized Reports

- PDF reports using JasperReports
- Trend charts with JFreeChart
- Customizable templates
- Report scheduling

### 5. Complete Audit

- Log of all operations
- Change traceability
- Storage in MongoDB
- Advanced audit queries

## REST APIs

### Authentication

```bash
# Login
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}

# Response body (200 OK)
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "tokenType": "Bearer",
  "expiresIn": 86400,
  "username": "admin",
  "role": "ADMIN",
  "permissions": ["analysis:read", "analysis:create", ...]
}
```

### Analyses

```bash
# Create analysis
POST /api/v1/analyses
Authorization: Bearer {token}
Content-Type: application/json

{
  "transformerId": 1,
  "analysisDate": "2024-03-15",
  "sampleDate": "2024-03-14",
  "laboratory": "Lab Central",
  "hydrogenH2Ppm": 45.5,
  "methaneCh4Ppm": 12.3,
  "acetyleneC2h2Ppm": 0.5,
  ...
}

# List analyses
GET /api/v1/analyses?page=0&size=20
Authorization: Bearer {token}

# Search by transformer
GET /api/v1/analyses/transformer/1
Authorization: Bearer {token}
```

### Diagnostics

```bash
# Create diagnostic
POST /api/v1/diagnostics
Authorization: Bearer {token}
Content-Type: application/json

{
  "analysisId": 1,
  "method": "DUVAL_TRIANGLE",
  "includeRecommendations": true
}
```

## Tests

### Execute all tests

```bash
mvn test
```

### Integration tests with Testcontainers

```bash
mvn integration-test
```

### Code coverage

```bash
mvn jacoco:report
```

### Quality analysis with SonarQube

```bash
mvn sonar:sonar
```

## Monitoring

### Available metrics

- `/actuator/health` - Application status
- `/actuator/metrics` - System metrics
- `/actuator/prometheus` - Prometheus Metrics

### Grafana Dashboards

1. **Application Overview** - General application metrics
2. **Database Performance** - PostgreSQL and MongoDB performance
3. **Business Metrics** - Business metrics (analysis, diagnostics)
4. **System Resources** - CPU, memory, disk

### Configured Alerts

- High CPU usage (>80%)
- High memory usage (>85%)
- Frequent authentication failures
- High response time (>2s)

## Logs

### Logs configuration

Logs are configured at different levels:

```yaml
logging:
  level:
    com.chromatography: DEBUG
    org.springframework.security: DEBUG
    org.springframework.web: INFO
  file:
    name: logs/chromamon.log
```

### Log Types

- **DEBUG** - Detailed information for development
- **INFO** - Important application events
- **WARN** - Situations that deserve attention
- **ERROR** - Errors that need correction

## 🔄 CI/CD

### Build pipeline

```yaml
# .github/workflows/ci.yml
name: CI Pipeline
on:
  push:
    branches: [main, develop]
  pull_request:
    branches: [main]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 21
        uses: actions/setup-java@v3
        with:
          java-version: '21'
          distribution: 'temurin'
      - name: Run tests
        run: mvn clean test
      - name: SonarQube analysis
        run: mvn sonar:sonar
```

### Deploy

```bash
# Application Build
mvn clean package -DskipTests

# Docker Image Build
docker build -t chromamon:latest .

# Deploy with Docker Compose
docker-compose -f docker-compose.prod.yml up -d
```

## Security

### Security Settings

- **HTTPS** required in production
- **JWT** with configurable expiration
- **CORS** configured for specific domains
- **Rate limiting** on APIs
- **Input validation** on all endpoints

### Best Practices Implemented

1. **Principle of least privilege** - Users with minimal permissions
2. **Full auditing** - Logging of all operations
3. **Input validation** - Data sanitization
4. **Password encryption** - BCrypt with salt
5. **Secure tokens** - JWT with HMAC-SHA512 signature

## Deploy in Production (If you want to)

### Production Configurations

1. **Environment variables**:
```env
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=jdbc:postgresql://prod-db:5432/transformer_analysis
MONGODB_URI=mongodb://prod-mongo:27017/audit_db
JWT_SECRET=<Your super strong secret>
```

2. **SSL/TLS**:
```yaml
server:
  ssl:
    enabled: true
    key-store: classpath:keystore.p12
    key-store-password: ${SSL_KEYSTORE_PASSWORD}
    key-store-type: PKCS12
```

3. **Reverse Proxy (NGINX)**:
```nginx
server {
    listen 80;
    server_name <Your domain>.com;
    return 301 https://$server_name$request_uri;
}

server {
    listen 443 ssl;
    server_name <Your domain>.com;
    
    ssl_certificate /path/to/certificate.crt;
    ssl_certificate_key /path/to/private.key;
    
    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

### Backup and Recovery

1. **PostgreSQL backup**:
```bash
# Daily backup
pg_dump -h localhost -U postgres <Your database name> > backup_$(date +%Y%m%d).sql

# Restore
psql -h localhost -U postgres <Your database name> < backup_<yyyyMMdd>.sql
```

2. **MongoDB backup**:
```bash
# Audit backup
mongodump --uri=“mongodb://admin:admin@localhost:27017/<Your mongoDb database>” --out=/backup/mongo

# Restore
mongorestore --uri=“mongodb://admin:admin@localhost:27017/<Your mongoDb database>” /backup/mongo/audit_db
```

## Advanced Configurations

### Performance Tuning

1. **JVM Settings**:
```bash
JAVA_OPTS="-Xms2g -Xmx4g -XX:+UseG1GC -XX:MaxGCPauseMillis=200"
```

2. **Connection Pool**:
```yaml
spring:
  datasource:
    hikari:
      maximum-pool-size: 50
      minimum-idle: 10
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
```

3. **Cache Configuration**:
```yaml
spring:
  cache:
    type: caffeine
    caffeine:
      spec: maximumSize=1000,expireAfterWrite=10m
```

### Templates customization

1. **Jasper Reports**:
```
src/main/resources/reports/templates/
├── analysis-report.jrxml
├── diagnostic-report.jrxml
├── transformer-summary.jrxml
└── historical-trend.jrxml
```

2. **Freemarker Templates**:
```
src/main/resources/templates/
├── layout.ftlh
├── dashboard.ftlh
├── analyses.ftlh
└── reports.ftlh
```

## API Documentation

### OpenAPI/Swagger

The complete API documentation is available at:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

### Integration Examples

#### Python
```python
import requests

# Login
response = requests.post('http://localhost:8080/api/v1/auth/login', 
                        json={'username': 'admin', 'password': 'admin123'})
token = response.json()['token']

# Create analyses
headers = {'Authorization': f'Bearer {token}'}
analysis_data = {
    'transformerId': 1,
    'analysisDate': '2024-03-15',
    'sampleDate': '2024-03-14',
    'laboratory': 'Central Lab',
    'hydrogenH2Ppm': 45.5
}

response = requests.post('http://localhost:8080/api/v1/analyses',
                        json=analysis_data, headers=headers)
```

#### JavaScript
```javascript
// Login
const loginResponse = await fetch('/api/v1/auth/login', {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({username: 'admin', password: 'admin123'})
});

const {token} = await loginResponse.json();

// Search analyses
const analysesResponse = await fetch('/api/v1/analyses', {
    headers: {'Authorization': `Bearer ${token}`}
});

const analyses = await analysesResponse.json();
```

## FAQ (Frequently Asked Questions)

### Q: How do I change the admin user password?
**A**: Go to `/users`, find the admin user, and use the “Change Password” option.

### Q: How do I import batch analyses?
**A**: Use the `/api/v1/analyses/import` endpoint with an Excel/CSV file in the specified format.

### Q: How do I configure new diagnostic methods?
**A**: Implement the `DiagnosticMethod` interface in the `diagnostic.domain.method` package.

### Q: How do I customize reports?
**A**: Edit the JasperReports templates in `src/main/resources/reports/templates/`, I recommend downloading [Jaspersoft Studio Community Edition](https://community.jaspersoft.com/download-jaspersoft/download-jaspersoft/) before doing anything else.

### Q: How to configure LDAP integration?
**A**: Configure the Spring LDAP properties in `application.yml`:

```yaml
spring:
  ldap:
    urls: ldap://localhost:389
    base: dc=company,dc=com
    username: cn=admin,dc=company,dc=com
    password: admin
```

## Contribution

### How to Contribute

1. Fork the project
2. Create a branch for your feature (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am ‘Add new feature’`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Open a Pull Request

### Code Standards

- Use **Java 21** features
- Follow **Spring Boot** conventions
- Maintain **test coverage > 80%**
- Use **Conventional Commits**
- Document APIs with the **OpenAPI** annotations

### Mandatory Tests

```bash
# Unit tests
mvn test

# Integration tests
mvn integration-test

# Quality analysis
mvn sonar:sonar

# Security check
mvn dependency-check:check
```

## License

This project is licensed under the MIT License—see the [LICENSE](LICENSE) file for details.

**Developed to help reduce the risk of accidents and possible explosions caused by the generation of combustible gases in electrical substation transformers.**
