package es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.annotations.api.version;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/** The configuration class that calls the mapping class for the annotation. */
@Configuration
public class WebConfig implements WebMvcRegistrations {
  @Override
  public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
    return new CustomRequestMappingHandlerMapping();
  }
}
