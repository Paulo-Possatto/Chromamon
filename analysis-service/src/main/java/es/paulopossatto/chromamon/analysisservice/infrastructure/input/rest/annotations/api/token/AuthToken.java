package es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.annotations.api.token;

import es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers.constants.ApiConstants;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthToken {

  String headerName() default ApiConstants.TOKEN_HEADER_NAME;

  boolean autoGenerate() default true;
}
