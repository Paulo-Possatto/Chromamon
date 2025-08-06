package es.paulopossatto.chromamon.analysisservice.application.exception;

import org.springframework.http.HttpStatus;

/** The custom exception 401 responses. */
public class UnauthorizedException extends CustomException {
  public UnauthorizedException(String message) {
    super(message, HttpStatus.UNAUTHORIZED);
  }
}
