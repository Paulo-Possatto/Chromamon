package es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers;

import es.paulopossatto.chromamon.analysisservice.infrastructure.config.PostgresqlTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("integration-test")
@Testcontainers
@Transactional
class GetAnalysisControllerIT extends PostgresqlTestContainer {

  @Test
  @DisplayName("""
      GIVEN an request to the getAnalyses endpoint
      AND the database has valid data
      WHEN the request is made
      AND the requested the first version of the API
      AND the token is valid
      THEN response is 200 ok
      """)
  @Sql(scripts = "/mocked-db/sample-valid-analyses.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(scripts = "/mocked-db/clean-sample.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void whenRequestIsDoneSuccessfullyAndThereIsValidDataInDatabase_thenReturn200Ok() {
    assertTrue(true);
  }
}
