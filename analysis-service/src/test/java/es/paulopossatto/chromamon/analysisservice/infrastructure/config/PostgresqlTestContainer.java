package es.paulopossatto.chromamon.analysisservice.infrastructure.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

/** The test container for the PostgreSQL database. */
@TestConfiguration
public class PostgresqlTestContainer {

  private static final PostgreSQLContainer<?> postgreSqlContainer =
      new PostgreSQLContainer<>("postgres:15-alpine")
          .withDatabaseName("chromamon_test")
          .withUsername("test")
          .withPassword("test");

  static {
    postgreSqlContainer.start();
  }

  /**
   * Overrides the properties to use the test container database.
   *
   * @param registry the dynamic properties' registry.
   */
  @DynamicPropertySource
  public static void overrideProps(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSqlContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSqlContainer::getUsername);
    registry.add("spring.datasource.password", postgreSqlContainer::getPassword);
    registry.add("spring.flyway.url", postgreSqlContainer::getJdbcUrl);
    registry.add("spring.flyway.user", postgreSqlContainer::getUsername);
    registry.add("spring.flyway.password", postgreSqlContainer::getPassword);
    registry.add("spring.flyway.enabled", () -> "true");
  }

  /**
   * The bean to enable the test container.
   *
   * @return the test container bean.
   */
  @Bean
  @ServiceConnection
  public PostgreSQLContainer<?> postgreSqlContainer() {
    return postgreSqlContainer;
  }
}
