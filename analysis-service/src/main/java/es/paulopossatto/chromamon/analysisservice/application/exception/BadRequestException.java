package es.paulopossatto.chromamon.analysisservice.application.exception;

import org.springframework.http.HttpStatus;

/** Custom exception for 400 response. */
public class BadRequestException extends CustomException {
  public BadRequestException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }
}
