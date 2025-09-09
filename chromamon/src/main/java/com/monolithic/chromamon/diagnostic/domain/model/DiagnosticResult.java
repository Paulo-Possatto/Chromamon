package com.monolithic.chromamon.diagnostic.domain.model;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiagnosticResult {
  private Long analysisId;
  private DiagnosticMethod method;
  private String conclusion;
  private Instant generatedAt;
}
