package es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Resource to check service health status. */
@RestController
@RequestMapping("/keepalive")
public class KeepAliveController {

  @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> keepAlive() {
    return ResponseEntity.ok(KeepAliveStatus.KEEPALIVE_OK.name());
  }

  enum KeepAliveStatus {
    KEEPALIVE_OK,
    UNAVAILABLE
  }
}
