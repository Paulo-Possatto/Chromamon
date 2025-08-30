package com.monolithic.chromamon.login.application.service;

import com.monolithic.chromamon.login.domain.model.request.LoginRequest;
import com.monolithic.chromamon.login.domain.model.response.LoginResponse;
import com.monolithic.chromamon.login.domain.model.User;
import com.monolithic.chromamon.login.domain.port.JwtService;
import com.monolithic.chromamon.login.domain.port.PasswordEncoder;
import com.monolithic.chromamon.login.domain.port.UserRepository;
import com.monolithic.chromamon.shared.application.security.PermissionService;
import com.monolithic.chromamon.shared.domain.security.Permission;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Service for the login resources.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

   @Value("${application.security.jwt.expiration}")
   private long jwtExpiration;

   @Value("${application.security.jwt.auth-scheme}")
   private String jwtAuthScheme;

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final JwtService jwtService;
   private final PermissionService permissionService;

   /**
    * Validates the user logging in the application.
    *
    * @param loginRequest the login request object.
    * @return an object response with the necessary details.
    */
   @Transactional
   public LoginResponse authenticate(LoginRequest loginRequest) {
      log.info("User authentication attempt: {}", loginRequest.email());

      User user = userRepository.findByEmail(loginRequest.email())
         .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found"));

      if (Boolean.FALSE.equals(user.getActive())) {
         throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "User inactive");
      }

      if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
         throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED,"Invalid credentials");
      }

      userRepository.updateLastLoginAt(user.getId(), LocalDateTime.now());

      Set<String> permissions = permissionService.getUserPermissions(user.getId(), user.getRole())
         .stream()
         .map(Permission::getPermission)
         .collect(java.util.stream.Collectors.toSet());

      String token = jwtService.generateToken(user, permissions);

      LocalDateTime now = LocalDateTime.now();
      LocalDateTime expiresAt = now.plusSeconds(jwtExpiration);

      log.info("Successful authentication for user: {}", user.getUsername());

      return LoginResponse.builder()
         .jwtToken(token)
         .tokenType(jwtAuthScheme)
         .expiresIn(jwtExpiration)
         .username(user.getUsername())
         .email(user.getEmail())
         .fullName(user.getFullName())
         .role(user.getRole().name())
         .permissions(permissions)
         .issuedAt(now)
         .expiresAt(expiresAt)
         .build();
   }

   /**
    * Checks if the token is still valid.
    *
    * @param token the String JWT token.
    * @return a boolean value for the validation (True -> Valid, False -> Invalid)
    */
   public boolean validateToken(String token) {
      try {
         String username = jwtService.extractUsername(token);
         return jwtService.isTokenValid(token, username) && !jwtService.isTokenExpired(token);
      } catch (Exception e) {
         log.error("Error validating jwtToken: {}", e.getMessage());
         return false;
      }
   }

   /**
    * Extracts the User information with the JWT Token.
    *
    * @param token the String JWT token.
    * @return the User information object.
    */
   public User getUserFromToken(String token) {
      String username = jwtService.extractUsername(token);
      return userRepository.findByUsername(username)
         .orElseThrow(() -> new RuntimeException("User not found"));
   }
}