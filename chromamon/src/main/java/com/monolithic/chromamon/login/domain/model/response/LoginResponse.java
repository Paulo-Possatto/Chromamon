package com.monolithic.chromamon.login.domain.model.response;

import com.monolithic.chromamon.shared.domain.security.SwaggerConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;

/**
 * Response object for when login is successful.
 *
 * @param jwtToken the string JWT token value.
 * @param tokenType the type of authorization used by the token.
 * @param expiresIn the unix epoch value for when the token expires.
 * @param username the username for the logged user.
 * @param idCode the internal identifier code.
 * @param uuid the user unique identifier.
 * @param email the logged user email.
 * @param fullName the logged user full name.
 * @param role the logged user role.
 * @param permissions the permissions that the role has.
 * @param issuedAt the date-time of when the token was issued.
 * @param expiresAt the date-time of when the token expires.
 */
@Builder
@Schema(
    name = "LoginResponse",
    description = "Response DTO for when login is successful",
    type = SwaggerConstants.OBJECT,
    examples =
        """
      {
         "jwtToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30",
         "tokenType": "Bearer",
         "expiresIn": "864000",
         "username": "JohnDoe",
         "uuid": "3191403f-15f2-4797-a671-4172031a979a",
         "idCode": "DR69TD",
         "email": "john.doe@email.com",
         "fullName": "John Smith Doe",
         "role": "ENGINEER",
         "permissions": ["analysis:read", "diagnostic:read", "transformer:read", "report:read", "report:addAnalysis"],
         "issuedAt": "2024-05-01T13:30:10.0",
         "expiresAt": "2024-05-01T15:30:10.0"
      }
      """)
public record LoginResponse(
    @Schema(
            name = "jwtToken",
            description = "JWT token string value",
            type = SwaggerConstants.STRING,
            example =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30",
            implementation = String.class)
        String jwtToken,
    @Schema(
            name = "tokenType",
            description = "The token authorization type",
            type = SwaggerConstants.STRING,
            example = "Bearer",
            implementation = String.class)
        String tokenType,
    @Schema(
            name = "expiresIn",
            description = "Expiration time in seconds since Unix epoch",
            type = SwaggerConstants.INTEGER,
            example = "1516239022",
            implementation = Long.class)
        Long expiresIn,
    @Schema(
            name = "username",
            description = "The username for the logged user",
            type = SwaggerConstants.STRING,
            examples = "JohnDoe",
            implementation = String.class)
        String username,
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
            name = "email",
            description = "The user's email",
            type = SwaggerConstants.STRING,
            examples = "john.doe@email.com",
            implementation = String.class)
        String email,
    @Schema(
            name = "fullName",
            description = "The user's complete name",
            type = SwaggerConstants.STRING,
            examples = "John Smith Doe",
            implementation = String.class)
        String fullName,
    @Schema(
            name = "role",
            description = "The user role for the application",
            type = SwaggerConstants.STRING,
            examples = "ENGINEER",
            implementation = String.class)
        String role,
    @Schema(
            name = "permissions",
            description = "The array of permissions the user has",
            type = SwaggerConstants.ARRAY,
            examples =
                "[\"analysis:read\", \"diagnostic:read\", \"transformer:read\", \"report:read\", \"report:addAnalysis\"]",
            implementation = Set.class)
        Set<String> permissions,
    @Schema(
            name = "issuedAt",
            description = "The date and time of when the token was issued",
            type = SwaggerConstants.STRING,
            examples = "2024-05-01T13:30:10.0",
            implementation = LocalDateTime.class)
        LocalDateTime issuedAt,
    @Schema(
            name = "expiresAt",
            description = "The date and time of when the token expires",
            type = SwaggerConstants.STRING,
            examples = "2024-05-01T15:30:10.0",
            implementation = LocalDateTime.class)
        LocalDateTime expiresAt) {}
