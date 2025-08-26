package com.monolithic.chromamon.shared.domain.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for the user permissions.
 */
@Entity
@Table(name = "user_permissions", schema = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPermission {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "user_id", nullable = false)
   private Long userId;

   @Enumerated(EnumType.STRING)
   @Column(name = "permission", nullable = false)
   private Permission permission;

   @Column(name = "granted", nullable = false)
   private Boolean granted = true;

   /**
    * Constructor without the ID parameter.4
    *
    * @param userId     the user identification.
    * @param permission the user permission.
    * @param granted    is the user permission granted?
    */
   public UserPermission(Long userId, Permission permission, Boolean granted) {
      this.userId = userId;
      this.permission = permission;
      this.granted = granted;
   }
}
