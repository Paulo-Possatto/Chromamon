package com.monolithic.chromamon.login.domain.model.request;

import com.monolithic.chromamon.shared.domain.security.Role;
import com.monolithic.chromamon.shared.domain.security.SwaggerConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * The required object for creating a new user.
 *
 * @param username the username used by the application
 * @param email the created user email
 * @param password the created user password
 * @param firstName the new user first name
 * @param lastName the new user last name
 * @param role the role that the user will have
 * @param active the boolean that indicates if the new user is isActive
 */
@Builder
@Schema(
   name = "CreateUserRequest",
   description = "The request object for creating a new user",
   type = SwaggerConstants.OBJECT,
   example = """
      {
         "username": "JohnDoe",
         "email": "john.doe@email.com",
         "password": "IAmStillNotTheAdmin123",
         "firstName": "John",
         "lastName": "Smith Doe",
         "role": "MANAGEMENT",
         "isActive": true
      }
      """
)
public record CreateUserRequest(
   @NotBlank(message = "Username is obligatory")
   @Schema(
      name = "username",
      description = "The user's username",
      implementation = String.class,
      type = SwaggerConstants.STRING,
      example = "JohnDoe"
   )
   String username,
   @NotBlank(message = "Email is obligatory")
   @Email(message = "Email has a wrong format")
   @Schema(
      name = "email",
      description = "The user's email (It's used to log in the application)",
      implementation = String.class,
      type = SwaggerConstants.STRING,
      example = "john.doe@email.com"
   )
   String email,
   @NotBlank(message = "Password is obligatory")
   @Size(min = 7, max = 20, message = "Password must be between 7 and 20 characters")
   @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
      message = "Password must contain at least one uppercase letter, one lowercase letter and one number"
   )
   @Schema(
      name = "password",
      description = "The user's password (It's used to log in the application)",
      implementation = String.class,
      type = SwaggerConstants.STRING,
      example = "IAmStillNotTheAdmin123",
      pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$"
   )
   String password,
   @NotBlank(message = "First Name is obligatory")
   @Size(max = 40, message = "First name cannot succeed 40 characters")
   @Schema(
      name = "firstName",
      description = "The user's first name",
      implementation = String.class,
      type = SwaggerConstants.STRING,
      example = "John"
   )
   String firstName,
   @NotBlank(message = "Last Name is obligatory")
   @Size(max = 40, message = "Last name cannot succeed 40 characters")
   @Schema(
      name = "lastName",
      description = "The user's last name",
      implementation = String.class,
      type = SwaggerConstants.STRING,
      example = "Smith Doe"
   )
   String lastName,
   @NotNull(message = "Role is obligatory")
   @Schema(
      name = "role",
      description = "The user's role in the application",
      implementation = Role.class,
      type = SwaggerConstants.STRING,
      example = "MANAGEMENT"
   )
   Role role,
   @NotNull(message = "Active is obligatory")
   @Schema(
      name = "isActive",
      description = "Defines if the user can or cannot use the application's granted services",
      implementation = Boolean.class,
      type = SwaggerConstants.BOOLEAN,
      example = "true"
   )
   Boolean active
) {
}
