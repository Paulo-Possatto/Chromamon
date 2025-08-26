package com.monolithic.chromamon.shared.domain.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Roles and permissions used throughout the application.
 */
@RequiredArgsConstructor
@Getter
public enum Permission {
   // Analysis permissions
   ANALYSIS_READ("analysis:read"),
   ANALYSIS_CREATE("analysis:create"),
   ANALYSIS_UPDATE("analysis:update"),
   ANALYSIS_DELETE("analysis:delete"),

   // Diagnostic permissions
   DIAGNOSTIC_READ("diagnostic:read"),
   DIAGNOSTIC_CREATE("diagnostic:create"),
   DIAGNOSTIC_UPDATE("diagnostic:update"),
   DIAGNOSTIC_DELETE("diagnostic:delete"),

   // Transformer permissions
   TRANSFORMER_READ("transformer:read"),
   TRANSFORMER_CREATE("transformer:create"),
   TRANSFORMER_UPDATE("transformer:update"),
   TRANSFORMER_DELETE("transformer:delete"),

   // Report permissions
   REPORT_READ("report:read"),
   REPORT_CREATE("report:create"),
   REPORT_UPDATE("report:update"),
   REPORT_DELETE("report:delete"),

   // User management permissions
   USER_READ("user:read"),
   USER_CREATE("user:create"),
   USER_UPDATE("user:update"),
   USER_DELETE("user:delete"),

   // Audit permissions
   AUDIT_READ("audit:read"),
   AUDIT_DELETE("audit:delete");

   private final String permission;
}
