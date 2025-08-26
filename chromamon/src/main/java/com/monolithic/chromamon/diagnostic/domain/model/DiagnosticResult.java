package com.monolithic.chromamon.diagnostic.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class DiagnosticResult {
   private Long analysisId;
   private DiagnosticMethod method;
   private String conclusion;
   private Instant generatedAt;
}
