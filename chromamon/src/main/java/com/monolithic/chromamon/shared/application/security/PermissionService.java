package com.monolithic.chromamon.shared.application.security;

import com.monolithic.chromamon.shared.domain.security.Permission;
import com.monolithic.chromamon.shared.domain.security.Role;
import com.monolithic.chromamon.shared.domain.security.UserPermission;
import com.monolithic.chromamon.shared.infrastructure.security.UserPermissionRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Service for permission. */
@Service
@RequiredArgsConstructor
public class PermissionService {

  private final UserPermissionRepository userPermissionRepository;

  public boolean hasPermission(Long userId, Role role, Permission permission) {
    Set<Permission> rolePermissions = new HashSet<>(role.getPermissions());

    List<UserPermission> userPermissions = userPermissionRepository.findByUserId(userId);

    for (UserPermission userPermission : userPermissions) {
      if (userPermission.getGranted()) {
        rolePermissions.add(userPermission.getPermission());
      } else {
        rolePermissions.remove(userPermission.getPermission());
      }
    }

    return rolePermissions.contains(permission);
  }

  public Set<Permission> getUserPermissions(Long userId, Role role) {
    Set<Permission> permissions = new HashSet<>(role.getPermissions());

    List<UserPermission> userPermissions = userPermissionRepository.findByUserId(userId);

    for (UserPermission userPermission : userPermissions) {
      if (userPermission.getGranted()) {
        permissions.add(userPermission.getPermission());
      } else {
        permissions.remove(userPermission.getPermission());
      }
    }

    return permissions;
  }

  @Transactional
  public void grantPermission(Long userId, Permission permission) {
    UserPermission userPermission =
        userPermissionRepository
            .findByUserIdAndPermission(userId, permission)
            .orElse(new UserPermission(userId, permission, true));

    userPermission.setGranted(true);
    userPermissionRepository.save(userPermission);
  }

  @Transactional
  public void revokePermission(Long userId, Permission permission) {
    UserPermission userPermission =
        userPermissionRepository
            .findByUserIdAndPermission(userId, permission)
            .orElse(new UserPermission(userId, permission, false));

    userPermission.setGranted(false);
    userPermissionRepository.save(userPermission);
  }
}
