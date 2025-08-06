package es.paulopossatto.chromamon.analysisservice.application.exception;

import org.springframework.http.HttpStatus;

/** Custom exception for 404 responses. */
public class NotFoundException extends CustomException {
  public NotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }
}
