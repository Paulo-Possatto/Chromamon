package es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.annotations.api.version;

import es.paulopossatto.chromamon.analysisservice.application.exception.BadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

/** The class that verifies the API version annotation value. */
@RequiredArgsConstructor
@Slf4j
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {
  private final String apiVersion;

  @Override
  public ApiVersionCondition combine(ApiVersionCondition other) {
    return new ApiVersionCondition(other.apiVersion);
  }

  @Override
  public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
    String versionHeader = request.getHeader("X-API-Version");
    if (versionHeader == null || versionHeader.isEmpty()) {
      log.error("API version sent in the headers is empty or null");
      throw new BadRequestException("Header parameter 'X-API-Version' must not be empty");
    }
    if (versionHeader.equalsIgnoreCase(this.apiVersion)) {
      return this;
    } else if (!versionHeader.matches("^[vV][0-9]{0,9}$")) {
      log.error("API version sent in the headers is with an incorrect format");
      throw new BadRequestException(
          "'X-API-Version' must begin with 'V', followed by the version number");
    }
    return null;
  }

  @Override
  public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
    return 0;
  }
}
