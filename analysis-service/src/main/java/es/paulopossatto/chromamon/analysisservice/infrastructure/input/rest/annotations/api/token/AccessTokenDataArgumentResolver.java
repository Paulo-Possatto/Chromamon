package es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.annotations.api.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.paulopossatto.chromamon.analysisservice.application.exception.BadRequestException;
import es.paulopossatto.chromamon.analysisservice.application.exception.UnauthorizedException;
import es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers.constants.ApiConstants;
import es.paulopossatto.chromamon.analysisservice.infrastructure.security.token.AccessTokenData;
import es.paulopossatto.chromamon.analysisservice.infrastructure.security.token.LocalJwtService;
import es.paulopossatto.chromamon.analysisservice.infrastructure.security.token.Requester;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccessTokenDataArgumentResolver implements HandlerMethodArgumentResolver {

  private final LocalJwtService localJwtService;

  @Value("${spring.profiles.active:}")
  private String activeProfile;

  @Value("${jwt.secret-key}")
  private String secretKey;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return AccessTokenData.class.isAssignableFrom(parameter.getParameterType());
  }

  @Override
  public Object resolveArgument(
      MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory)
      throws Exception {

    HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

    String headerName = getHeaderName(parameter);

    String token = request.getHeader(headerName);

    if ((token == null || token.trim().isEmpty()) && shouldAutoGenerate(parameter)) {
      token = generateTokenForLocalEnvironment(request);
    }

    if (token == null || token.trim().isEmpty()) {
      throw new UnauthorizedException("JWT token not found in Header: " + headerName);
    }

    return decodeAndValidateToken(token);
  }

  private String getHeaderName(MethodParameter parameter) {
    AuthToken authToken = parameter.getParameterAnnotation(AuthToken.class);
    if (authToken != null && !authToken.headerName().isEmpty()) {
      return authToken.headerName();
    }
    return ApiConstants.TOKEN_HEADER_NAME;
  }

  private boolean shouldAutoGenerate(MethodParameter parameter) {
    if (!isLocalEnvironment()) {
      return false;
    }

    AuthToken authToken = parameter.getParameterAnnotation(AuthToken.class);
    return authToken == null || authToken.autoGenerate();
  }

  private String generateTokenForLocalEnvironment(HttpServletRequest request) {
    if (!isLocalEnvironment()) {
      return null;
    }

    try {
      String token = localJwtService.generateToken();
      log.debug("JWT Token generated for: {} {}", request.getMethod(), request.getRequestURI());
      return token;
    } catch (Exception e) {
      log.error("Error generating JWT token for test/development: {}", e.getMessage());
      return null;
    }
  }

  private AccessTokenData decodeAndValidateToken(String token) {
    try {
      DecodedJWT decodedJWT =
          JWT.require(getAlgorithm()).withIssuer("chroma-app").build().verify(token);

      Claims claims = extractClaims(decodedJWT);

      return new AccessTokenData(
          claims.getSubject(),
          claims.getIssuer(),
          claims.get("department", String.class),
          claims.get("nbf", Long.class),
          claims.get("iat", Long.class),
          claims.get("exp", Long.class),
          extractRequester(claims));

    } catch (Exception e) {
      log.error("Error decoding JWT token: {}", e.getMessage());
      throw new BadRequestException("Invalid JWT token: " + e.getMessage());
    }
  }

  private Claims extractClaims(DecodedJWT decodedJWT) {
    return new Claims() {
      @Override
      public String getSubject() {
        return decodedJWT.getSubject();
      }

      @Override
      public String getIssuer() {
        return decodedJWT.getIssuer();
      }

      @Override
      public <T> T get(String claimName, Class<T> requiredType) {
        Claim claim = decodedJWT.getClaim(claimName);
        if (claim.isNull()) return null;

        if (requiredType == String.class) {
          return (T) claim.asString();
        } else if (requiredType == Long.class) {
          return (T) claim.asLong();
        }
        return null;
      }
    };
  }

  private Requester extractRequester(Claims claims) {
    Map<String, Object> requesterMap = (Map<String, Object>) claims.get("requester", Map.class);
    if (requesterMap == null) return null;

    ObjectMapper mapper = new ObjectMapper();
    return mapper.convertValue(requesterMap, Requester.class);
  }

  private Algorithm getAlgorithm() {
    return Algorithm.HMAC256(secretKey);
  }

  private boolean isLocalEnvironment() {
    return activeProfile.contains("local") || activeProfile.contains("integration-test");
  }

  private interface Claims {
    String getSubject();

    String getIssuer();

    <T> T get(String claimName, Class<T> requiredType);
  }
}
