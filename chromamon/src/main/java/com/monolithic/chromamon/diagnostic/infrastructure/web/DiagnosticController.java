package com.monolithic.chromamon.diagnostic.infrastructure.web;

import com.monolithic.chromamon.analysis.domain.port.AnalysisRepository;
import com.monolithic.chromamon.diagnostic.application.DiagnosticService;
import com.monolithic.chromamon.diagnostic.domain.model.DiagnosticMethod;
import com.monolithic.chromamon.diagnostic.domain.model.DiagnosticResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/diagnostics")
@RequiredArgsConstructor
public class DiagnosticController {

   private final DiagnosticService service;
   private final AnalysisRepository analysisRepository;

   @PostMapping("/{analysisId}/{method}")
   public DiagnosticResult run(@PathVariable Long analysisId, @PathVariable DiagnosticMethod method) {
      var analysis = analysisRepository.findById(analysisId).orElseThrow();
      return service.diagnose(method, analysis);
   }
}
