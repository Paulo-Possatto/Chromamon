/**
 * Data Transfer Object layer - Objects used as a response body and request body.
 *
 * <p>This package contains all the DTOs needed as a request or as a response.
 *
 * <ul>
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.application.dto.response} - Responses
 *       DTOs
 *   <li>{@link es.paulopossatto.chromamon.analysisservice.application.dto.request} - Requests DTOs
 * </ul>
 *
 * <p>Responsibilities:
 *
 * <ul>
 *   <li>Generate the necessary response for each endpoint
 *   <li>When necessary, be the model for a body request
 *   <li>Be the schema model for Swagger.
 * </ul>
 *
 * @see es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers REST
 *     Controllers
 * @version 1.0.0
 * @since 2025-08-08
 * @author Paulo Possatto
 */
package es.paulopossatto.chromamon.analysisservice.application.dto;
