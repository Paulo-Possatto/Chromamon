package com.monolithic.chromamon.analysis.domain.port;

import com.monolithic.chromamon.analysis.domain.model.Analysis;
import com.monolithic.chromamon.analysis.domain.model.AnalysisStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnalysisRepository {

   Analysis save(Analysis analysis);

   Optional<Analysis> findById(Long id);

   List<Analysis> findAll();

   List<Analysis> findByTransformerId(Long transformerId);

   List<Analysis> findByTransformerIdAndAnalysisDateBetween(
      Long transformerId, LocalDate startDate, LocalDate endDate);

   List<Analysis> findByAnalysisDateBetween(LocalDate startDate, LocalDate endDate);

   List<Analysis> findByStatus(AnalysisStatus status);

   List<Analysis> findByLaboratory(String laboratory);

   void deleteById(Long id);

   boolean existsById(Long id);

   long countByTransformerId(Long transformerId);

   Optional<Analysis> findLatestByTransformerId(Long transformerId);
}