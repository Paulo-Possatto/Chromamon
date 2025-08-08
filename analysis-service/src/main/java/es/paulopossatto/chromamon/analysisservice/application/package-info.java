/**
 * Application Layer - Coordinates the application workflow.
 *
 * <p>This layer orchestrates operations between the domain layer and external adapters.
 *
 * <p>Contains:
 *
 * <ul>
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.application.usecases} - Use cases and
 *       application services
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.application.converter} - Converters from
 *       domain to response
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.application.dto} - Input and Output Data
 *       Transfer Objects
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.application.exception} -
 *       Application-specific exceptions
 * </ul>
 *
 * <p>Responsibilities:
 *
 * <ul>
 *   <li>Define input contracts (interfaces) for the UI/API/CLI
 *   <li>Workflow coordination
 *   <li>Conversion between DTOs and domain objects
 *   <li>Basic error handling and validation
 * </ul>
 *
 * @see es.paulopossatto.chromamon.analysisservice.domain Domain layer
 * @version 1.0.0
 * @since 2025-08-08
 * @author Paulo Possatto
 */
package es.paulopossatto.chromamon.analysisservice.application;
