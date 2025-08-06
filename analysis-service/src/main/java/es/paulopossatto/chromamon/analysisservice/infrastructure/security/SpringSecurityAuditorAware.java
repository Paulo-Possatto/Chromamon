package es.paulopossatto.chromamon.analysisservice.infrastructure.security;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Auditor Aware for the JWT token.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

  private JwtUtils jwtUtils;

  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of(jwtUtils.getSubject());
  }
}
