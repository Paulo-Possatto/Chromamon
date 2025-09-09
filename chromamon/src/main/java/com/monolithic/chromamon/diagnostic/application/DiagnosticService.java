package com.monolithic.chromamon.diagnostic.application;

import com.monolithic.chromamon.analysis.domain.model.Analysis;
import com.monolithic.chromamon.diagnostic.domain.model.DiagnosticMethod;
import com.monolithic.chromamon.diagnostic.domain.model.DiagnosticResult;
import com.monolithic.chromamon.shared.application.security.HasPermission;
import com.monolithic.chromamon.shared.domain.security.Permission;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class DiagnosticService {

  @HasPermission(Permission.DIAGNOSTIC_CREATE)
  public DiagnosticResult diagnose(DiagnosticMethod method, Analysis a) {
    String conclusion =
        switch (method) {
          case DUVAL -> "DUVAL: evaluate triangle with C2H4/C2H2/CH4";
          case IEEE -> "IEEE: evaluate CH4/H2, C2H6/CH4, C2H4/C2H6, C2H2/C2H4";
          case ROGERS -> "ROGERS: evaluate ratios R1..R4";
          case IEC -> "IEC 60599: evaluate key gas limits";
        };
    return DiagnosticResult.builder()
        .analysisId(a.getId())
        .method(method)
        .conclusion(conclusion)
        .generatedAt(Instant.now())
        .build();
  }
}
