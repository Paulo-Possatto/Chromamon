package com.monolithic.chromamon.login.infrastructure.web;

import com.monolithic.chromamon.login.application.service.UserService;
import com.monolithic.chromamon.login.domain.model.response.GetUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for getting all users.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class GetAllUsersController {

   private final UserService userService;

   /**
    * Endpoint to get all users stored in the database.
    *
    * @param pageable the pageable params.
    * @return a list of all the users.
    */
   @Operation(summary = "List users", description = "List all users from the system")
   @SecurityRequirement(name = "bearerAuth")
   @GetMapping("/users")
   public ResponseEntity<Page<GetUserResponse>> getAllUsers(
      @PageableDefault(sort = "idCode", direction = Sort.Direction.ASC) Pageable pageable) {
      Page<GetUserResponse> users = userService.getAllUsers(pageable);
      return ResponseEntity.ok(users);
   }
}
