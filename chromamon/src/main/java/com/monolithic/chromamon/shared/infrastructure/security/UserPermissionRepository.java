package com.monolithic.chromamon.shared.infrastructure.security;

import com.monolithic.chromamon.shared.domain.security.Permission;
import com.monolithic.chromamon.shared.domain.security.UserPermission;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** JPA Interface for the user permissions. */
@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {

  List<UserPermission> findByUserId(Long userId);

  Optional<UserPermission> findByUserIdAndPermission(Long userId, Permission permission);

  void deleteByUserIdAndPermission(Long userId, Permission permission);
}
