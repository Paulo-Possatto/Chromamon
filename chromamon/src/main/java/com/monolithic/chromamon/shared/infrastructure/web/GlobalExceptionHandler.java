package com.monolithic.chromamon.shared.infrastructure.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

   @ExceptionHandler(RuntimeException.class)
   public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
      log.error("Runtime exception: ", ex);

      ErrorResponse errorResponse = ErrorResponse.builder()
         .timestamp(LocalDateTime.now())
         .status(HttpStatus.BAD_REQUEST.value())
         .error("Bad Request")
         .message(ex.getMessage())
         .path(request.getDescription(false).replace("uri=", ""))
         .build();

      return ResponseEntity.badRequest().body(errorResponse);
   }

   @ExceptionHandler(AccessDeniedException.class)
   public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
      log.warn("Access denied: {}", ex.getMessage());

      ErrorResponse errorResponse = ErrorResponse.builder()
         .timestamp(LocalDateTime.now())
         .status(HttpStatus.FORBIDDEN.value())
         .error("Forbidden")
         .message("Access Denied: " + ex.getMessage())
         .path(request.getDescription(false).replace("uri=", ""))
         .build();

      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
      log.warn("Validation error: {}", ex.getMessage());

      Map<String, String> errors = new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach((error) -> {
         String fieldName = ((FieldError) error).getField();
         String errorMessage = error.getDefaultMessage();
         errors.put(fieldName, errorMessage);
      });

      ErrorResponse errorResponse = ErrorResponse.builder()
         .timestamp(LocalDateTime.now())
         .status(HttpStatus.BAD_REQUEST.value())
         .error("Validation Failed")
         .message("Error validating fields")
         .validationErrors(errors)
         .build();

      return ResponseEntity.badRequest().body(errorResponse);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
      log.error("Unexpected error: ", ex);

      ErrorResponse errorResponse = ErrorResponse.builder()
         .timestamp(LocalDateTime.now())
         .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
         .error("Internal Server Error")
         .message("Internal Server Error")
         .path(request.getDescription(false).replace("uri=", ""))
         .build();

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
   }

   // ErrorResponse.java - Error Response DTO
   @lombok.Data
   @lombok.Builder
   @lombok.AllArgsConstructor
   @lombok.NoArgsConstructor
   public static class ErrorResponse {
      private LocalDateTime timestamp;
      private int status;
      private String error;
      private String message;
      private String path;
      private Map<String, String> validationErrors;
   }
}
