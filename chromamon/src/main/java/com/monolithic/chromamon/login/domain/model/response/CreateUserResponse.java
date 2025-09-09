package com.monolithic.chromamon.login.domain.model.response;

import com.monolithic.chromamon.shared.domain.security.Role;
import com.monolithic.chromamon.shared.domain.security.SwaggerConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

/**
 * The response object when a user is saved in the database.
 *
 * @param id the user generated ID.
 * @param username the user's username.
 * @param email the user email.
 * @param firstName the user first name.
 * @param fullName the user full name.
 * @param role the user role.
 * @param isActive flag that indicates if the user can access the protected resources.
 */
@Builder
@Schema(
    name = "CreateUserResponse",
    description = "The response object when a new user is added in the database",
    type = SwaggerConstants.OBJECT,
    example =
        """
      {
         "id": "1",
         "username": "JohnDoe",
         "email": "john.doe@email.com",
         "firstName": "John",
         "fullName": "John Smith Doe",
         "Role": "MANAGEMENT",
         "active": true
      }
      """)
public record CreateUserResponse(
    @Schema(
            name = "id",
            description = "The database-generated ID for the user",
            type = SwaggerConstants.INTEGER,
            implementation = Long.class,
            example = "1")
        long id,
    @Schema(
            name = "username",
            description = "The user's username",
            implementation = String.class,
            type = SwaggerConstants.STRING,
            example = "JohnDoe")
        String username,
    @Schema(
            name = "email",
            description = "The user's email (It's used to log in the application)",
            implementation = String.class,
            type = SwaggerConstants.STRING,
            example = "john.doe@email.com")
        String email,
    @Schema(
            name = "firstName",
            description = "The user's first name",
            implementation = String.class,
            type = SwaggerConstants.STRING,
            example = "John")
        String firstName,
    @Schema(
            name = "fullName",
            description = "The user's full name based on his first name and last name",
            implementation = String.class,
            type = SwaggerConstants.STRING,
            example = "John Smith Doe")
        String fullName,
    @Schema(
            name = "role",
            description = "The user's role in the application",
            implementation = Role.class,
            type = SwaggerConstants.STRING,
            example = "MANAGEMENT")
        Role role,
    @Schema(
            name = "isActive",
            description =
                "Defines if the user can or cannot use the application's granted services",
            implementation = Boolean.class,
            type = SwaggerConstants.BOOLEAN,
            example = "true")
        boolean isActive) {}
