package es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.annotations.api.version;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** The annotation that defines the API version using. */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {
  /**
   * The parameter for the ApiVersion annotation.
   *
   * @return the API Version.
   */
  String value();
}
