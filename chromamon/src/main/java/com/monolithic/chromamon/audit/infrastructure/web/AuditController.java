package com.monolithic.chromamon.audit.infrastructure.web;

import com.monolithic.chromamon.audit.infrastructure.persistence.AuditMongoRepository;
import com.monolithic.chromamon.shared.application.security.HasPermission;
import com.monolithic.chromamon.shared.domain.security.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/audit")
@RequiredArgsConstructor
public class AuditController {

  private final AuditMongoRepository repo;

  @HasPermission(Permission.AUDIT_READ)
  @GetMapping
  public Object list() {
    return repo.findAll();
  }
}
