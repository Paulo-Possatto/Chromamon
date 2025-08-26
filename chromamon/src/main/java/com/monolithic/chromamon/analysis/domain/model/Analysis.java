package com.monolithic.chromamon.analysis.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Analysis {

   private Long id;
   private Long transformerId;
   private LocalDate analysisDate;
   private LocalDate sampleDate;
   private String laboratory;
   private String method;
   private BigDecimal sampleTemperatureCelsius;

   // gases (ppm)
   private BigDecimal hydrogenH2Ppm;
   private BigDecimal methaneCh4Ppm;
   private BigDecimal acetyleneC2h2Ppm;
   private BigDecimal ethyleneC2h4Ppm;
   private BigDecimal ethaneC2h6Ppm;
   private BigDecimal carbonMonoxideCoPpm;
   private BigDecimal carbonDioxideCo2Ppm;
   private BigDecimal oxygenO2Ppm;
   private BigDecimal nitrogenN2Ppm;

   // totals
   private BigDecimal totalDissolvedGasTdgPpm;
   private BigDecimal totalCombustibleGasTcgPpm;

   private String observations;
   private String sampleCondition;

   private AnalysisStatus status;
   private Long createdBy;
   private Long updatedBy;

   public boolean isValidForCreate() {
      return transformerId != null &&
         analysisDate != null &&
         sampleDate != null &&
         laboratory != null &&
         !laboratory.trim().isEmpty() &&
         hasAtLeastOneGasValue();
   }

   private boolean hasAtLeastOneGasValue() {
      return hydrogenH2Ppm != null || methaneCh4Ppm != null ||
         acetyleneC2h2Ppm != null || ethyleneC2h4Ppm != null ||
         ethaneC2h6Ppm != null || carbonMonoxideCoPpm != null ||
         carbonDioxideCo2Ppm != null || oxygenO2Ppm != null || nitrogenN2Ppm != null;
   }
}
