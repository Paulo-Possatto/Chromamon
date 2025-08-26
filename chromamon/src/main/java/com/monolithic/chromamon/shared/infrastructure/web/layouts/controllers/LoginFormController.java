package com.monolithic.chromamon.shared.infrastructure.web.layouts.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginFormController {

   private final WebClient.Builder webClientBuilder;

   @PostMapping("/login")
   public String doLogin(@RequestParam String username,
                         @RequestParam String password,
                         Model model,
                         HttpSession session) {
      log.info("[LOGIN CONTROLLER] - Login attempt for email: '{}'", username);
      var tokenResponse = webClientBuilder.build()
         .post()
         .uri("http://localhost:8080/api/v1/auth/login")
         .bodyValue(Map.of("username", username, "password", password))
         .retrieve()
         .bodyToMono(Map.class)
         .block();

      if(tokenResponse == null || tokenResponse.isEmpty()){
         model.addAttribute("error", "Invalid username or password");
         return "error";
      }

      String token = (String) tokenResponse.get("token");
      String user =  (String) tokenResponse.get("username");
      String issuedAt = (String) tokenResponse.get("issuedAt");
      String expiresAt = (String) tokenResponse.get("expiresAt");

      LocalDateTime issuedAtDateTime = LocalDateTime.parse(issuedAt);
      LocalDateTime expiresAtDateTime = LocalDateTime.parse(expiresAt);

      session.setAttribute("jwt", token);
      session.setAttribute("username", user);
      session.setAttribute("issuedAt", issuedAt);
      session.setAttribute("expiresAt", expiresAt);

      log.info("""
         [LOGIN CONTROLLER] - Login success for:
          - email: '{}'
          - username: {}
          - Issued at: {}
          - Expires at: {}
         """, username, user, issuedAtDateTime, expiresAtDateTime);

      return "landing";
   }
}
