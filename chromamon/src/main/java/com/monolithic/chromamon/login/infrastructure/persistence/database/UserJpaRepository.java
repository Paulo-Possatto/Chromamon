package com.monolithic.chromamon.login.infrastructure.persistence.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

   Optional<UserEntity> findByUsername(String username);

   Optional<UserEntity> findByEmail(String email);

   boolean existsByUsername(String username);

   boolean existsByEmail(String email);

   @Modifying
   @Query("UPDATE UserEntity u SET u.lastLoginAt = :lastLoginAt WHERE u.id = :userId")
   void updateLastLoginAt(@Param("userId") Long userId, @Param("lastLoginAt") LocalDateTime lastLoginAt);

   Optional<UserEntity> findByIdCode(String idCode);
}
