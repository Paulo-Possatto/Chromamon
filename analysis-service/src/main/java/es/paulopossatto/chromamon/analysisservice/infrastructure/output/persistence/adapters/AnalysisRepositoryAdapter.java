package es.paulopossatto.chromamon.analysisservice.infrastructure.output.persistence.adapters;

import es.paulopossatto.chromamon.analysisservice.domain.model.Analysis;
import es.paulopossatto.chromamon.analysisservice.domain.ports.outbound.AnalysisRepositoryPort;
import es.paulopossatto.chromamon.analysisservice.infrastructure.mapper.AnalysisEntityMapper;
import es.paulopossatto.chromamon.analysisservice.infrastructure.output.persistence.repositories.AnalysisJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/** The adapter for the Analysis repository port. */
@RequiredArgsConstructor
@Repository
public class AnalysisRepositoryAdapter implements AnalysisRepositoryPort {

  private final AnalysisJpaRepository analysisJpaRepository;
  private final AnalysisEntityMapper mapper;

  @Override
  public Page<Analysis> getAllAnalyses(Pageable pageable) {
    return analysisJpaRepository.findAll(pageable).map(mapper::toDomain);
  }
}
