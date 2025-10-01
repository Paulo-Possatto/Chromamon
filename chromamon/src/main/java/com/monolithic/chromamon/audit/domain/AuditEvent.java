package com.monolithic.chromamon.audit.domain;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditEvent {
  private String id;
  private Instant at;
  private Long userId;
  private String action; // ex: "analysis.addAnalysis"
  private String details; // livre
}
