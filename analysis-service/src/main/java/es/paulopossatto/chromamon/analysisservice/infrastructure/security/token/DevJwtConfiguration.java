package es.paulopossatto.chromamon.analysisservice.infrastructure.security.token;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

/** Launches information related to the generated access token. */
@Configuration
@Profile({"local", "integration-test"})
@Slf4j
public class DevJwtConfiguration {

  /** Log the information about the token. */
  @EventListener
  public void handleApplicationReady(ApplicationReadyEvent event) {
    log.info(
        """
        === DEVELOPMENT MODE ENABLED ===
        The token will be automatically generated for requests on localhost without X-Chroma-Token
        Token properties configured at: src/test/resources/local-access-token.json
        ================================
        """);
  }
}
