package com.monolithic.chromamon.report.infrastructure.web;

import com.monolithic.chromamon.report.application.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
