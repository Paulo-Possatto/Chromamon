package com.monolithic.chromamon.login.infrastructure.web;

import com.monolithic.chromamon.login.application.service.UserService;
import com.monolithic.chromamon.shared.domain.security.Permission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for revoking a user permission.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class RevokePermissionController {

   private final UserService userService;

   /**
    * Revokes a specific permission for a user.
    *
    * @param userId the id of the user to revoke the permission.
    * @param permission the permission to be revoked from the user.
    * @return a ok response if the revoke step is successful
    */
   @Operation(summary = "Revoke permission", description = "Remove a permission from a specific user")
   @SecurityRequirement(name = "bearerAuth")
   @PostMapping("/users/{userId}/permissions/{permission}/revoke")
   public ResponseEntity<Void> revokePermission(
      @PathVariable Long userId,
      @PathVariable Permission permission) {
      userService.revokePermission(userId, permission);
      return ResponseEntity.ok().build();
   }
}
