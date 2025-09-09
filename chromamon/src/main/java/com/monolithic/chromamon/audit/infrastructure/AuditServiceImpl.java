package com.monolithic.chromamon.audit.infrastructure;

import com.monolithic.chromamon.analysis.domain.port.AuditService;
import com.monolithic.chromamon.audit.infrastructure.persistence.AuditDocument;
import com.monolithic.chromamon.audit.infrastructure.persistence.AuditMongoRepository;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

  private final AuditMongoRepository repo;

  public void trace(String action, String details) {
    Long uid = 0L;
    try {
      Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      uid = jwt.getClaim("userId");
    } catch (Exception ignored) {
    }
    repo.save(
        AuditDocument.builder()
            .at(Instant.now())
            .userId(uid)
            .action(action)
            .details(details)
            .build());
  }

  @Override
  public void logAnalysisCreated(String info) {
    trace("analysis.create", info);
  }

  @Override
  public void logAnalysisUpdated(String info) {
    trace("analysis.update", info);
  }

  @Override
  public void logAnalysisDeleted(String info) {
    trace("analysis.delete", info);
  }
}
