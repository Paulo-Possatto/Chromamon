package com.monolithic.chromamon.shared.infrastructure.web;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
@RequiredArgsConstructor
public class HealthController implements HealthIndicator {

  @Override
  public Health health() {
    return Health.up()
        .withDetail("timestamp", LocalDateTime.now())
        .withDetail("service", "Chromamon")
        .withDetail("version", "0.0.1-SNAPSHOT")
        .build();
  }

  @GetMapping("/status")
  public ResponseEntity<Map<String, Object>> getHealthStatus() {
    Map<String, Object> status = new HashMap<>();
    status.put("status", "UP");
    status.put("timestamp", LocalDateTime.now());
    status.put("service", "Chromamon");
    status.put("version", "0.0.1-SNAPSHOT");

    return ResponseEntity.ok(status);
  }
}
