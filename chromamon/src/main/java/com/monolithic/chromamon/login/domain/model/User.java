package com.monolithic.chromamon.login.domain.model;

import com.monolithic.chromamon.shared.domain.security.Role;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The domain object used for the login service.
 *
 * @param id the generated user id.
 * @param uuid the generated user unique identifier (for processing use).
 * @param idCode the generated id allocated for the user (for internal use).
 * @param username the username.
 * @param email the user email.
 * @param password the user password (Used only to DTO -> Entity, never to Entity -> DTO).
 * @param firstName the user first name.
 * @param lastName the user last name.
 * @param role the user role.
 * @param active the flag that indicates if the user is active.
 * @param createdAt when the user was created.
 * @param updatedAt when the user was last updated.
 * @param lastLoginAt the last time when the user logged in.
 */
@Builder
public record User(
   Long id,
   UUID uuid,
   String idCode,
   String username,
   String email,
   String password,
   String firstName,
   String lastName,
   Role role,
   Boolean active,
   LocalDateTime createdAt,
   LocalDateTime updatedAt,
   LocalDateTime lastLoginAt)
{

   /**
    * Generates the user full name based on internal params.
    *
    * @return the user's full name.
    */
   public String getFullName() {
      return firstName + " " + lastName;
   }
}
