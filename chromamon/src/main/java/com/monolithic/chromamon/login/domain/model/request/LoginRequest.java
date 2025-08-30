package com.monolithic.chromamon.login.domain.model.request;

import com.monolithic.chromamon.shared.domain.security.SwaggerType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;


/**
 * Object required to log in the application.
 *
 * @param email the user email
 * @param password the user password
 */
@Builder
@Schema(
   name = "LoginRequest",
   description = "The request object for log in user to the application",
   type = SwaggerType.OBJECT,
   example = """
      {
         "email": "john.doe@email.com",
         "password": "IAmAnEngineer123"
      }
      """
)
public record LoginRequest(
   @Schema(
      name = "email",
      description = "The user email to access the application",
      type = SwaggerType.STRING,
      example = "john.doe@email.com",
      implementation = String.class
   )
   @NotBlank(message = "Email is obligatory")
   @Email(message = "Email has a wrong format")
   String email,
   @Schema(
      name = "password",
      description = "The user password to access the application",
      type = SwaggerType.STRING,
      example = "IAmAnEngineer123",
      implementation = String.class,
      pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{7,20}$"
   )
   @NotBlank(message = "Password is obligatory")
   @Size(min = 7, max = 20, message = "Password must be between 7 and 20 characters")
   @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
      message = "Password must contain at least one uppercase letter, one lowercase letter and one number"
   )
   String password
) {}


