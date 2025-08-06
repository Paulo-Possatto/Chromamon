package es.paulopossatto.chromamon.analysisservice.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/** A custom exception to be handled dynamically. */
@Getter
public class CustomException extends RuntimeException {
  private final HttpStatus status;

  public CustomException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
