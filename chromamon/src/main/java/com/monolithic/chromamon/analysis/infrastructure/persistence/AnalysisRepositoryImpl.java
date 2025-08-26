package com.monolithic.chromamon.analysis.infrastructure.persistence;

import com.monolithic.chromamon.analysis.domain.model.Analysis;
import com.monolithic.chromamon.analysis.domain.model.AnalysisStatus;
import com.monolithic.chromamon.analysis.domain.port.AnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AnalysisRepositoryImpl implements AnalysisRepository {

   private final AnalysisJpaRepository jpa;

   private static AnalysisEntity toEntity(Analysis a) {
      return AnalysisEntity.builder()
         .id(a.getId())
         .transformerId(a.getTransformerId())
         .analysisDate(a.getAnalysisDate())
         .sampleDate(a.getSampleDate())
         .laboratory(a.getLaboratory())
         .method(a.getMethod())
         .sampleTemperatureCelsius(a.getSampleTemperatureCelsius())
         .hydrogenH2Ppm(a.getHydrogenH2Ppm())
         .methaneCh4Ppm(a.getMethaneCh4Ppm())
         .acetyleneC2h2Ppm(a.getAcetyleneC2h2Ppm())
         .ethyleneC2h4Ppm(a.getEthyleneC2h4Ppm())
         .ethaneC2h6Ppm(a.getEthaneC2h6Ppm())
         .carbonMonoxideCoPpm(a.getCarbonMonoxideCoPpm())
         .carbonDioxideCo2Ppm(a.getCarbonDioxideCo2Ppm())
         .oxygenO2Ppm(a.getOxygenO2Ppm())
         .nitrogenN2Ppm(a.getNitrogenN2Ppm())
         .totalDissolvedGasTdgPpm(a.getTotalDissolvedGasTdgPpm())
         .totalCombustibleGasTcgPpm(a.getTotalCombustibleGasTcgPpm())
         .observations(a.getObservations())
         .sampleCondition(a.getSampleCondition())
         .status(a.getStatus())
         .createdBy(a.getCreatedBy())
         .updatedBy(a.getUpdatedBy())
         .build();
   }

   private static Analysis toDomain(AnalysisEntity e) {
      return Analysis.builder()
         .id(e.getId())
         .transformerId(e.getTransformerId())
         .analysisDate(e.getAnalysisDate())
         .sampleDate(e.getSampleDate())
         .laboratory(e.getLaboratory())
         .method(e.getMethod())
         .sampleTemperatureCelsius(e.getSampleTemperatureCelsius())
         .hydrogenH2Ppm(e.getHydrogenH2Ppm())
         .methaneCh4Ppm(e.getMethaneCh4Ppm())
         .acetyleneC2h2Ppm(e.getAcetyleneC2h2Ppm())
         .ethyleneC2h4Ppm(e.getEthyleneC2h4Ppm())
         .ethaneC2h6Ppm(e.getEthaneC2h6Ppm())
         .carbonMonoxideCoPpm(e.getCarbonMonoxideCoPpm())
         .carbonDioxideCo2Ppm(e.getCarbonDioxideCo2Ppm())
         .oxygenO2Ppm(e.getOxygenO2Ppm())
         .nitrogenN2Ppm(e.getNitrogenN2Ppm())
         .totalDissolvedGasTdgPpm(e.getTotalDissolvedGasTdgPpm())
         .totalCombustibleGasTcgPpm(e.getTotalCombustibleGasTcgPpm())
         .observations(e.getObservations())
         .sampleCondition(e.getSampleCondition())
         .status(e.getStatus())
         .createdBy(e.getCreatedBy())
         .updatedBy(e.getUpdatedBy())
         .build();
   }

   @Override
   public Analysis save(Analysis analysis) {
      return toDomain(jpa.save(toEntity(analysis)));
   }

   @Override
   public Optional<Analysis> findById(Long id) {
      return jpa.findById(id).map(AnalysisRepositoryImpl::toDomain);
   }

   @Override
   public List<Analysis> findAll() {
      return jpa.findAll().stream().map(AnalysisRepositoryImpl::toDomain).toList();
   }
   @Override
   public List<Analysis> findByTransformerId(Long t) {
      return jpa.findByTransformerId(t).stream().map(AnalysisRepositoryImpl::toDomain).toList();
   }

   @Override
   public List<Analysis> findByTransformerIdAndAnalysisDateBetween(Long transformerId, LocalDate startDate, LocalDate endDate) {
      return List.of();
   }

   @Override
   public List<Analysis> findByAnalysisDateBetween(LocalDate s, LocalDate e) {
      return jpa.findByAnalysisDateBetween(s, e).stream().map(AnalysisRepositoryImpl::toDomain).toList();
   }

   @Override
   public List<Analysis> findByStatus(AnalysisStatus st) {
      return jpa.findByStatus(st).stream().map(AnalysisRepositoryImpl::toDomain).toList();
   }

   @Override
   public List<Analysis> findByLaboratory(String lab) {
      return jpa.findByLaboratory(lab).stream().map(AnalysisRepositoryImpl::toDomain).toList();
   }

   @Override
   public void deleteById(Long id) {
      jpa.deleteById(id);
   }

   @Override
   public boolean existsById(Long id) {
      return jpa.existsById(id);
   }

   @Override
   public long countByTransformerId(Long transformerId) {
      return jpa.countByTransformerId(transformerId);
   }

   @Override
   public Optional<Analysis> findLatestByTransformerId(Long transformerId) {
      return jpa.findTop1ByTransformerIdOrderByAnalysisDateDescIdDesc(transformerId).map(AnalysisRepositoryImpl::toDomain);
   }
}
