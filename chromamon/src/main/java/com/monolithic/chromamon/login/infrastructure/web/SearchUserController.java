package com.monolithic.chromamon.login.infrastructure.web;

import com.monolithic.chromamon.login.application.service.UserService;
import com.monolithic.chromamon.login.domain.model.response.GetUserResponse;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Controller for searching a specific user. */
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
  @Operation(
      summary = "Search user",
      description = "Search a user by its internal ID",
      operationId = "getUserById",
      method = SwaggerConstants.METHOD_GET,
      tags = {SwaggerConstants.TAG_USER},
      security = {@SecurityRequirement(name = SwaggerConstants.AUTH_NAME)},
      servers = {@Server(url = SwaggerConstants.SERVER_LOCALHOST)},
      parameters = {
        @Parameter(
            name = "codeId",
            in = ParameterIn.PATH,
            description = "The internal code identification of the user",
            required = true,
            style = ParameterStyle.SIMPLE,
            schema =
                @Schema(
                    implementation = String.class,
                    type = SwaggerConstants.STRING,
                    format = "[A-Z]{2}+[0-9]{2}+[A-Z]{2}"),
            example = "AB12CD")
      },
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully returns the user with given codeId",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema =
                        @Schema(
                            implementation = GetUserResponse.class,
                            example =
                                """
                       {
                           "id": 1,
                           "uuid": "3191403f-15f2-4797-a671-4172031a979a",
                           "idCode": "DR69TD",
                           "username": "admin",
                           "email": "admin@chromamon.com",
                           "firstName": "System",
                           "lastName": "Admin",
                           "role": "ADMIN",
                           "isActive": true,
                           "lastLoginAt": "2025-09-04T18:02:28.151699"
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
                         "path": "/api/v1/auth/users/DR69TD",
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
                         "message": "Access Denied: Insufficient permission: user:read",
                         "path": "/api/v1/auth/users/DR69TD",
                         "validationErrors": null
                     }
                     """))),
        @ApiResponse(
            responseCode = "500",
            description = "Something went wrong while getting user information",
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
                         "path": "/api/v1/auth/users/DR69TD",
                         "validationErrors": null
                     }
                     """)))
      })
  @GetMapping(value = "/users/{codeId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetUserResponse> getUserById(@PathVariable(name = "codeId") String codeId) {
    GetUserResponse user = userService.getUserByCodeId(codeId);
    return ResponseEntity.ok(user);
  }
}
