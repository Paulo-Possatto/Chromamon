package com.monolithic.chromamon.login.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

   @NotBlank(message = "Username is obligatory")
   private String username;

   @NotBlank(message = "Password is obligatory")
   private String password;
}
