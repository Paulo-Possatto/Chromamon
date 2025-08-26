package com.monolithic.chromamon.transformer.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transformers", schema = "transformers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransformerEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false, unique = true, length = 50)
   private String tag;

   @Column(name = "serial_number", nullable = false, unique = true, length = 100)
   private String serialNumber;

   @Column(nullable = false, length = 100)
   private String manufacturer;

   @Column(length = 100)
   private String model;

   @Column(name = "year_manufacture")
   private Integer yearManufacture;

   @Column(name = "nominal_power_kva", nullable = false, precision = 10, scale = 2)
   private BigDecimal nominalPowerKva;

   @Column(name = "primary_voltage_kv", nullable = false, precision = 8, scale = 2)
   private BigDecimal primaryVoltageKv;

   @Column(name = "secondary_voltage_kv", nullable = false, precision = 8, scale = 2)
   private BigDecimal secondaryVoltageKv;

   @Column(name = "connection_type", nullable = false, length = 20)
   private String connectionType;

   @Column(name = "cooling_type", nullable = false, length = 20)
   private String coolingType;

   @Column(name = "installation_date")
   private LocalDate installationDate;

   @Column(nullable = false, length = 255)
   private String location;

   // Campos possíveis (conforme índices vistos no Flyway)
   @Column(length = 100)
   private String substation;

   @Column(length = 20)
   private String status;

   @Column(name = "created_by")
   private Long createdBy;

   @Column(name = "updated_by")
   private Long updatedBy;
}
