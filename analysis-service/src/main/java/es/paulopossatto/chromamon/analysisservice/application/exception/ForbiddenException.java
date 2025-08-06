package es.paulopossatto.chromamon.analysisservice.application.exception;

import org.springframework.http.HttpStatus;

/** Custom exception for 403 responses. */
public class ForbiddenException extends CustomException {
  public ForbiddenException(String message) {
    super(message, HttpStatus.FORBIDDEN);
  }
}
