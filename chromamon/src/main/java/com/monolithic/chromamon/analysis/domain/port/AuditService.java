package com.monolithic.chromamon.analysis.domain.port;

public interface AuditService {
   void logAnalysisCreated(String info);

   void logAnalysisUpdated(String info);

   void logAnalysisDeleted(String info);
}