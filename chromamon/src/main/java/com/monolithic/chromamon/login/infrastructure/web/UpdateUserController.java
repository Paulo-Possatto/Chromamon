package com.monolithic.chromamon.login.infrastructure.web;

import com.monolithic.chromamon.login.application.service.UserService;
import com.monolithic.chromamon.login.domain.model.request.UpdateUserRequest;
import com.monolithic.chromamon.login.domain.model.response.UpdateUserResponse;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Controller for updating user data. */
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
  @Operation(
      summary = "Update user",
      description = "Updates user data",
      operationId = "updateUser",
      method = SwaggerConstants.METHOD_PUT,
      tags = {SwaggerConstants.TAG_USER},
      security = {@SecurityRequirement(name = SwaggerConstants.AUTH_NAME)},
      servers = {@Server(url = SwaggerConstants.SERVER_LOCALHOST)},
      parameters = {
        @Parameter(
            name = "id",
            in = ParameterIn.PATH,
            description = "The identifier of the user",
            required = true,
            style = ParameterStyle.SIMPLE,
            schema = @Schema(implementation = String.class, type = SwaggerConstants.STRING),
            example = "1")
      },
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Information update the user information",
              content =
                  @Content(
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = UpdateUserRequest.class)),
              required = true),
      responses = {
        @ApiResponse(
            responseCode = "200",
            description =
                "The updated information about the user has been successfully saved in the database.",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UpdateUserResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Some parameter(s) does not comply with the required constraint(s)",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema =
                        @Schema(
                            implementation = GlobalExceptionHandler.ErrorResponse.class,
                            example =
                                """
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
          """))),
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
              "path": "/api/v1/auth/users",
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
              "message": "Access Denied: Insufficient permission: user:create",
              "path": "/api/v1/auth/users",
              "validationErrors": null
          }
          """))),
        @ApiResponse(
            responseCode = "500",
            description = "The user trying to be added to the system already exists",
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
              "path": "/api/v1/auth/users",
              "validationErrors": null
          }
          """)))
      })
  @PutMapping("/users/{id}")
  public ResponseEntity<UpdateUserResponse> updateUser(
      @PathVariable Long id, @RequestBody UpdateUserRequest user) {
    UpdateUserResponse updatedUser = userService.updateUser(id, user);
    return ResponseEntity.ok(updatedUser);
  }
}
