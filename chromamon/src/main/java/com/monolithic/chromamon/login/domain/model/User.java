package com.monolithic.chromamon.login.domain.model;

import com.monolithic.chromamon.shared.domain.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

   private Long id;
   private String username;
   private String email;
   private String password;
   private String firstName;
   private String lastName;
   private Role role;
   private Boolean active;
   private LocalDateTime createdAt;
   private LocalDateTime updatedAt;
   private LocalDateTime lastLoginAt;

   /**
    * Generates the user full name.
    *
    * @return the user's full name.
    */
   public String getFullName() {
      return firstName + " " + lastName;
   }
}
