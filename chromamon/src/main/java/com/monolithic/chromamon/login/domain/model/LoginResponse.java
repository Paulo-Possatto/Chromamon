package com.monolithic.chromamon.login.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

   private String token;
   private String tokenType;
   private Long expiresIn;
   private String username;
   private String email;
   private String fullName;
   private String role;
   private Set<String> permissions;
   private LocalDateTime issuedAt;
   private LocalDateTime expiresAt;
}