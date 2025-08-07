package es.paulopossatto.chromamon.analysisservice.application.exception;

import es.paulopossatto.chromamon.analysisservice.application.dto.response.ErrorResponse;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/** Class that handle custom and generic exceptions. */
@RestControllerAdvice
public class ApplicationExceptionHandler {

  private static ErrorResponse toErrorResponse(int errorCode, String message, String details) {
    return ErrorResponse.builder().statusCode(errorCode).message(message).details(details).build();
  }

  /**
   * The handler for any Custom Exception.
   *
   * @param e the CustomException class.
   * @return an ResponseEntity with the treated information.
   */
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponse> handleBadRequestException(CustomException e) {
    return ResponseEntity.status(e.getStatus())
        .contentType(MediaType.APPLICATION_JSON)
        .body(
            toErrorResponse(
                e.getStatus().value(), e.getStatus().getReasonPhrase(), e.getMessage()));
  }

  /**
   * Handler for exception when the API Version is not found.
   *
   * @param e the NoResourceFoundException.
   * @return an ResponseEntity with the information about the 404 error.
   * @throws NoResourceFoundException for any other reason besides the API Version.
   */
  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException e)
      throws NoResourceFoundException {
    if (e.getMessage().equals("No static resource api/analyses.")) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(toErrorResponse(404, "Not Found", "API Version not supported"));
    }
    throw e;
  }

  /**
   * Handles the error if the property is not found in the request.
   *
   * @param e PropertyReferenceException class.
   * @return a Response Entity of Bad Request.
   */
  @ExceptionHandler(PropertyReferenceException.class)
  public ResponseEntity<ErrorResponse> handlePropertyReferenceException(
      PropertyReferenceException e) {
    if (e.getMessage().contains("No property") && e.getMessage().contains("found for type")) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(toErrorResponse(400, "Bad Request", e.getMessage()));
    }
    throw e;
  }
}
