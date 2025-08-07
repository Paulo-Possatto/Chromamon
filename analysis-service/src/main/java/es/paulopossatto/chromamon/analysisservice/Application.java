package es.paulopossatto.chromamon.analysisservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/** The main application class. */
@SpringBootApplication
@Slf4j
@EnableCaching
@ComponentScan({
  "es.paulopossatto.chromamon.analysisservice.infrastructure.mapper",
  "es.paulopossatto.chromamon"
})
@OpenAPIDefinition(
    info =
        @Info(
            title = "Chromamon Analysis Service API",
            version = "1.0.0",
            description = "API Documentation for Analysis Service for the Chromamon project",
            contact = @Contact(name = "Paulo Possatto", url = "https://github.com/Paulo-Possatto"),
            license =
                @License(
                    name = "MIT License",
                    url = "https://github.com/Paulo-Possatto/Chromamon/blob/main/LICENSE")),
    tags = {
      @Tag(
          name = "Get Analysis",
          description = "All resources related to retrieve analysis data from the database"),
      @Tag(
          name = "Post Analysis",
          description = "All resources related to save analysis data into the database"),
      @Tag(
          name = "Put Analysis",
          description = "All resources related to modify analysis data saved into the database"),
      @Tag(
          name = "Delete Analysis",
          description = "All resources related to erase analysis data into the database")
    },
    servers = {
      @Server(url = "http://localhost:8080", description = "Local environment for testing")
    })
public class Application {

  private static final String START_LOG = "--- Analysis Service started successfully in {} ms";

  /**
   * The main method for the application.
   *
   * @param args the system arguments
   */
  public static void main(String[] args) {
    Instant start = Instant.now();
    SpringApplication.run(Application.class, args);
    Instant end = Instant.now();

    log.info(String.join("", Collections.nCopies(100, "-")));
    log.info(START_LOG, Duration.between(start, end).toMillis());
    log.info(String.join("", Collections.nCopies(100, "-")));
  }
}
