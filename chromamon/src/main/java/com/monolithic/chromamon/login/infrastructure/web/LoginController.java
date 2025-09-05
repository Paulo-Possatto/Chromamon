package com.monolithic.chromamon.login.infrastructure.web;

import com.monolithic.chromamon.login.application.service.LoginService;
import com.monolithic.chromamon.login.domain.model.request.LoginRequest;
import com.monolithic.chromamon.login.domain.model.response.LoginResponse;
import com.monolithic.chromamon.shared.domain.security.SwaggerConstants;
import com.monolithic.chromamon.shared.infrastructure.web.GlobalExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for login resources.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

   private final LoginService loginService;

   /**
    * Generates the JWT jwtToken from the login parameters.
    *
    * @param loginRequest the DTO required to do the login.
    * @return a response entity with the generated JWT object.
    */
   @Operation(
      summary = "Authenticate user",
      description = "Does the login and returns the JWT jwtToken",
      operationId = "doLogin",
      method = SwaggerConstants.METHOD_POST,
      tags = {
         SwaggerConstants.TAG_USER,
         SwaggerConstants.TAG_AUTHENTICATION
      },
      servers = {
         @Server(
            url = SwaggerConstants.SERVER_LOCALHOST
         )
      },
      requestBody =  @io.swagger.v3.oas.annotations.parameters.RequestBody(
         description = "The required information from the user to log in the application",
         required = true,
         content = @Content(
            schema = @Schema(
               implementation = LoginRequest.class
            )
         )
      ),
      responses = {
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
         hidden = true
      )
      LoginRequest loginRequest) {
      LoginResponse response = loginService.authenticate(loginRequest);
      return ResponseEntity.ok(response);
   }
}