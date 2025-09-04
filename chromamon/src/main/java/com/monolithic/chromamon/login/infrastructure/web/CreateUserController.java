package com.monolithic.chromamon.login.infrastructure.web;

import com.monolithic.chromamon.login.application.service.UserService;
import com.monolithic.chromamon.login.domain.model.request.CreateUserRequest;
import com.monolithic.chromamon.login.domain.model.response.CreateUserResponse;
import com.monolithic.chromamon.shared.domain.security.SwaggerConstants;
import com.monolithic.chromamon.shared.infrastructure.web.GlobalExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for creating new user
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class CreateUserController {

   private final UserService userService;

   /**
    * Endpoint to create a new user (Requires role with permission).
    *
    * @param user the necessary data for creating a new user.
    * @return the information about the created user.
    */
   @Operation(
      summary = "Create user",
      description = "Creates a new user for the system",
      operationId = "createUser",
      method = SwaggerConstants.METHOD_POST,
      tags = {
         SwaggerConstants.TAG_AUTHENTICATION,
         SwaggerConstants.TAG_USER
      },
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
         description = "Information required to add a new user to the system",
         content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = CreateUserRequest.class)
         ),
         required = true
      ),
      security = {
         @SecurityRequirement(
            name = SwaggerConstants.AUTH_NAME
         )
      },
      servers = {
         @Server(
            url = SwaggerConstants.SERVER_LOCALHOST
         )
      },
      responses = {
         @ApiResponse(
            responseCode = "201",
            description = "The new user has been successfully added into the application database",
            content = @Content(
               mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(
                  implementation = CreateUserResponse.class
               )
            )
         ),
         @ApiResponse(
            responseCode = "400",
            description = "Some parameter(s) does not comply with the required constraint(s)",
            content = @Content(
               mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(
                  implementation = GlobalExceptionHandler.ErrorResponse.class,
                  example = """
                     {
                         "timestamp": "2025-09-04T17:22:09.649681754",
                         "status": 400,
                         "error": "Bad Request",
                         "message": "Error validating fields",
                         "path": "/api/v1/auth/users",
                         "validationErrors": {
                             "password": "Password must contain at least one uppercase letter, one lowercase letter and one number"
                         }
                     }
                     """
               )
            )
         ),
         @ApiResponse(
            responseCode = "401",
            description = "The 'Authorization' header token does not start with the required authorization scheme or is not present",
            content = @Content(
               mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(
                  implementation = GlobalExceptionHandler.ErrorResponse.class,
                  example = """
                     {
                         "timestamp": "2025-08-30T17:41:43.247846674",
                         "status": 401,
                         "error": "Unauthorized",
                         "message": "Authorization token invalid or not present",
                         "path": "/api/v1/auth/users",
                         "validationErrors": "null"
                     }
                     """
               )
            )
         ),
         @ApiResponse(
            responseCode = "403",
            description = "The role that the user have does not has the necessary permission to access the resource",
            content = @Content(
               mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(
                  implementation = GlobalExceptionHandler.ErrorResponse.class,
                  example = """
                     {
                         "timestamp": "2025-08-31T19:22:02.023790528",
                         "status": 403,
                         "error": "Forbidden",
                         "message": "Access Denied: Insufficient permission: user:create",
                         "path": "/api/v1/auth/users",
                         "validationErrors": null
                     }
                     """
               )
            )
         ),
         @ApiResponse(
            responseCode = "409",
            description = "The user trying to be added to the system already exists",
            content = @Content(
               mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(
                  implementation = GlobalExceptionHandler.ErrorResponse.class,
                  example = """
                     {
                         "timestamp": "2025-08-31T19:23:12.230182497",
                         "status": 409,
                         "error": "Conflict",
                         "message": "Username already exists: <username>",
                         "path": "/api/v1/auth/users",
                         "validationErrors": null
                     }
                     """
               )
            )
         ),
         @ApiResponse(
            responseCode = "500",
            description = "The user trying to be added to the system already exists",
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
                         "path": "/api/v1/auth/users",
                         "validationErrors": null
                     }
                     """
               )
            )
         )
      }
   )
   @PostMapping(
      value = "/users",
      produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<CreateUserResponse> createUser(
      @Valid
      @RequestBody
      CreateUserRequest user) {
      CreateUserResponse createdUser = userService.createUser(user);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
   }
}
