package es.paulopossatto.chromamon.analysisservice.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/** The ORM for only common data in other entities. */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

  @Column(name = "dt_created", nullable = false, updatable = false)
  @CreationTimestamp
  private OffsetDateTime dtCreated;

  @Column(name = "dt_modified", nullable = false)
  @UpdateTimestamp
  private OffsetDateTime dtModified;

  @Column(name = "user_created", nullable = false, length = 6)
  @CreatedBy
  private String userCreated;

  @Column(name = "user_updated", nullable = false, length = 6)
  @LastModifiedBy
  private String userUpdated;
}
