package es.paulopossatto.chromamon.analysisservice.infrastructure.output.persistence.repositories;

import es.paulopossatto.chromamon.analysisservice.infrastructure.config.PostgresqlTestContainer;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.AnalysisEntity;
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

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.END_DATE_WITHOUT_ANAYLSES;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.END_DATE_WITH_ANAYLSES;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.EXISTING_IDENTIFIER;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.EXISTING_SERIAL_NUMBER;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.NON_EXISTING_IDENTIFIER;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.NON_EXISTING_SERIAL_NUMBER;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.START_DATE_WITHOUT_ANAYLSES;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.START_DATE_WITH_ANAYLSES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The test methods for the possible uses from the Analysis JPA interface.
 */
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
      GIVEN the start of the test class
      WHEN PostgreSQL database test container starts
      THEN should create database with Flyway scripts
      AND populate with test SQL file
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

  @Test
  @DisplayName("""
      GIVEN an identifier that exists in the database
      WHEN the findByIdentifier method is called
      THEN should return the analysis with given identifier
      """)
  void shouldReturnOptionalOfIdentifier_whenFindByIdentifierMethodIsCalled(){
    Optional<AnalysisEntity> analysis = analysisJpaRepository.findByIdentifier(EXISTING_IDENTIFIER);
    assertTrue(analysis.isPresent());
    assertEquals(EXISTING_IDENTIFIER, analysis.get().getIdentifier());
    assertEquals(EXISTING_SERIAL_NUMBER, analysis.get().getTransSerNum());
  }

  @Test
  @DisplayName("""
      GIVEN an identifier that does not exists in the database
      WHEN the findByIdentifier method is called
      THEN should return an empty optional
      """)
  void shouldReturnEmptyOptional_whenFindByIdentifierMethodIsCalled(){
    Optional<AnalysisEntity> analysis = analysisJpaRepository.findByIdentifier(NON_EXISTING_IDENTIFIER);
    assertTrue(analysis.isEmpty());
  }

  @Test
  @DisplayName("""
      GIVEN an serial number that exists in the database
      WHEN the findBySerialNumber method is called
      THEN should return a list containing the found analyses
      """)
  @Sql(scripts = "/mocked-db/add-valid-analysis.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(scripts = "/mocked-db/erase-additional-analysis.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void shouldReturnListOfAnalyses_whenFindBySerialNumberMethodIsCalled(){
    List<AnalysisEntity> analysis = analysisJpaRepository.findBySerialNumber(EXISTING_SERIAL_NUMBER);
    assertFalse(analysis.isEmpty());
    assertEquals(2, analysis.size());
    assertEquals(EXISTING_SERIAL_NUMBER, analysis.getFirst().getTransSerNum());
    assertEquals(EXISTING_SERIAL_NUMBER, analysis.getLast().getTransSerNum());
  }

  @Test
  @DisplayName("""
      GIVEN an serial number that does not exists in the database
      WHEN the findBySerialNumber method is called
      THEN should return an empty list
      """)
  void shouldReturnEmptyList_whenFindBySerialNumberMethodIsCalled(){
    List<AnalysisEntity> analysis = analysisJpaRepository.findBySerialNumber(NON_EXISTING_SERIAL_NUMBER);
    assertTrue(analysis.isEmpty());
  }

  @Test
  @DisplayName("""
      GIVEN a start date value
      AND an end date value
      AND has analyses in this period
      WHEN the findByPeriod method is called
      THEN should return a list of analyses done in the given period
      """)
  void shouldReturnListOfAnalyses_whenFindByDatePeriodMethodIsCalled(){
    List<AnalysisEntity> analyses = analysisJpaRepository.findByPeriod(
        START_DATE_WITH_ANAYLSES, END_DATE_WITH_ANAYLSES
    );
    assertFalse(analyses.isEmpty());
    assertEquals(2, analyses.size());
    OffsetDateTime analysisFirstDateTime = analyses.getFirst().getAnalysisDateTime();
    OffsetDateTime analysisLastDateTime = analyses.getLast().getAnalysisDateTime();
    assertTrue(analysisFirstDateTime.isAfter(START_DATE_WITH_ANAYLSES));
    assertTrue(analysisFirstDateTime.isBefore(END_DATE_WITH_ANAYLSES));
    assertTrue(analysisLastDateTime.isAfter(START_DATE_WITH_ANAYLSES));
    assertTrue(analysisLastDateTime.isBefore(END_DATE_WITH_ANAYLSES));
  }

  @Test
  @DisplayName("""
      GIVEN a start date value
      AND an end date value
      AND doesn't have analyses in this period
      WHEN the findByPeriod method is called
      THEN should return an empty list
      """)
  void shouldReturnEmptyList_whenFindByDatePeriodMethodIsCalled(){
    List<AnalysisEntity> analyses = analysisJpaRepository.findByPeriod(
        START_DATE_WITHOUT_ANAYLSES, END_DATE_WITHOUT_ANAYLSES
    );
    assertTrue(analyses.isEmpty());
  }
}
