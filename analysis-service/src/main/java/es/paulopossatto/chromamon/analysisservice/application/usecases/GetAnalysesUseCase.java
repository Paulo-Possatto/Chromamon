package es.paulopossatto.chromamon.analysisservice.application.usecases;

import es.paulopossatto.chromamon.analysisservice.application.converter.AnalysisConverter;
import es.paulopossatto.chromamon.analysisservice.application.dto.response.AnalysesResponses;
import es.paulopossatto.chromamon.analysisservice.domain.model.Analysis;
import es.paulopossatto.chromamon.analysisservice.domain.ports.outbound.AnalysisRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/** The use case for the get analyses. */
@Service
@RequiredArgsConstructor
public class GetAnalysesUseCase {

  private final AnalysisRepositoryPort analysisRepositoryPort;
  private final AnalysisConverter converter;

  public AnalysesResponses getAnalysesResponses(Pageable pageable) {
    Page<Analysis> analysesPage = analysisRepositoryPort.getAllAnalyses(pageable);
    return converter.toAnalysesResponse(analysesPage);
  }
}
