package com.monolithic.chromamon.login.infrastructure.web;

import com.monolithic.chromamon.login.application.service.LoginService;
import com.monolithic.chromamon.login.application.service.UserService;
import com.monolithic.chromamon.login.domain.model.request.CreateUserRequest;
import com.monolithic.chromamon.login.domain.model.request.LoginRequest;
import com.monolithic.chromamon.login.domain.model.response.LoginResponse;
import com.monolithic.chromamon.login.domain.model.User;
import com.monolithic.chromamon.shared.domain.security.Permission;
import com.monolithic.chromamon.shared.domain.security.SwaggerType;
import com.monolithic.chromamon.shared.infrastructure.web.GlobalExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for login resources.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

   private final LoginService loginService;
   private final UserService userService;

   /**
    * Generates the JWT jwtToken from the login parameters.
    *
    * @param loginRequest the DTO required to do the login.
    * @return a response entity with the generated JWT object.
    */
   @Tag(name = SwaggerType.TAG_AUTHENTICATION)
   @Operation(
      summary = "Authenticate user",
      description = "Does the login and returns the JWT jwtToken",
      operationId = "doLogin"
   )
   @ApiResponses(
      value = {
         @ApiResponse(
            responseCode = "200",
            description = "Successful login and generating jwtToken",
            content = @Content(
               mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(
                  implementation = LoginResponse.class
               )
            )
         ),
         @ApiResponse(
            responseCode = "400",
            description = "Some parameter(s) in the login request object is wrong",
            content = @Content(
               mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(
                  implementation = GlobalExceptionHandler.ErrorResponse.class
               ),
               examples = {
                  @ExampleObject(
                     name = "Blank email",
                     description = "400 error response for when the email parameter is not added in the request",
                     value = """
                        {
                            "timestamp": "2025-08-29T19:34:15.84031303",
                            "status": 400,
                            "error": "Bad Request",
                            "message": "Error validating fields",
                            "path": "/api/v1/auth/login",
                            "validationErrors": {
                                "email": "Email is obligatory"
                            }
                        }
                        """
                  ),
                  @ExampleObject(
                     name = "Wrong email",
                     description = "400 error response for when the email parameter does not have the correct format",
                     value = """
                        {
                            "timestamp": "2025-08-29T19:33:57.134365635",
                            "status": 400,
                            "error": "Bad Request",
                            "message": "Error validating fields",
                            "path": "/api/v1/auth/login",
                            "validationErrors": {
                                "email": "Email has a wrong format"
                            }
                        }
                        """
                  ),
                  @ExampleObject(
                     name = "Blank password",
                     description = "400 error response for when the password parameter is blank",
                     value = """
                        {
                            "timestamp": "2025-08-29T19:33:22.671273886",
                            "status": 400,
                            "error": "Bad Request",
                            "message": "Error validating fields",
                            "path": "/api/v1/auth/login",
                            "validationErrors": {
                                "password": "Password is obligatory"
                            }
                        }
                        """
                  ),
                  @ExampleObject(
                     name = "Size password",
                     description = "400 error response for when the password parameter is too short or too long",
                     value = """
                        {
                            "timestamp": "2025-08-29T19:32:57.349474758",
                            "status": 400,
                            "error": "Bad Request",
                            "message": "Error validating fields",
                            "path": "/api/v1/auth/login",
                            "validationErrors": {
                                "password": "Password must be between 7 and 20 characters"
                            }
                        }
                        """
                  ),
                  @ExampleObject(
                     name = "Pattern password",
                     description = "400 error response for when the password does not satisfies the required characters",
                     value = """
                        {
                            "timestamp": "2025-08-29T19:32:07.245861278",
                            "status": 400,
                            "error": "Bad Request",
                            "message": "Error validating fields",
                            "path": "/api/v1/auth/login",
                            "validationErrors": {
                                "password": "Password must contain at least one uppercase letter, one lowercase letter and one number"
                            }
                        }
                        """
                  )
               }
            )
         ),
         @ApiResponse(
            responseCode = "401",
            description = "Some access configuration for the user logging in is disabled",
            content = @Content(
               mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(
                  implementation = GlobalExceptionHandler.ErrorResponse.class
               ),
               examples = {
                  @ExampleObject(
                     name = "Invalid credentials",
                     description = "401 error response for when the user input is invalid",
                     value = """
                        {
                            "timestamp": "2025-08-29T19:27:52.019612812",
                            "status": 401,
                            "error": "Unauthorized",
                            "message": "Invalid credentials",
                            "path": "/api/v1/auth/login",
                            "validationErrors": null
                        }
                        """
                  ),
                  @ExampleObject(
                     name = "User Inactive",
                     description = "401 error response for when the user is set to inactive in the database",
                     value = """
                        {
                            "timestamp": "2025-08-29T19:27:52.019612812",
                            "status": 401,
                            "error": "Unauthorized",
                            "message": "User Inactive",
                            "path": "/api/v1/auth/login",
                            "validationErrors": null
                        }
                        """
                  )
               }
            )
         ),
         @ApiResponse(
            responseCode = "404",
            description = "The user that has the provided email was not found in the database",
            content = @Content(
               mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(
                  implementation = GlobalExceptionHandler.ErrorResponse.class,
                  example = """
                     {
                         "timestamp": "2025-08-29T20:13:30.565819877",
                         "status": 404,
                         "error": "Not Found",
                         "message": "User not found",
                         "path": "/api/v1/auth/login",
                         "validationErrors": null
                     }
                     """
               )
            )
         ),
         @ApiResponse(
            responseCode = "500",
            description = "Some internal error happened, this needs to be verified by the IT department",
            content = @Content(
               mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(
                  implementation = GlobalExceptionHandler.ErrorResponse.class,
                  example = """
                     {
                         "timestamp": "2025-08-29T20:13:30.565819877",
                         "status": 500,
                         "error": "Internal Server Error",
                         "message": "Internal Server Error",
                         "path": "/api/v1/auth/login",
                         "validationErrors": null
                     }
                     """
               )
            )
         )
      }
   )
   @PostMapping(
      value = "/login",
      produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<LoginResponse> login(
      @Valid
      @RequestBody
      @Parameter(
         name = "loginRequest",
         description = "The object containing the necessary information to log in the user",
         required = true,
         schema = @Schema(
            implementation = LoginRequest.class
         )
      )
      LoginRequest loginRequest) {
      LoginResponse response = loginService.authenticate(loginRequest);
      return ResponseEntity.ok(response);
   }

   /**
    * Endpoint for checking the JWT token validation.
    *
    * @param token the string JWT token.
    * @return true or false depending on the validation.
    */
   @Tag(name = SwaggerType.TAG_AUTHENTICATION)
   @Operation(
      summary = "Validate the user token",
      description = "Checks if the user using the application has a valid token",
      operationId = "validateToken"
   )
   @ApiResponses(
      value = {
         @ApiResponse(
            responseCode = "200",
            description = "Successfully checked the token validation",
            content = @Content(
               mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(
                  implementation = Boolean.class
               ),
               examples = {
                  @ExampleObject(
                     name = "Valid token",
                     description = "The returned boolean if the token is valid",
                     value = "true"
                  ),
                  @ExampleObject(
                     name = "Invalid token",
                     description = "The returned boolean if the token is not valid",
                     value = "false"
                  )
               }
            )
         ),
         @ApiResponse(
            responseCode = "500",
            description = "Some error occurred while validating the token, contact the IT",
            content = @Content(
               mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(
                  implementation = GlobalExceptionHandler.ErrorResponse.class,
                  example = """
                     {
                         "timestamp": "2025-08-29T20:13:30.565819877",
                         "status": 500,
                         "error": "Internal Server Error",
                         "message": "Internal Server Error",
                         "path": "/api/v1/auth/login",
                         "validationErrors": null
                     }
                     """
               )
            )
         )
      }
   )
   @PostMapping(
      value = "/validate",
      produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Boolean> validateToken(
      @RequestParam
      @NotBlank
      @Parameter(
         name = "token",
         description = "The string value of the JWT token generated by the application",
         required = true,
         schema = @Schema(
            implementation = String.class
         ),
         in = ParameterIn.QUERY
      )
      String token) {
      boolean isValid = loginService.validateToken(token);
      return ResponseEntity.ok(isValid);
   }

   @Operation(
      summary = "Create user",
      description = "Creates a new user for the system",
      operationId = "createUser"
   )
   @SecurityRequirement(name = "bearerAuth")
   @PostMapping(
      value = "/users",
      produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<User> createUser(
      @Valid
      @RequestBody
      CreateUserRequest user) {
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