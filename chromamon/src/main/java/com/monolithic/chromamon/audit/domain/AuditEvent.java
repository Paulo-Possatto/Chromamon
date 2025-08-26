package com.monolithic.chromamon.audit.domain;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditEvent {
   private String id;
   private Instant at;
   private Long userId;
   private String action;   // ex: "analysis.create"
   private String details;  // livre
}