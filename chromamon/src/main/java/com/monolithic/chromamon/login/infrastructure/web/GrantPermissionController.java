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
 * Controller for granting permission.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class GrantPermissionController {

   private final UserService userService;

   /**
    * Grants a specific permission for a user.
    *
    * @param userId the id of the user to grant the permission.
    * @param permission the permission to be granted.
    * @return an ok response if nothing bad happens.
    */
   @Operation(summary = "Grant permission", description = "Grant a specific permission for a user")
   @SecurityRequirement(name = "bearerAuth")
   @PostMapping("/users/{userId}/permissions/{permission}/grant")
   public ResponseEntity<Void> grantPermission(
      @PathVariable Long userId,
      @PathVariable Permission permission) {
      userService.grantPermission(userId, permission);
      return ResponseEntity.ok().build();
   }
}
