package es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers;

import es.paulopossatto.chromamon.analysisservice.application.dto.response.AnalysesResponses;
import es.paulopossatto.chromamon.analysisservice.application.dto.response.ErrorResponse;
import es.paulopossatto.chromamon.analysisservice.infrastructure.config.PostgresqlTestContainer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpClientErrorException;

import static es.paulopossatto.chromamon.analysisservice.constants.PathsContants.GET_ANALYSES_PATH;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.API_VERSION_HEADER_NAME;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.API_VERSION_HEADER_VALUE_V1;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.BAD_API_VERSION_VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(PostgresqlTestContainer.class)
@DirtiesContext
@ActiveProfiles("integration-test")
@Tag("integration")
@Slf4j
class GetAnalysisControllerIT {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  @DisplayName("""
      GIVEN an request to the getAnalyses endpoint
      AND the database has no data
      WHEN the request is made
      AND is requested the first version of the API
      AND no pageable parameter is added
      THEN response is 204 no content
      """)
  void whenRequestIsMadeAndThereIsNoDataInDatabase_thenReturn204NoContent() {
    String url = String.format(GET_ANALYSES_PATH, port);

    HttpHeaders headers = new HttpHeaders();
    headers.add(API_VERSION_HEADER_NAME, API_VERSION_HEADER_VALUE_V1);

    ResponseEntity<AnalysesResponses> response = testRestTemplate.exchange(
        url, HttpMethod.GET, new HttpEntity<>(headers), AnalysesResponses.class
    );

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  @DisplayName("""
      GIVEN an request to the getAnalyses endpoint
      AND the database has valid data
      WHEN the request is made
      AND is requested the first version of the API
      AND no pageable parameter is added
      THEN response is 200 ok
      AND should have a size of 3
      """)
  @Sql(scripts = "/mocked-db/sample-valid-analyses.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void whenRequestIsDoneSuccessfullyAndThereIsValidDataInDatabase_thenReturn200Ok() {
    String url = String.format(GET_ANALYSES_PATH, port);

    HttpHeaders headers = new HttpHeaders();
    headers.add(API_VERSION_HEADER_NAME, API_VERSION_HEADER_VALUE_V1);

    ResponseEntity<AnalysesResponses> response = testRestTemplate.exchange(
        url, HttpMethod.GET, new HttpEntity<>(headers), AnalysesResponses.class
    );

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(3, response.getBody().totalItems());
  }

  @Test
  @DisplayName("""
      GIVEN an request to the GetAnalyses endpoint
      AND the database has valid data
      WHEN the request is made
      AND is requested an wrong API version
      AND no pageable parameter is added
      THEN should throw a 400 error response
      """)
  void whenRequestIsDoneWithBadApiVersionHeader_thenReturn400Error() {
    String url = String.format(GET_ANALYSES_PATH, port);

    HttpHeaders headers = new HttpHeaders();
    headers.add(API_VERSION_HEADER_NAME, BAD_API_VERSION_VALUE);

    ResponseEntity<ErrorResponse> error = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, error.getStatusCode());
  }
}
