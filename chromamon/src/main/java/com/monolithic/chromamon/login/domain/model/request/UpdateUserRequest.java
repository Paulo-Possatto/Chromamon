package com.monolithic.chromamon.login.domain.model.request;

import com.monolithic.chromamon.shared.domain.security.Role;
import com.monolithic.chromamon.shared.domain.security.SwaggerConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * DTO required to update an existing user.
 *
 * @param email the updated user email.
 * @param firstName the updated user first name.
 * @param lastName the updated user last name.
 * @param role the updated role for the user.
 * @param active the updated active flag for the user.
 */
@Builder
@Schema(
    name = "UpdateUserRequest",
    description = "The request object for updating an existing user",
    type = SwaggerConstants.OBJECT,
    example =
        """
    {
       "email": "john.doe@email.com",
       "firstName": "John",
       "lastName": "Smith Doe",
       "role": "MANAGEMENT",
       "isActive": true
    }
    """)
public record UpdateUserRequest(
    @NotBlank(message = "Email is obligatory")
        @Email(message = "Email has a wrong format")
        @Schema(
            name = "email",
            description = "The user's email (It's used to log in the application)",
            implementation = String.class,
            type = SwaggerConstants.STRING,
            example = "john.doe@email.com")
        String email,
    @NotBlank(message = "First Name is obligatory")
        @Size(max = 40, message = "First name cannot succeed 40 characters")
        @Schema(
            name = "firstName",
            description = "The user's first name",
            implementation = String.class,
            type = SwaggerConstants.STRING,
            example = "John")
        String firstName,
    @NotBlank(message = "Last Name is obligatory")
        @Size(max = 40, message = "Last name cannot succeed 40 characters")
        @Schema(
            name = "lastName",
            description = "The user's last name",
            implementation = String.class,
            type = SwaggerConstants.STRING,
            example = "Smith Doe")
        String lastName,
    @NotNull(message = "Role is obligatory")
        @Schema(
            name = "role",
            description = "The user's role in the application",
            implementation = Role.class,
            type = SwaggerConstants.STRING,
            example = "MANAGEMENT")
        Role role,
    @NotNull(message = "Active is obligatory")
        @Schema(
            name = "isActive",
            description =
                "Defines if the user can or cannot use the application's granted services",
            implementation = Boolean.class,
            type = SwaggerConstants.BOOLEAN,
            example = "true")
        Boolean active) {}
