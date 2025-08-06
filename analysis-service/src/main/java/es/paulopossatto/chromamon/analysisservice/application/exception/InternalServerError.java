package es.paulopossatto.chromamon.analysisservice.application.exception;

import org.springframework.http.HttpStatus;

/** Custom exception for 500 response. */
public class InternalServerError extends CustomException {
  public InternalServerError(String message) {
    super(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
