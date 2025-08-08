/**
 * Infrastructure Layer - Concrete implementations of external adapters.
 *
 * <p>This layer contains all specific technical implementations:
 *
 * <ul>
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.infrastructure.config} - Spring
 *       configurations
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.infrastructure.entity} - Database
 *       entities
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.infrastructure.input} - REST Controllers,
 *       send Kafka messages and custom annotations used by the controller
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.infrastructure.mapper} - Mappers
 *       interfaces using MapStruct to convert to domain
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.infrastructure.output} - Calls external
 *       services
 * </ul>
 *
 * <p>Characteristics:
 *
 * <ul>
 *   <li>Implements the ports defined in the domain/application layers
 *   <li>Contains technical details and framework dependencies
 *   <li>Can be replaced without affecting the inner layers
 * </ul>
 *
 * @see <a href="https://spring.io/projects/spring-boot">Spring Boot Framework</a>
 * @see es.paulopossatto.chromamon.analysisservice.application Application layer
 * @version 1.0.0
 * @since 2025-08-08
 * @author Paulo Possatto
 */
package es.paulopossatto.chromamon.analysisservice.infrastructure;
