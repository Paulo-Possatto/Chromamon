package com.monolithic.chromamon.login.infrastructure.web;

import com.monolithic.chromamon.login.application.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for deleting user.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class DeleteUserController {

   private final UserService userService;

   /**
    * Deletes a user from the database.
    *
    * @param id the ID of the user to be deleted.
    * @return an ok affirmation if the user is deleted.
    */
   @Operation(summary = "Delete user", description = "Remove a user from the system")
   @SecurityRequirement(name = "bearerAuth")
   @DeleteMapping("/users/{id}")
   public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
      userService.deleteUser(id);
      return ResponseEntity.ok().build();
   }
}
