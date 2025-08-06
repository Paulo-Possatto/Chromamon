package es.paulopossatto.chromamon.analysisservice.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.paulopossatto.chromamon.analysisservice.infrastructure.security.token.LocalJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the JWT Service bean.
 */
@Configuration
@RequiredArgsConstructor
public class JwtConfig {

  private final ObjectMapper objectMapper;

  @Bean
  public LocalJwtService jwtService() {
    return new LocalJwtService(objectMapper);
  }
}
