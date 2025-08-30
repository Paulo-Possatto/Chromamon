package com.monolithic.chromamon.login.domain.model.request;

import com.monolithic.chromamon.shared.domain.security.Role;
import com.monolithic.chromamon.shared.domain.security.SwaggerType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
 * @param active the boolean that indicates if the new user is active
 */
@Builder
@Schema(
   name = "CreateUserRequest",
   description = "The request object for creating a new user",
   type = SwaggerType.OBJECT,
   example = """
      {
         "username": "JohnDoe",
         "email": "john.doe@email.com",
         "password": "IAmStillNotTheAdmin123",
         "firstName": "John",
         "lastName": "Smith Doe",
         "Role": "MANAGEMENT",
         "active": true
      }
      """
)
public record CreateUserRequest(
   @NotBlank(message = "Username is obligatory")
   String username,
   @NotBlank(message = "Email is obligatory")
   @Email(message = "Email has a wrong format")
   String email,
   @NotBlank(message = "Password is obligatory")
   @Size(min = 7, max = 20, message = "Password must be between 7 and 20 characters")
   @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
      message = "Password must contain at least one uppercase letter, one lowercase letter and one number"
   )
   String password,
   @NotBlank(message = "First Name is obligatory")
   @Size(max = 40, message = "First name cannot succeed 40 characters")
   String firstName,
   @NotBlank(message = "Last Name is obligatory")
   @Size(max = 40, message = "Last name cannot succeed 40 characters")
   String lastName,
   @NotBlank(message = "Role is obligatory")
   @Pattern(
      regexp = "ADMIN|OPERATIONS|MAINTENANCE|ENGINEERING|PLANNING|" +
         "CUSTOMER_SERVICE|BUSINESS|MANAGEMENT|ADMINISTRATION|TECHNOLOGY|" +
         "ANALYST|SAFETY|USER",
      message = "Role selected is not acceptable"
   )
   Role role,
   @NotBlank(message = "Active is obligatory")
   @Pattern(
      regexp = "true|false",
      message = "Active must be true or false"
   )
   Boolean active
) {
}
