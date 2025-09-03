package com.monolithic.chromamon.login.infrastructure.web;

import com.monolithic.chromamon.login.application.service.UserService;
import com.monolithic.chromamon.login.domain.model.response.GetUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for searching a specific user.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class SearchUserController {

   private final UserService userService;

   /**
    * Get a specific user from its ID.
    *
    * @param codeId the user internal ID
    * @return the information of the searched user.
    */
   @Operation(summary = "Search user", description = "Search a user by its internal ID")
   @SecurityRequirement(name = "bearerAuth")
   @GetMapping("/users/{codeId}")
   public ResponseEntity<GetUserResponse> getUserById(
      @PathVariable(name = "codeId")
      String codeId) {
      GetUserResponse user = userService.getUserByCodeId(codeId);
      return ResponseEntity.ok(user);
   }
}
