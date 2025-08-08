package es.paulopossatto.chromamon.analysisservice.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/** A custom exception to be handled dynamically. */
@Getter
public class CustomException extends RuntimeException {
  /** The HttpStatus of the exception. */
  private final HttpStatus status;

  /**
   * Custom exception constructor.
   *
   * @param message the message containing the details of the exception.
   * @param status the HttpStatus code of the exception.
   */
  public CustomException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
