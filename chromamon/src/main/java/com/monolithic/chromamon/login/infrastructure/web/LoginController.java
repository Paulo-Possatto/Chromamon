package com.monolithic.chromamon.login.infrastructure.web;

import com.monolithic.chromamon.login.application.service.LoginService;
import com.monolithic.chromamon.login.application.service.UserService;
import com.monolithic.chromamon.login.domain.model.LoginRequest;
import com.monolithic.chromamon.login.domain.model.LoginResponse;
import com.monolithic.chromamon.login.domain.model.User;
import com.monolithic.chromamon.shared.domain.security.Permission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for authentication and user management")
public class LoginController {

   private final LoginService loginService;
   private final UserService userService;

   @Operation(summary = "Authenticate user", description = "Does the login and returns the JWT token")
   @PostMapping("/login")
   public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
      LoginResponse response = loginService.authenticate(loginRequest);
      return ResponseEntity.ok(response);
   }

   @Operation(summary = "Validate token", description = "Validates the JWT Token")
   @PostMapping("/validate")
   public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
      boolean isValid = loginService.validateToken(token);
      return ResponseEntity.ok(isValid);
   }

   @Operation(summary = "Create user", description = "Creates a new user for the system")
   @SecurityRequirement(name = "bearerAuth")
   @PostMapping("/users")
   public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
      User createdUser = userService.createUser(user);
      return ResponseEntity.ok(createdUser);
   }

   @Operation(summary = "List users", description = "List all users from the system")
   @SecurityRequirement(name = "bearerAuth")
   @GetMapping("/users")
   public ResponseEntity<List<User>> getAllUsers() {
      List<User> users = userService.getAllUsers();
      return ResponseEntity.ok(users);
   }

   @Operation(summary = "Search user", description = "Search a user by its ID")
   @SecurityRequirement(name = "bearerAuth")
   @GetMapping("/users/{id}")
   public ResponseEntity<User> getUserById(@PathVariable Long id) {
      User user = userService.getUserById(id);
      return ResponseEntity.ok(user);
   }

   @Operation(summary = "Update user", description = "Updates user data")
   @SecurityRequirement(name = "bearerAuth")
   @PutMapping("/users/{id}")
   public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
      User updatedUser = userService.updateUser(id, user);
      return ResponseEntity.ok(updatedUser);
   }

   @Operation(summary = "Delete user", description = "Remove a user from the system")
   @SecurityRequirement(name = "bearerAuth")
   @DeleteMapping("/users/{id}")
   public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
      userService.deleteUser(id);
      return ResponseEntity.noContent().build();
   }

   @Operation(summary = "Grant permission", description = "Grant a specific permission for a user")
   @SecurityRequirement(name = "bearerAuth")
   @PostMapping("/users/{userId}/permissions/{permission}/grant")
   public ResponseEntity<Void> grantPermission(
      @PathVariable Long userId,
      @PathVariable Permission permission) {
      userService.grantPermission(userId, permission);
      return ResponseEntity.ok().build();
   }

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