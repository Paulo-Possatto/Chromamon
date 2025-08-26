package com.monolithic.chromamon.report.infrastructure.web;

import com.monolithic.chromamon.report.application.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

   private final ReportService service;

   @PostMapping("/{template}")
   public ResponseEntity<byte[]> generate(@PathVariable String template, @RequestBody Map<String, Object> params) {
      byte[] pdf = service.generate(template, params);
      return ResponseEntity.ok()
         .contentType(MediaType.APPLICATION_PDF)
         .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=%s.pdf".formatted(template))
         .body(pdf);
   }
}
