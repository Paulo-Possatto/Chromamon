package com.monolithic.chromamon.analysis.infrastructure.web;

import com.monolithic.chromamon.analysis.application.service.AnalysisService;
import com.monolithic.chromamon.analysis.domain.model.Analysis;
import com.monolithic.chromamon.analysis.domain.model.request.CreateAnalysisRequest;
import com.monolithic.chromamon.shared.domain.security.SwaggerConstants;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Controller for adding new analysis information. */
@RestController
@RequestMapping("/api/v1/analyses")
@RequiredArgsConstructor
public class AddAnalysisController {

  private final AnalysisService service;

  @Operation(
      method = SwaggerConstants.METHOD_POST,
      tags = {SwaggerConstants.TAG_ANALYSIS},
      summary = "Add Analysis",
      description = "Adds a new single analysis to the database")
  @PostMapping
  public ResponseEntity<Analysis> addAnalysis(@Valid @RequestBody CreateAnalysisRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
  }
}
