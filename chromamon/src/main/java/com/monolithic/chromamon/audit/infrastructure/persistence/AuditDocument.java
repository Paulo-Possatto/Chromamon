package com.monolithic.chromamon.audit.infrastructure.persistence;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("audit_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditDocument {
  @Id private String id;
  private Instant at;
  private Long userId;
  private String action;
  private String details;
}
