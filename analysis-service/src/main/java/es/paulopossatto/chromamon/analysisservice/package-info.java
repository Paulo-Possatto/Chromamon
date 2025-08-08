/**
 * Analysis Service - Service responsible for storing, processing and analysing each and every
 * chromatographic analysis made.
 *
 * <p>This application follows the principles of Hexagonal Architecture (Ports and Adapters) with a
 * modular structure organized into three main layers:
 *
 * <ul>
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.domain} - Contains the core business
 *       logic (core domain)
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.application} - Orchestrate application
 *       flows
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.infrastructure} - Concrete technical
 *       implementations
 * </ul>
 *
 * <p>Main technologies:
 *
 * <ul>
 *   <li>Java 21
 *   <li>Spring Boot 3.5.3
 *   <li>Docker
 *   <li>PostgreSQL
 *   <li>Kafka
 *   <li>Wiremock
 *   <li>OpenAPI (Swagger)
 *   <li>Lombok
 *   <li>JUnit5
 *   <li>Mockito
 *   <li>MapStruct
 *   <li>Testcontainers
 * </ul>
 *
 * <p>Testing methods:
 *
 * <ul>
 *   <li>Integration Tests
 *   <li>Unit Tests
 *   <li>Contract Tests (Pact Tests)
 * </ul>
 *
 * <p>Future Testing methods:
 *
 * <ul>
 *   <li>BDD-Cucumber Tests
 *   <li>Load Tests (JMeter)
 *   <li>Resilience Tests (Chaos Engineering)
 *   <li>Security Tests
 * </ul>
 *
 * <p>Project conventions:
 *
 * <ul>
 *   <li>Preference for records over classes for DTOs
 *   <li>Use of sealed interfaces for domain models
 *   <li>API versioning following SEMVER (Semantic versioning)
 *   <li>Use of OpenAPI annotations to generate Swagger
 *   <li>Do <strong>not</strong> use * import
 *   <li>Add the Javadoc for created classes and methods
 * </ul>
 *
 * <p>Design principles:
 *
 * <ol>
 *   <li>Dependency always towards the center (domain)
 *   <li>Testability as an architectural requirement
 *   <li>Clear separation between business rules and technical details
 * </ol>
 *
 * @see <a href="https://www.postgresql.org/">PostgreSQL website</a>
 * @see <a href="https://www.docker.com/">Docker website</a>
 * @see <a href="https://kafka.apache.org/">Apache Kafka website</a>
 * @see <a href="https://wiremock.org/">Wiremock website</a>
 * @see <a href="https://www.openapis.org/">OpenAPI website</a>
 * @see <a href="https://swagger.io/">Swagger website</a>
 * @see <a href="https://projectlombok.org/">Lombok website</a>
 * @see <a href="https://junit.org/">JUnit5 website</a>
 * @see <a href="https://site.mockito.org/">Mockito website</a>
 * @see <a href="https://mapstruct.org/">MapStruct website</a>
 * @see <a href="https://testcontainers.com/">Testcontainers website</a>
 * @see <a href="https://semver.org/">SEMVER website</a>
 * @see es.paulopossatto.chromamon.analysisservice.domain Domain layer
 * @see es.paulopossatto.chromamon.analysisservice.application Application layer
 * @see es.paulopossatto.chromamon.analysisservice.infrastructure Infrastructure layer
 * @version 1.0.0
 * @since 2025-08-08
 * @author Paulo Possatto
 */
package es.paulopossatto.chromamon.analysisservice;
