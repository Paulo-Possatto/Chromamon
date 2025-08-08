package es.paulopossatto.chromamon.analysisservice.infrastructure.output.persistence.repositories;

import es.paulopossatto.chromamon.analysisservice.infrastructure.config.PostgresqlTestContainer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.EXISTING_IDENTIFIER;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.EXISTING_SERIAL_NUMBER;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.NON_EXISTING_IDENTIFIER;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.NON_EXISTING_SERIAL_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@Import(PostgresqlTestContainer.class)
@ActiveProfiles("integration-test")
@Tag("integration")
@Sql(scripts = "/mocked-db/sample-valid-analyses.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class AnalysisJpaTest {

  @Autowired
  private AnalysisJpaRepository analysisJpaRepository;

  @Test
  @DisplayName("""
      WHEN PostgreSQL database test container starts
      THEN should create database with Flyway scripts
      """)
  void shouldPopulate_whenFlywayInitiates(){
    assertEquals(3, analysisJpaRepository.findAll().size());
  }

  @Test
  @DisplayName("""
      GIVEN an transformer serial number that exists in the database
      WHEN the validation method is called
      THEN should return true
      """)
  void shouldReturnTrue_whenExistingSerialNumberIsSearched(){
    assertTrue(analysisJpaRepository.isSerialNumberExists(EXISTING_SERIAL_NUMBER));
  }

  @Test
  @DisplayName("""
      GIVEN an transformer serial number that doesn't exists in the database
      WHEN the validation method is called
      THEN should return false
      """)
  void shouldReturnFalse_whenNonExistingSerialNumberIsSearched(){
    assertFalse(analysisJpaRepository.isSerialNumberExists(NON_EXISTING_SERIAL_NUMBER));
  }

  @Test
  @DisplayName("""
      GIVEN an identifier that exists in the database
      WHEN the validation method is called
      THEN should return true
      """)
  void shouldReturnTrue_whenExistingIdentifierIsSearched(){
    assertTrue(analysisJpaRepository.isIdentifierExists(EXISTING_IDENTIFIER));
  }

  @Test
  @DisplayName("""
      GIVEN an identifier that doesn't exists in the database
      WHEN the validation method is called
      THEN should return false
      """)
  void shouldReturnFalse_whenNonExistingIdentifierIsSearched(){
    assertFalse(analysisJpaRepository.isIdentifierExists(NON_EXISTING_IDENTIFIER));
  }
}
