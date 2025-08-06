package es.paulopossatto.chromamon.analysisservice.infrastructure.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.paulopossatto.chromamon.analysisservice.application.exception.InternalServerError;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

/**
 * This class generates a access token for local and integration tests wheh X-Chroma-Token is not
 * provided.
 */
@Service
@Profile({"local", "integration-test"})
@Slf4j
@RequiredArgsConstructor
public class LocalJwtService {

  private final ObjectMapper objectMapper;

  @Value("${jwt.secret-key}")
  private String secretKey;

  private AccessTokenData tokenData;
  private Map<String, Object> requesterData;

  /** Load the token claim set. */
  @PostConstruct
  public void loadTokenData() {
    try {
      ClassPathResource resource = new ClassPathResource("local-access-token.json");
      this.tokenData = objectMapper.readValue(resource.getInputStream(), AccessTokenData.class);
      this.requesterData = objectMapper.convertValue(tokenData.getRequester(), Map.class);
      log.info("JWT token for local and testing generated");
    } catch (IOException e) {
      log.error("Error loading local-access-token.json: {}", e.getMessage());
      throw new InternalServerError("Not able to generate token for local and test usage: " + e);
    }
  }

  /**
   * Generates the token based on the information provided.
   *
   * @return the String value of the token.
   */
  public String generateToken() {
    try {
      long currentTime = System.currentTimeMillis();
      long expirationTime = currentTime + 3600;

      Algorithm algorithm = Algorithm.HMAC256(secretKey);

      JWTCreator.Builder jwtBuilder =
          JWT.create()
              .withSubject(tokenData.getSubject())
              .withIssuer(tokenData.getIssuer())
              .withClaim("department", tokenData.getDepartment())
              .withNotBefore(new Date(tokenData.getNotBefore()))
              .withIssuedAt(new Date(currentTime))
              .withExpiresAt(new Date(expirationTime))
              .withClaim("requester", requesterData);

      log.info("Token generated. Signing now...");
      return jwtBuilder.sign(algorithm);
    } catch (Exception e) {
      log.error("Error generating development token: {}", e.getMessage());
      throw new InternalServerError(e.getMessage());
    }
  }
}
