package es.paulopossatto.chromamon.analysisservice.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

/** The containers for integrated tests. */
@Testcontainers
@Slf4j
public class PostgresqlTestContainer {

  /** Generates the database container and run flyway for testing. */
  private static final PostgreSQLContainer<?> postgreSqlContainer =
      new PostgreSQLContainer<>("postgres:15-alpine")
          .withDatabaseName("chromamon_test")
          .withUsername("test")
          .withPassword("test")
          .withReuse(false);

  static {
    postgreSqlContainer.start();
    Flyway flyway =
        Flyway.configure()
            .dataSource(
                postgreSqlContainer.getJdbcUrl(),
                postgreSqlContainer.getUsername(),
                postgreSqlContainer.getPassword())
            .load();
    flyway.migrate();
  }

  /** Sets the properties for the test container. */
  @DynamicPropertySource
  public static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSqlContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSqlContainer::getUsername);
    registry.add("spring.datasource.password", postgreSqlContainer::getPassword);
    registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
    registry.add("spring.flyway.locations", () -> "classpath:db/migration");
    registry.add("spring.flyway.enabled", () -> "false");
  }

  /** Stops the container whenever the tests finish. */
  public static void stopContainer() {
    if (postgreSqlContainer != null && postgreSqlContainer.isRunning()) {
      postgreSqlContainer.stop();
    }
  }
}
