package es.paulopossatto.chromamon.analysisservice.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

/** The ORM for the analyses data. */
@Entity
@Table(
    name = "analyses",
    indexes = {
      @Index(name = "idx_analyses_trans_ser_num", columnList = "trans_ser_num"),
      @Index(name = "idx_analyses_an_dt", columnList = "an_dt"),
      @Index(name = "idx_analyses_identifier", columnList = "identifier")
    })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "an_id")
  private Long anId;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @Column(name = "identifier", nullable = false, unique = true, length = 14)
  @ColumnDefault("generate_unique_identifier()")
  private String identifier;

  @Column(name = "trans_ser_num", nullable = false, length = 40)
  @NotBlank(message = "Transformer Serial Number is obligatory")
  @Size(max = 40, message = "Transformer Serial Number must have a maximum of 40 characters")
  private String transSerNum;

  @Column(name = "an_dt", nullable = false)
  @NotNull(message = "Analysis Date is obligatory")
  private OffsetDateTime analysisDateTime;

  @Column(name = "lab_an_dt")
  @NotNull(message = "Laboratory Analysis Date is obligatory")
  private OffsetDateTime labAnalysisDateTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "chroma", referencedColumnName = "chr_id")
  private ChromatographyEntity chromatography;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "oil_type", referencedColumnName = "oil_type_id")
  private OilTypesEntity oilType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "obs", referencedColumnName = "obs_id")
  private ObservationEntity observation;

  @Column(name = "furfural_an", nullable = false, precision = 10, scale = 2)
  @NotNull(message = "Furfural Analysis is obligatory")
  @DecimalMin(value = "0.0", message = "Furfural Analysis must be greater or equal than 0")
  @Digits(
      integer = 8,
      fraction = 2,
      message = "Furfural Analysis must have a maximum of 8 digits and 2 decimals")
  private BigDecimal furfuralAnalysis;

  @Column(name = "oil_hum", nullable = false, precision = 10, scale = 2)
  @NotNull(message = "OIL Humidity is obligatory")
  @DecimalMin(value = "0.0", message = "Oil humidity must be greater or equal than 0")
  @DecimalMax(value = "100.0", message = "Oil humidity must be lower or equal than 100")
  @Digits(
      integer = 8,
      fraction = 2,
      message = "Oil Humidity must have a maximum of 8 digits and 2 decimals")
  private BigDecimal oilHumidity;
}
