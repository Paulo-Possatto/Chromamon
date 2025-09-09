package com.monolithic.chromamon.analysis.infrastructure.persistence;

import com.monolithic.chromamon.analysis.domain.model.AnalysisStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisJpaRepository extends JpaRepository<AnalysisEntity, Long> {

  Page<AnalysisEntity> findAllBy(Pageable pageable);

  List<AnalysisEntity> findByTransformerId(Long transformerId);

  Page<AnalysisEntity> findByTransformerId(Long transformerId, Pageable pageable);

  List<AnalysisEntity> findByAnalysisDateBetween(LocalDate start, LocalDate end);

  List<AnalysisEntity> findByStatus(AnalysisStatus status);

  List<AnalysisEntity> findByLaboratory(String laboratory);

  long countByTransformerId(Long transformerId);

  Optional<AnalysisEntity> findTop1ByTransformerIdOrderByAnalysisDateDescIdDesc(Long transformerId);
}
