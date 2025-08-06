package es.paulopossatto.chromamon.analysisservice.constants.contract;

import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.AnalysisEntity;
import java.util.Collections;
import java.util.List;

/** The responses for mocked calls to the database repository. */
public class AnalysesResponseContract {

  /**
   * Generates an ok entity for mocking to test the pact.
   *
   * @return the entity object.
   */
  public static List<AnalysisEntity> analysesResponse200Ok() {
    return List.of(new AnalysisEntity());
  }

  /**
   * Generates an empty list to test the client result.
   *
   * @return an empty list
   */
  public static List<AnalysisEntity> analysisResponse204NoContent() {
    return Collections.emptyList();
  }

  /**
   * Generates an ok list of analyses entities for mocking to test the pact.
   *
   * @return the entity object list.
   */
  public static List<AnalysisEntity> analysesResponse200WithMoreElementsOk() {
    return List.of(new AnalysisEntity());
  }
}
