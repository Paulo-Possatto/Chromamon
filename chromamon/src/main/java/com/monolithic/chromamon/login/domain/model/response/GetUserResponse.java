package com.monolithic.chromamon.login.domain.model.response;

import com.monolithic.chromamon.shared.domain.security.Role;
import com.monolithic.chromamon.shared.domain.security.SwaggerConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;

/**
 * Information shown when user info is requested.
 *
 * @param id the user id
 * @param uuid the user unique identifier
 * @param idCode the user identification code (internal)
 * @param username the user's username
 * @param email the user email
 * @param firstName the user first name
 * @param lastName the user last name
 * @param role the user role
 * @param isActive the user flag that indicates if the user is isActive
 * @param lastLoginAt the last time when the user logged in
 */
@Builder
@Schema(
    name = "GetUserResponse",
    description = "The response object when the user information is requested",
    type = SwaggerConstants.OBJECT,
    example =
        """
      {
         "id": "1",
         "uuid": "3191403f-15f2-4797-a671-4172031a979a",
         "idCode": "DR69TD",
         "username": "JohnDoe",
         "email": "john.doe@email.com",
         "firstName": "John",
         "lastName": "Smith Doe",
         "Role": "MANAGEMENT",
         "active": true,
         "lastLoginAt": "2025-08-31T22:09:12.569351"
      }
      """)
public record GetUserResponse(
    @Schema(
            name = "id",
            description = "The database-generated ID for the user",
            type = SwaggerConstants.INTEGER,
            implementation = Long.class,
            example = "1")
        Long id,
    @Schema(
            name = "uuid",
            description = "The user unique identifier",
            type = SwaggerConstants.STRING,
            examples = "5187f7f1-9c98-4b2b-ad01-2061b8bcac5f",
            implementation = String.class)
        String uuid,
    @Schema(
            name = "idCode",
            description = "The user internal identification",
            type = SwaggerConstants.STRING,
            examples = "AB12CD",
            implementation = String.class)
        String idCode,
    @Schema(
            name = "username",
            description = "The user's username",
            implementation = String.class,
            type = SwaggerConstants.STRING,
            example = "JohnDoe")
        String username,
    @Schema(
            name = "email",
            description = "The user's email",
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
            name = "lastName",
            description = "The user's last name",
            implementation = String.class,
            type = SwaggerConstants.STRING,
            example = "Smith Doe")
        String lastName,
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
        Boolean isActive,
    @Schema(
            name = "lastLoginAt",
            description = "The date and time of when the user last logged in",
            type = SwaggerConstants.STRING,
            examples = "2025-05-01T15:30:10.0",
            implementation = LocalDateTime.class)
        LocalDateTime lastLoginAt) {}
