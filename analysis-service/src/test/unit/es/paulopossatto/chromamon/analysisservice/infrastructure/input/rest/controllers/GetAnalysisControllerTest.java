package es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers;

import es.paulopossatto.chromamon.analysisservice.application.dto.response.AnalysesResponses;
import es.paulopossatto.chromamon.analysisservice.application.usecases.GetAnalysesUseCase;
import es.paulopossatto.chromamon.analysisservice.constants.AnalysesMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Get Analysis controller.
 */
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class GetAnalysisControllerTest {

  @InjectMocks
  GetAnalysisController controller;

  @Mock
  GetAnalysesUseCase useCase;

  @Mock
  Pageable pageable;

  @Test
  @DisplayName("""
      GIVEN an call to the getAnalyses method
      WHEN the token is valid
      AND the no exception is thrown for the roles
      AND there's no results in the database
      THEN return a 204 response code
      """)
  void testController() {
    // ARRANGE
    Mockito.when(useCase.getAnalysesResponses(pageable)).thenReturn(
        AnalysesMother.emptyListResponse()
    );

    // ACT
    ResponseEntity<AnalysesResponses> response = controller.getAnalyses(pageable);

    // ASSERT
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
