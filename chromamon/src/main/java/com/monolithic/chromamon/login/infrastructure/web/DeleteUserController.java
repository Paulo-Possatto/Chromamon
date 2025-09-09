package com.monolithic.chromamon.login.infrastructure.web;

import com.monolithic.chromamon.login.application.service.UserService;
import com.monolithic.chromamon.shared.domain.security.SwaggerConstants;
import com.monolithic.chromamon.shared.infrastructure.web.GlobalExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Controller for deleting user. */
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
  @Operation(
      summary = "Delete user",
      description = "Remove a user from the system",
      operationId = "deleteUser",
      method = SwaggerConstants.METHOD_DELETE,
      tags = {SwaggerConstants.TAG_USER},
      security = {@SecurityRequirement(name = SwaggerConstants.AUTH_NAME)},
      servers = {@Server(url = SwaggerConstants.SERVER_LOCALHOST)},
      parameters = {
        @Parameter(
            name = "id",
            in = ParameterIn.PATH,
            description = "The ID of the user to be deleted",
            required = true,
            style = ParameterStyle.SIMPLE,
            schema = @Schema(implementation = String.class, type = SwaggerConstants.STRING),
            example = "1")
      },
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "The user was successfully erased from the database"),
        @ApiResponse(
            responseCode = "401",
            description =
                "The 'Authorization' header token does not start with the required authorization scheme or is not present",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema =
                        @Schema(
                            implementation = GlobalExceptionHandler.ErrorResponse.class,
                            example =
                                """
                     {
                         "timestamp": "2025-08-30T17:41:43.247846674",
                         "status": 401,
                         "error": "Unauthorized",
                         "message": "Authorization token invalid or not present",
                         "path": "/api/v1/auth/users/1",
                         "validationErrors": "null"
                     }
                     """))),
        @ApiResponse(
            responseCode = "403",
            description =
                "The role that the user have does not has the necessary permission to access the resource",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema =
                        @Schema(
                            implementation = GlobalExceptionHandler.ErrorResponse.class,
                            example =
                                """
                     {
                         "timestamp": "2025-08-31T19:22:02.023790528",
                         "status": 403,
                         "error": "Forbidden",
                         "message": "Access Denied: Insufficient permission: user:delete",
                         "path": "/api/v1/auth/users/1",
                         "validationErrors": null
                     }
                     """))),
        @ApiResponse(
            responseCode = "404",
            description = "The user with the given ID was not found in the database",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema =
                        @Schema(
                            implementation = GlobalExceptionHandler.ErrorResponse.class,
                            example =
                                """
                     {
                         "timestamp": "2025-08-29T20:13:30.565819877",
                         "status": 404,
                         "error": "Not Found",
                         "message": "User not found",
                         "path": "/api/v1/auth/users/1",
                         "validationErrors": null
                     }
                     """))),
        @ApiResponse(
            responseCode = "500",
            description = "Something went wrong while trying to delete the user",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema =
                        @Schema(
                            implementation = GlobalExceptionHandler.ErrorResponse.class,
                            example =
                                """
                     {
                         "timestamp": "2025-08-29T20:13:30.565819877",
                         "status": 500,
                         "error": "Internal Server Error",
                         "message": "Internal Server Error",
                         "path": "/api/v1/auth/users/1",
                         "validationErrors": null
                     }
                     """)))
      })
  @SecurityRequirement(name = "bearerAuth")
  @DeleteMapping("/users/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok().build();
  }
}
