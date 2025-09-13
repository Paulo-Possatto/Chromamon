package com.monolithic.chromamon.audit.infrastructure;

import com.monolithic.chromamon.analysis.domain.port.AuditService;
import com.monolithic.chromamon.audit.infrastructure.persistence.AuditDocument;
import com.monolithic.chromamon.audit.infrastructure.persistence.AuditMongoRepository;
import com.monolithic.chromamon.login.domain.port.AuditLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.time.Instant;

/**
 * Implementation class for the audit interfaces across the application.
 */
@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService, AuditLoginService {

  private final AuditMongoRepository repo;

   /**
    * Add the auditing information to the Mongo database.
    *
    * @param action the request action.
    * @param details added details about the request.
    */
  public void trace(String action, String details) {
    Long uid = 0L;
    try {
      Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      uid = jwt.getClaim("userId");
    } catch (Exception ignored) {
       throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, ignored.getMessage());
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

   @Override
   public void logUserCreation(String info) {
      trace("user.create", info);
   }

   @Override
   public void logGetAllUsers() {
      trace("user.get", "Requested all users information");
   }

   @Override
   public void logGetUserCodeById(String info) {
     trace("user.get", info);
   }

   @Override
   public void logUserUpdate(String info) {
     trace("user.update", info);
   }

   @Override
   public void logUserDelete(String info) {
     trace("user.delete", info);
   }
}
