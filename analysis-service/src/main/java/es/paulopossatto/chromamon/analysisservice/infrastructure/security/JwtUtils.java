package es.paulopossatto.chromamon.analysisservice.infrastructure.security;

import es.paulopossatto.chromamon.analysisservice.application.exception.BadRequestException;
import es.paulopossatto.chromamon.analysisservice.application.exception.ForbiddenException;
import es.paulopossatto.chromamon.analysisservice.application.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** The utils class that's responsible for managing the access token. */
@Component
public class JwtUtils {

  private final Key key;

  @Getter private String subject;

  public JwtUtils(@Value("${jwt.secret-key}") String secretKey) {
    this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
  }

  private static List<String> extractRoles(Claims claims) {
    if (claims.get("roles") instanceof List<?>) {
      return (List<String>) claims.get("roles", List.class);
    }
    return Collections.emptyList();
  }

  private static boolean extractIsAdmin(Claims claims) {
    if (claims.get("admin") instanceof Boolean) {
      return claims.get("admin", Boolean.class);
    }
    return false;
  }

  private Claims validateAndExtractClaims(String jwtToken) {
    try {
      Jws<Claims> claimsJws =
          Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);

      return claimsJws.getBody();
    } catch (Exception e) {
      throw new BadRequestException("Invalid or expired JWT token: " + e.getMessage());
    }
  }

  private boolean isValid(String jwtToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * The method that validates the token and the possible permission to a resource.
   *
   * @param jwtToken the string value of the JWT token.
   * @param requiredPermissions a series of roles for the type String.
   */
  public void checkJwtAndPermissions(String jwtToken, String... requiredPermissions) {
    if (jwtToken == null || jwtToken.isBlank()) {
      throw new UnauthorizedException("Header parameter 'X-Chroma-Token' must not be empty");
    }

    if (!isValid(jwtToken)) {
      throw new BadRequestException("Header parameter 'X-Chroma-Token' has a invalid value");
    }

    Claims claims = validateAndExtractClaims(jwtToken);

    if (claims == null || claims.isEmpty()) {
      throw new UnauthorizedException("Header parameter 'X-Chroma-Token' has no claims");
    }

    if (extractIsAdmin(claims)) {
      return;
    }

    if (claims.getExpiration() != null && claims.getExpiration().before(new Date())) {
      throw new UnauthorizedException(
          "Header parameter 'X-Chroma-Token' is expired, please refresh it");
    }

    if (claims.getIssuer().isEmpty() || !claims.getIssuer().equals("chroma-app")) {
      throw new UnauthorizedException(
          "Header parameter 'X-Chroma-Token' was not issued by the application");
    }

    List<String> userRoles = extractRoles(claims);

    if (userRoles.isEmpty() && !extractIsAdmin(claims)) {
      throw new UnauthorizedException(
          "Header parameter 'X-Chroma-Token' does not contain roles for the user");
    }

    boolean hasPermission = Arrays.stream(requiredPermissions).anyMatch(userRoles::contains);

    if (!hasPermission && !extractIsAdmin(claims)) {
      throw new ForbiddenException("The user has no permission to access this resource");
    }

    this.subject = claims.getSubject();
  }
}
