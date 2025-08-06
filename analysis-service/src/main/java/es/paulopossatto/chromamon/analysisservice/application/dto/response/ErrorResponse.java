package es.paulopossatto.chromamon.analysisservice.application.dto.response;

import es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers.constants.SwaggerType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Builder;

/**
 * The error object for handled exceptions.
 *
 * @param statusCode the Status Code of the exception.
 * @param message the reason phrase of the status code.
 * @param details information about the reason of the exception.
 * @param timestamp the timestamp of when the exception happened.
 */
@Schema(
    name = "Error-Response",
    description = "The object that contains the information about the error",
    type = "object")
@Builder
public record ErrorResponse(
    @Schema(
            description = "The HTTP Status Code of the error",
            example = "404",
            type = SwaggerType.INTEGER)
        int statusCode,
    @Schema(
            description = "The HTTP Status message, saying what the code means",
            example = "Not Found",
            type = SwaggerType.STRING)
        String message,
    @Schema(
            description = "The details of what caused the error",
            example = "API Version not supported",
            type = SwaggerType.STRING)
        String details,
    @Schema(
            description = "The date and time of when the error occurred",
            example = "22-07-2025 10:23:30",
            type = SwaggerType.STRING)
        String timestamp) {
  public ErrorResponse {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    timestamp = LocalDateTime.now().format(formatter);
  }
}
