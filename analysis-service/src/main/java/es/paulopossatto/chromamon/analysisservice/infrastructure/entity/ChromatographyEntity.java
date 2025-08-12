package es.paulopossatto.chromamon.analysisservice.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** The entity ORM for the Chromatography data. */
@Entity
@Table(name = "chromatography")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChromatographyEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "chr_id")
  private Long chrId;

  @Column(name = "hydrogen", nullable = false, precision = 10, scale = 2)
  @NotNull(message = "Hydrogen value is obligatory")
  @DecimalMin(value = "0.0", message = "Hydrogen value must be greater or equal than 0")
  @Digits(
      integer = 8,
      fraction = 2,
      message = "Hydrogen value must have a maximum of 8 digits and 2 decimals")
  private BigDecimal hydrogen;

  @Column(name = "methane", nullable = false, precision = 10, scale = 2)
  @NotNull(message = "Methane value is obligatory")
  @DecimalMin(value = "0.0", message = "Methane value must be greater or equal than 0")
  @Digits(
      integer = 8,
      fraction = 2,
      message = "Methane value must have a maximum of 8 digits and 2 decimals")
  private BigDecimal methane;

  @Column(name = "ethane", nullable = false, precision = 10, scale = 2)
  @NotNull(message = "Ethane value is obligatory")
  @DecimalMin(value = "0.0", message = "Ethane value must be greater or equal than 0")
  @Digits(
      integer = 8,
      fraction = 2,
      message = "Ethane value must have a maximum of 8 digits and 2 decimals")
  private BigDecimal ethane;

  @Column(name = "acetylene", nullable = false, precision = 10, scale = 2)
  @NotNull(message = "Acetylene value is obligatory")
  @DecimalMin(value = "0.0", message = "Acetylene value must be greater or equal than 0")
  @Digits(
      integer = 8,
      fraction = 2,
      message = "Acetylene value must have a maximum of 8 digits and 2 decimals")
  private BigDecimal acetylene;

  @Column(name = "c_monoxide", nullable = false, precision = 10, scale = 2)
  @NotNull(message = "Carbon Monoxide value is obligatory")
  @DecimalMin(value = "0.0", message = "Carbon Monoxide value must be greater or equal than 0")
  @Digits(
      integer = 8,
      fraction = 2,
      message = "Carbon Monoxide value must have a maximum of 8 digits and 2 decimals")
  private BigDecimal carbonMonoxide;

  @Column(name = "c_dioxide", nullable = false, precision = 10, scale = 2)
  @NotNull(message = "Carbon Dioxide value is obligatory")
  @DecimalMin(value = "0.0", message = "Carbon Dioxide value must be greater or equal than 0")
  @Digits(
      integer = 8,
      fraction = 2,
      message = "Carbon Dioxide value must have a maximum of 8 digits and 2 decimals")
  private BigDecimal carbonDioxide;
}
