package es.paulopossatto.chromamon.analysisservice.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The ORM for the Observation data. */
@Entity
@Table(name = "observation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObservationEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "obs_id")
  private Long obsId;

  @Column(name = "sample_cond", nullable = false, columnDefinition = "TEXT")
  @NotBlank(message = "Sample Condition is obligatory")
  private String sampleCond;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "g_ext_method", referencedColumnName = "g_ext_met_id")
  private GasExtractionEntity gasExtMethod;

  @Column(name = "mod_used", nullable = false, length = 40)
  @NotBlank(message = "Model Used is obligatory")
  @Size(max = 40, message = "Model Used must have a maximum of 40 characters")
  private String modUsed;
}
