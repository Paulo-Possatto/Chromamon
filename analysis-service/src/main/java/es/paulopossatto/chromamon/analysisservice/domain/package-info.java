/**
 * Domain Layer - Contains the core of the application and business rules.
 *
 * <p>This layer is the innermost layer and does not depend on any other layer. It contains:
 *
 * <ul>
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.domain.model} - Domain entities and value
 *       objects
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.domain.service} - Domain services and
 *       business rules
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.domain.ports} - Repository interfaces
 *       (output ports)
 * </ul>
 *
 * <p>Principals:
 *
 * <ul>
 *   <li>No class here should have external dependencies (frameworks, databases, etc.).
 *   <li>Interfaces define the contracts that the infrastructure layer must implement.
 *   <li>Entities should encapsulate behavior and validate their own state.
 * </ul>
 *
 * @see <a href="https://alistair.cockburn.us/hexagonal-architecture/">Hexagonal Architecture</a>
 * @version 1.0.0
 * @since 2025-08-08
 * @author Paulo Possatto
 */
package es.paulopossatto.chromamon.analysisservice.domain;
