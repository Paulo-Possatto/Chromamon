package com.monolithic.chromamon.shared.domain.security;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Possible roles used throughout the application. */
@RequiredArgsConstructor
@Getter
public enum Role {

  /** The user who can do anything and everything. */
  ADMIN(
      Set.of(
          Permission.ANALYSIS_READ,
          Permission.ANALYSIS_CREATE,
          Permission.ANALYSIS_UPDATE,
          Permission.ANALYSIS_DELETE,
          Permission.DIAGNOSTIC_READ,
          Permission.DIAGNOSTIC_CREATE,
          Permission.DIAGNOSTIC_UPDATE,
          Permission.DIAGNOSTIC_DELETE,
          Permission.TRANSFORMER_READ,
          Permission.TRANSFORMER_CREATE,
          Permission.TRANSFORMER_UPDATE,
          Permission.TRANSFORMER_DELETE,
          Permission.REPORT_READ,
          Permission.REPORT_CREATE,
          Permission.REPORT_UPDATE,
          Permission.REPORT_DELETE,
          Permission.USER_READ,
          Permission.USER_CREATE,
          Permission.USER_UPDATE,
          Permission.USER_DELETE,
          Permission.AUDIT_READ,
          Permission.AUDIT_DELETE)),
  /** The ones who monitor and control the electrical distribution system in real-time. */
  OPERATIONS(
      Set.of(
          Permission.ANALYSIS_READ,
          Permission.DIAGNOSTIC_READ,
          Permission.TRANSFORMER_READ,
          Permission.REPORT_READ)),
  /** Field technicians who maintain power lines, transformers, and substations. */
  MAINTENANCE(
      Set.of(
          Permission.ANALYSIS_CREATE,
          Permission.ANALYSIS_READ,
          Permission.TRANSFORMER_READ,
          Permission.TRANSFORMER_UPDATE)),
  /** Distribution engineers who design and upgrade the electrical grid. */
  ENGINEERING(
      Set.of(
          Permission.ANALYSIS_READ,
          Permission.DIAGNOSTIC_READ,
          Permission.TRANSFORMER_READ,
          Permission.REPORT_READ,
          Permission.REPORT_CREATE)),
  /** System planners who forecast energy needs and plan infrastructure expansion. */
  PLANNING(Set.of(Permission.ANALYSIS_READ, Permission.DIAGNOSTIC_READ, Permission.REPORT_READ)),
  /** The representatives that handles billing and service inquiries. */
  CUSTOMER_SERVICE(Set.of(Permission.REPORT_READ)),
  /** Account managers for large commercial and industrial customers. */
  BUSINESS(Set.of(Permission.DIAGNOSTIC_READ, Permission.REPORT_READ)),
  /** Operations managers overseeing daily grid operations. */
  MANAGEMENT(
      Set.of(
          Permission.ANALYSIS_READ,
          Permission.DIAGNOSTIC_READ,
          Permission.TRANSFORMER_READ,
          Permission.REPORT_READ,
          Permission.AUDIT_READ)),
  /** Regulatory affairs specialists ensuring compliance with utility regulations. */
  ADMINISTRATION(Set.of(Permission.USER_READ, Permission.USER_CREATE, Permission.USER_UPDATE)),
  /** IT specialists maintaining communication and control systems. */
  TECHNOLOGY(Set.of(Permission.USER_READ, Permission.USER_UPDATE, Permission.AUDIT_READ)),
  /** Data analysts working with smart grid, consumption data and gas generation. */
  ANALYST(
      Set.of(
          Permission.ANALYSIS_READ,
          Permission.DIAGNOSTIC_READ,
          Permission.REPORT_READ,
          Permission.REPORT_CREATE)),
  /** Safety coordinators ensuring workplace and public safety. */
  SAFETY(Set.of(Permission.AUDIT_READ, Permission.REPORT_READ)),
  /** Just someone passing by... */
  USER(Set.of(Permission.REPORT_READ));

  private final Set<Permission> permissions;

  /**
   * Gets a set os permissions for given role.
   *
   * @return a set of permissions.
   */
  public Set<String> getPermissionStrings() {
    return permissions.stream().map(Permission::getPermission).collect(Collectors.toSet());
  }
}
