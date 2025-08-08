package es.paulopossatto.chromamon.analysisservice.domain.ports.outbound;

import es.paulopossatto.chromamon.analysisservice.domain.model.Analysis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** The outbound port for the Analysis. */
public interface AnalysisRepositoryPort {

  /**
   * Returns pages containing all the analyses.
   *
   * @param pageable pagination parameters.
   * @return the analyses within the pages.
   */
  Page<Analysis> getAllAnalyses(Pageable pageable);
}
