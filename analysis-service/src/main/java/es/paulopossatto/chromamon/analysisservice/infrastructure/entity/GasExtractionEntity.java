package es.paulopossatto.chromamon.analysisservice.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The ORM for the gas extraction methods data. */
@Entity
@Table(name = "gas_extraction_methods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GasExtractionEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "g_ext_met_id")
  private Long gasExtMethodId;

  @Column(name = "g_ext_met_typo", nullable = false, length = 40)
  @NotBlank(message = "Gas Extraction Typo value is obligatory")
  @Size(max = 40, message = "Gas Extraction Typo value must have a maximum of 40 characters")
  private String gasExtMethodTypo;

  @Column(name = "g_ext_met_literal", nullable = false, length = 40)
  @NotBlank(message = "Gas Extraction Literal value is obligatory")
  @Size(max = 40, message = "Gas Extraction Literal value must have a maximum of 40 characters")
  private String gasExtMethodLiteral;
}
