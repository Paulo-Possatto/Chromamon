package com.monolithic.chromamon.login.infrastructure.web;

import com.monolithic.chromamon.login.application.service.UserService;
import com.monolithic.chromamon.shared.domain.security.Permission;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for granting permission.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class GrantPermissionController {

   private final UserService userService;

   /**
    * Grants a specific permission for a user.
    *
    * @param userId the id of the user to grant the permission.
    * @param permission the permission to be granted.
    * @return an ok response if nothing bad happens.
    */
   @Operation(
      summary = "Grant permission",
      description = "Grant a specific permission for a user",
      operationId = "grantPermission",
      method = SwaggerConstants.METHOD_POST,
      tags = {
         SwaggerConstants.TAG_USER
      },
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
      parameters = {
         @Parameter(
            name = "userId",
            in = ParameterIn.PATH,
            description = "The ID of the user to have the permission granted",
            required = true,
            style = ParameterStyle.SIMPLE,
            schema = @Schema(
               implementation = String.class,
               type = SwaggerConstants.STRING
            ),
            example = "1"
         ),
         @Parameter(
            name = "permission",
            in = ParameterIn.PATH,
            description = "The permission to be granted to the specified user",
            required = true,
            style = ParameterStyle.SIMPLE,
            schema = @Schema(
               implementation = Permission.class,
               type = SwaggerConstants.STRING
            )
         )
      },
      responses = {
         @ApiResponse(
            responseCode = "200",
            description = "The permission was successfully added to the given user"
         ),
         @ApiResponse(
            responseCode = "400",
            description = "The user already has the given permission",
            content = @Content(
               mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(
                  implementation = GlobalExceptionHandler.ErrorResponse.class,
                  example = """
                     {
                         "timestamp": "2025-09-04T17:22:09.649681754",
                         "status": 400,
                         "error": "Bad Request",
                         "message": "User already have the permission: TRANSFORMER_READ",
                         "path": "/users/1/permissions/TRANSFORMER_READ/grant",
                         "validationErrors": null
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
                         "path": "/users/1/permissions/TRANSFORMER_READ/grant",
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
                         "message": "Access Denied: Insufficient permission: user:update",
                         "path": "/users/1/permissions/TRANSFORMER_READ/grant",
                         "validationErrors": null
                     }
                     """
               )
            )
         ),
         @ApiResponse(
            responseCode = "404",
            description = "The user with the given ID was not found in the database",
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
                         "path": "/users/1/permissions/TRANSFORMER_READ/grant",
                         "validationErrors": null
                     }
                     """
               )
            )
         ),
         @ApiResponse(
            responseCode = "500",
            description = "Something went wrong while trying to grant permission",
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
                         "path": "/users/1/permissions/TRANSFORMER_READ/grant",
                         "validationErrors": null
                     }
                     """
               )
            )
         )
      }
   )
   @PostMapping("/users/{userId}/permissions/{permission}/grant")
   public ResponseEntity<Void> grantPermission(
      @PathVariable Long userId,
      @PathVariable Permission permission) {
      userService.grantPermission(userId, permission);
      return ResponseEntity.ok().build();
   }
}
