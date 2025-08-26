package com.monolithic.chromamon.analysis.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateAnalysisRequest {
   private Long transformerId;
   private LocalDate analysisDate;
   private LocalDate sampleDate;
   private String laboratory;
   private String method;
   private BigDecimal sampleTemperatureCelsius;

   private BigDecimal hydrogenH2Ppm;
   private BigDecimal methaneCh4Ppm;
   private BigDecimal acetyleneC2h2Ppm;
   private BigDecimal ethyleneC2h4Ppm;
   private BigDecimal ethaneC2h6Ppm;
   private BigDecimal carbonMonoxideCoPpm;
   private BigDecimal carbonDioxideCo2Ppm;
   private BigDecimal oxygenO2Ppm;
   private BigDecimal nitrogenN2Ppm;

   private BigDecimal totalDissolvedGasTdgPpm;
   private BigDecimal totalCombustibleGasTcgPpm;

   private String observations;
   private String sampleCondition;

   public Analysis toAnalysis() {
      return Analysis.builder()
         .transformerId(transformerId)
         .analysisDate(analysisDate)
         .sampleDate(sampleDate)
         .laboratory(laboratory)
         .method(method)
         .sampleTemperatureCelsius(sampleTemperatureCelsius)
         .hydrogenH2Ppm(hydrogenH2Ppm)
         .methaneCh4Ppm(methaneCh4Ppm)
         .acetyleneC2h2Ppm(acetyleneC2h2Ppm)
         .ethyleneC2h4Ppm(ethyleneC2h4Ppm)
         .ethaneC2h6Ppm(ethaneC2h6Ppm)
         .carbonMonoxideCoPpm(carbonMonoxideCoPpm)
         .carbonDioxideCo2Ppm(carbonDioxideCo2Ppm)
         .oxygenO2Ppm(oxygenO2Ppm)
         .nitrogenN2Ppm(nitrogenN2Ppm)
         .totalDissolvedGasTdgPpm(totalDissolvedGasTdgPpm)
         .totalCombustibleGasTcgPpm(totalCombustibleGasTcgPpm)
         .observations(observations)
         .sampleCondition(sampleCondition)
         .status(AnalysisStatus.PENDING)
         .build();
   }
}
