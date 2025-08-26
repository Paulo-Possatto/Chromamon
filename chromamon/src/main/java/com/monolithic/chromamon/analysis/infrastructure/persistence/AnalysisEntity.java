package com.monolithic.chromamon.analysis.infrastructure.persistence;

import com.monolithic.chromamon.analysis.domain.model.AnalysisStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "analyses", schema = "analyses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "transformer_id", nullable = false)
   private Long transformerId;

   @Column(name = "analysis_date", nullable = false)
   private LocalDate analysisDate;

   @Column(name = "sample_date", nullable = false)
   private LocalDate sampleDate;

   @Column(name = "laboratory", nullable = false, length = 100)
   private String laboratory;

   @Column(name = "method", nullable = false, length = 50)
   private String method;

   @Column(name = "sample_temperature_celsius", precision = 5, scale = 2)
   private BigDecimal sampleTemperatureCelsius;

   // gases (ppm)
   @Column(name = "hydrogen_h2_ppm", precision = 10, scale = 3)
   private BigDecimal hydrogenH2Ppm;
   @Column(name = "methane_ch4_ppm", precision = 10, scale = 3)
   private BigDecimal methaneCh4Ppm;
   @Column(name = "acetylene_c2h2_ppm", precision = 10, scale = 3)
   private BigDecimal acetyleneC2h2Ppm;
   @Column(name = "ethylene_c2h4_ppm", precision = 10, scale = 3)
   private BigDecimal ethyleneC2h4Ppm;
   @Column(name = "ethane_c2h6_ppm", precision = 10, scale = 3)
   private BigDecimal ethaneC2h6Ppm;
   @Column(name = "carbon_monoxide_co_ppm", precision = 10, scale = 3)
   private BigDecimal carbonMonoxideCoPpm;
   @Column(name = "carbon_dioxide_co2_ppm", precision = 10, scale = 3)
   private BigDecimal carbonDioxideCo2Ppm;
   @Column(name = "oxygen_o2_ppm", precision = 10, scale = 3)
   private BigDecimal oxygenO2Ppm;
   @Column(name = "nitrogen_n2_ppm", precision = 10, scale = 3)
   private BigDecimal nitrogenN2Ppm;

   // totals
   @Column(name = "total_dissolved_gas_tdg_ppm", precision = 10, scale = 3)
   private BigDecimal totalDissolvedGasTdgPpm;
   @Column(name = "total_combustible_gas_tcg_ppm", precision = 10, scale = 3)
   private BigDecimal totalCombustibleGasTcgPpm;

   @Column(name = "observations", columnDefinition = "TEXT")
   private String observations;

   @Column(name = "sample_condition", length = 50)
   private String sampleCondition;

   @Enumerated(EnumType.STRING)
   @Column(name = "status", nullable = false, length = 20)
   private AnalysisStatus status;

   @CreationTimestamp
   @Column(name = "created_at", nullable = false, updatable = false)
   private LocalDateTime createdAt;

   @UpdateTimestamp
   @Column(name = "updated_at")
   private LocalDateTime updatedAt;

   @Column(name = "created_by", nullable = false)
   private Long createdBy;

   @Column(name = "updated_by")
   private Long updatedBy;
}
