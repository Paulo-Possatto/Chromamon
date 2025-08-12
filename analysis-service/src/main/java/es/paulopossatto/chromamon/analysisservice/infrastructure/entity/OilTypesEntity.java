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
import lombok.ToString;

/** The ORM for the oil types data. */
@Entity
@Table(name = "oil_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OilTypesEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "oil_type_id")
  private Long oilTypeId;

  @Column(name = "oil_type_typo", nullable = false, length = 40)
  @NotBlank(message = "Oil Type Typo is obligatory")
  @Size(max = 40, message = "Oil Type Typo must contain a maximum of 40 characters")
  private String oilTypeTypo;

  @Column(name = "oil_type_literal", nullable = false, length = 40)
  @NotBlank(message = "Oil Type Literal is obligatory")
  @Size(max = 40, message = "Oil Type Literal must contain a maximum of 40 characters")
  private String oilTypeLiteral;
}
