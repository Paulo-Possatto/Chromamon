package com.monolithic.chromamon.login.infrastructure.web;

import com.monolithic.chromamon.login.application.service.UserService;
import com.monolithic.chromamon.login.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for updating user data.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UpdateUserController {

   private final UserService userService;

   /**
    * Updates a user information.
    *
    * @param id the ID of the user to be updated
    * @param user the updated information of the user
    * @return the information updated in the database
    */
   @Operation(summary = "Update user", description = "Updates user data")
   @SecurityRequirement(name = "bearerAuth")
   @PutMapping("/users/{id}")
   public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
      User updatedUser = userService.updateUser(id, user);
      return ResponseEntity.ok(updatedUser);
   }
}
