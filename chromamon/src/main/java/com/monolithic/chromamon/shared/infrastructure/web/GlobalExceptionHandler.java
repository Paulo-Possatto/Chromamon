package com.monolithic.chromamon.shared.infrastructure.web;

import com.monolithic.chromamon.shared.domain.security.SwaggerConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

/** Exception Handler for the application. */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(
      RuntimeException ex, WebRequest request) {
    log.error("Runtime exception: ", ex);

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Bad Request")
            .message(ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .build();

    return ResponseEntity.badRequest().body(errorResponse);
  }

  /**
   * Exception handler for when Client Error happens.
   *
   * @param ex the HttpClientErrorException class
   * @param request the web request
   * @return the ResponseEntity with the error details
   */
  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<ErrorResponse> handleHttpClientErrorException(
      HttpClientErrorException ex, WebRequest request) {
    log.error("HttpClientErrorException: ", ex);

    HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(status.value())
            .error(status.getReasonPhrase())
            .message(ex.getStatusText())
            .path(request.getDescription(false).replace("uri=", ""))
            .build();

    return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> handleAccessDeniedException(
      AccessDeniedException ex, WebRequest request) {
    log.warn("Access denied: {}", ex.getMessage());

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.FORBIDDEN.value())
            .error("Forbidden")
            .message("Access Denied: " + ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .build();

    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(
      MethodArgumentNotValidException ex, WebRequest request) {
    log.warn("Validation error: {}", ex.getMessage());

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message("Error validating fields")
            .path(request.getDescription(false).replace("uri=", ""))
            .validationErrors(errors)
            .build();

    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
    log.error("Unexpected error: ", ex);

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .message("Internal Server Error")
            .path(request.getDescription(false).replace("uri=", ""))
            .build();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }

  /**
   * Error response for any exceptions caught.
   *
   * @param timestamp the information of when it happened.
   * @param status the status code of the error
   * @param error the literal error
   * @param message the details of the error
   * @param path which path the error happened
   * @param validationErrors the validation constraints when not satisfied
   */
  @lombok.Builder
  @Schema(
      name = "ErrorResponse",
      description = "Response object for when an error occurs",
      type = SwaggerConstants.OBJECT,
      example =
          """
         {
            "timestamp": "The timestamp of when the error happened",
            "status": "The HTTP Status code of the error",
            "error": "The HTTP Error type",
            "message": "The details of the error"
            "path": "The API path of the error",
            "validationErrors": "Object containing the validation constraints that were not satisfied"
         }
         """)
  public record ErrorResponse(
      LocalDateTime timestamp,
      int status,
      String error,
      String message,
      String path,
      Map<String, String> validationErrors) {
    @Override
    public String toString() {
      return "{\n"
          + "\t\"timestamp\": \""
          + timestamp
          + "\",\n"
          + "\t\"status\": \""
          + status
          + "\",\n"
          + "\t\"error\": \""
          + error
          + "\",\n"
          + "\t\"message\": \""
          + message
          + "\",\n"
          + "\t\"path\": \""
          + path
          + "\",\n"
          + "\t\"validationErrors\": \""
          + validationErrors
          + "\"\n"
          + '}';
    }
  }
}
