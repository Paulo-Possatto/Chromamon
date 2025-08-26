package com.monolithic.chromamon.analysis.infrastructure.web;

import com.monolithic.chromamon.analysis.application.service.AnalysisService;
import com.monolithic.chromamon.analysis.domain.model.Analysis;
import com.monolithic.chromamon.analysis.domain.model.AnalysisStatus;
import com.monolithic.chromamon.analysis.domain.model.CreateAnalysisRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/analyses")
@RequiredArgsConstructor
@Tag(name = "Analyses")
@SecurityRequirement(name = "bearerAuth")
public class AnalysisController {

   private final AnalysisService service;

   @Operation(summary = "Create analysis")
   @PostMapping
   public ResponseEntity<Analysis> create(@Valid @RequestBody CreateAnalysisRequest req) {
      return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
   }

   @Operation(summary = "List analyses (paged)")
   @GetMapping
   public Page<Analysis> list(Pageable pageable) {
      return service.list(pageable);
   }

   @Operation(summary = "Get by id")
   @GetMapping("/{id}")
   public Analysis get(@PathVariable Long id) {
      return service.get(id);
   }

   @Operation(summary = "Update analysis")
   @PutMapping("/{id}")
   public Analysis update(@PathVariable Long id, @RequestBody Analysis patch) {
      return service.update(id, patch);
   }

   @Operation(summary = "Delete analysis")
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {
      service.delete(id);
      return ResponseEntity.noContent().build();
   }

   @Operation(summary = "Find by transformer")
   @GetMapping("/transformer/{transformerId}")
   public List<Analysis> byTransformer(@PathVariable Long transformerId) {
      return service.findByTransformer(transformerId);
   }

   @Operation(summary = "Find by date range")
   @GetMapping("/date")
   public List<Analysis> byDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
      return service.findByDate(start, end);
   }

   @Operation(summary = "Find by status")
   @GetMapping("/status/{status}")
   public List<Analysis> byStatus(@PathVariable AnalysisStatus status) {
      return service.findByStatus(status);
   }

   @Operation(summary = "Find by laboratory")
   @GetMapping("/laboratory/{lab}")
   public List<Analysis> byLab(@PathVariable String lab) {
      return service.findByLaboratory(lab);
   }

   @Operation(summary = "Count by transformer")
   @GetMapping("/transformer/{transformerId}/count")
   public long countByTransformer(@PathVariable Long transformerId) {
      return service.getAnalysisCountByTransformerId(transformerId);
   }

   @Operation(summary = "Latest analysis by transformer")
   @GetMapping("/transformer/{transformerId}/latest")
   public Analysis latestByTransformer(@PathVariable Long transformerId) {
      return service.getLatestAnalysisByTransformerId(transformerId);
   }
}
