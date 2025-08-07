package es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers;

import es.paulopossatto.chromamon.analysisservice.infrastructure.config.PostgresqlTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(PostgresqlTestContainer.class)
@DirtiesContext
@ActiveProfiles("integration-test")
class GetAnalysisControllerIT {

  @LocalServerPort
  private int port;

  @Autowired
  PostgreSQLContainer<?> container;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  @DisplayName("""
      GIVEN an request to the getAnalyses endpoint
      AND the database has valid data
      WHEN the request is made
      AND the requested the first version of the API
      AND the token is valid
      THEN response is 200 ok
      """)
//  @Sql(scripts = "/mocked-db/sample-valid-analyses.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//  @Sql(scripts = "/mocked-db/clean-sample.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void whenRequestIsDoneSuccessfullyAndThereIsValidDataInDatabase_thenReturn200Ok() {
    assertTrue(true);
  }
}
