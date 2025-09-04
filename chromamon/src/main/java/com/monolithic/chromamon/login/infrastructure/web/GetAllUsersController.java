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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
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
   @Operation(
      summary = "List users",
      description = "List all users from the system into pages (default: sort by idCode in ascending order)",
      operationId = "getAllUsers",
      method = SwaggerConstants.METHOD_GET,
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
            name = "page",
            in = ParameterIn.QUERY,
            description = "The page number containing an amount of users",
            style = ParameterStyle.FORM,
            schema = @Schema(
               implementation = Integer.class,
               type = SwaggerConstants.INTEGER
            ),
            example = "0"
         ),
         @Parameter(
            name = "size",
            in = ParameterIn.QUERY,
            description = "The amount of elements present in a page",
            style = ParameterStyle.FORM,
            schema = @Schema(
               implementation = Integer.class,
               type = SwaggerConstants.INTEGER
            ),
            example = "5"
         ),
         @Parameter(
            name = "sort",
            in = ParameterIn.QUERY,
            description = "The parameter to sort the elements",
            style = ParameterStyle.FORM,
            schema = @Schema(
               implementation = String.class,
               type = SwaggerConstants.STRING
            ),
            example = "firstName"
         )
      },
      responses = {
         @ApiResponse(
            responseCode = "200",
            description = "Successfully returns a page of users",
            content = @Content(
               mediaType = MediaType.APPLICATION_JSON_VALUE,
               schema = @Schema(
                  implementation = GetUserResponse.class,
                  example = """
                     {
                         "content": [
                             {
                                 "id": 2,
                                 "uuid": "5187f7f1-9c98-4b2b-ad01-2061b8bcac5f",
                                 "idCode": "BJ75PR",
                                 "username": "CarlosVivaldi",
                                 "email": "carlos.vivaldi@chromamon.com",
                                 "firstName": "Carlos",
                                 "lastName": "Wolfgang Vivaldi",
                                 "role": "USER",
                                 "isActive": true,
                                 "lastLoginAt": null
                             },
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
                             },
                             {
                                 "id": 7,
                                 "uuid": "383e15d1-2595-4e93-91cb-f32abc61e46a",
                                 "idCode": "EX43KW",
                                 "username": "MaxVerstappen",
                                 "email": "max.verstappen@chromamon.com",
                                 "firstName": "Max",
                                 "lastName": "Emilian Verstappen",
                                 "role": "MANAGEMENT",
                                 "isActive": true,
                                 "lastLoginAt": null
                             },
                             {
                                 "id": 3,
                                 "uuid": "c4b1e9ab-d9e0-4206-8986-dd959473e420",
                                 "idCode": "RI00TE",
                                 "username": "FernandoAlonso",
                                 "email": "fernando.alonso@chromamon.com",
                                 "firstName": "Fernando",
                                 "lastName": "Alonso",
                                 "role": "OPERATIONS",
                                 "isActive": true,
                                 "lastLoginAt": null
                             }
                         ],
                         "pageable": {
                             "pageNumber": 0,
                             "pageSize": 10,
                             "sort": {
                                 "sorted": true,
                                 "unsorted": false,
                                 "empty": false
                             },
                             "offset": 0,
                             "paged": true,
                             "unpaged": false
                         },
                         "totalPages": 1,
                         "totalElements": 4,
                         "last": true,
                         "first": true,
                         "size": 10,
                         "number": 0,
                         "sort": {
                             "sorted": true,
                             "unsorted": false,
                             "empty": false
                         },
                         "numberOfElements": 4,
                         "empty": false
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
                         "message": "Access Denied: Insufficient permission: user:read",
                         "path": "/api/v1/auth/users",
                         "validationErrors": null
                     }
                     """
               )
            )
         ),
         @ApiResponse(
            responseCode = "500",
            description = "Something went wrong while trying to delete the user",
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
   @SecurityRequirement(name = "bearerAuth")
   @GetMapping("/users")
   public ResponseEntity<Page<GetUserResponse>> getAllUsers(
      @Parameter(hidden = true)
      @PageableDefault(sort = "idCode", direction = Sort.Direction.ASC) Pageable pageable) {
      Page<GetUserResponse> users = userService.getAllUsers(pageable);
      return ResponseEntity.ok(users);
   }
}
