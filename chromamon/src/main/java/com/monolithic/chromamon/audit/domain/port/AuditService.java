package com.monolithic.chromamon.audit.domain.port;

public interface AuditService {
   void trace(String action, String details);
}