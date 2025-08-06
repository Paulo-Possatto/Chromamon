package es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.annotations.api.version;

import java.lang.reflect.Method;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/** The handler for the annotation created. */
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
  @Override
  protected RequestCondition<?> getCustomMethodCondition(Method method) {
    ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
    return apiVersion != null ? new ApiVersionCondition(apiVersion.value()) : null;
  }
}
