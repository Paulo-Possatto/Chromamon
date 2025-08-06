package es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers;

import es.paulopossatto.chromamon.analysisservice.application.dto.response.AnalysesResponses;
import es.paulopossatto.chromamon.analysisservice.application.usecases.GetAnalysesUseCase;
import es.paulopossatto.chromamon.analysisservice.constants.unit.AnalysesMother;
import es.paulopossatto.chromamon.analysisservice.infrastructure.security.JwtUtils;
import org.junit.jupiter.api.DisplayName;
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
import static org.mockito.ArgumentMatchers.eq;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class GetAnalysisControllerTest {

  private static final String TOKEN = "token";
  private static final String ROLE_ADMIN = "ADMIN";
  private static final String ROLE_USER = "USER";
  private static final String ROLE_ENGINEER = "ENGINEER";
  private static final String ROLE_CHEMIST = "CHEMIST";
  private static final String ROLE_LAB_ANALYST = "LAB_ANALYST";

  @InjectMocks
  GetAnalysisController controller;

  @Mock
  JwtUtils jwtUtils;

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
    Mockito.doNothing().when(jwtUtils).checkJwtAndPermissions(
        eq(TOKEN),
        eq(ROLE_ADMIN),
        eq(ROLE_USER),
        eq(ROLE_ENGINEER),
        eq(ROLE_CHEMIST),
        eq(ROLE_LAB_ANALYST)
    );
    Mockito.when(useCase.getAnalysesResponses(pageable)).thenReturn(
        AnalysesMother.emptyListResponse()
    );

    // ACT
    ResponseEntity<AnalysesResponses> response = controller.getAnalyses(TOKEN, pageable);

    // ASSERT
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
