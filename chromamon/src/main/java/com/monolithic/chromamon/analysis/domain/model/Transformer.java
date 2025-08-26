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
public class Transformer {

   private Long id;
   private String tag;
   private String serialNumber;
   private String manufacturer;
   private String model;
   private Integer yearManufacture;
   private BigDecimal nominalPowerKva;
   private BigDecimal primaryVoltageKv;
   private BigDecimal secondaryVoltageKv;
   private String connectionType;
   private String coolingType;
   private LocalDate installationDate;
   private String location;
   private String substation;
   private String bay;
   private String status;
   private BigDecimal oilVolumeLiters;
   private String description;
}