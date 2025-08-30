package com.monolithic.chromamon.shared.domain.exception;

public class InvalidTokenException extends RuntimeException {
   public InvalidTokenException(String message) {
      super(message);
   }

   public InvalidTokenException(String message, Throwable cause) {
      super(message, cause);
   }
}
