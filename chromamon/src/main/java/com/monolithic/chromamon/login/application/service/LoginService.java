package com.monolithic.chromamon.login.application.service;

import com.monolithic.chromamon.login.domain.model.LoginRequest;
import com.monolithic.chromamon.login.domain.model.LoginResponse;
import com.monolithic.chromamon.login.domain.model.User;
import com.monolithic.chromamon.login.domain.port.JwtService;
import com.monolithic.chromamon.login.domain.port.PasswordEncoder;
import com.monolithic.chromamon.login.domain.port.UserRepository;
import com.monolithic.chromamon.shared.application.security.PermissionService;
import com.monolithic.chromamon.shared.domain.security.Permission;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final JwtService jwtService;
   private final PermissionService permissionService;

   @Transactional
   public LoginResponse authenticate(LoginRequest loginRequest) {
      log.info("User authentication attempt: {}", loginRequest.getUsername());

      User user = userRepository.findByEmail(loginRequest.getUsername())
         .orElseThrow(() -> new RuntimeException("User not found"));

      if (Boolean.FALSE.equals(user.getActive())) {
         throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "User inactive");
      }

      if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
         throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED,"Invalid credentials");
      }

      userRepository.updateLastLoginAt(user.getId(), LocalDateTime.now());

      Set<String> permissions = permissionService.getUserPermissions(user.getId(), user.getRole())
         .stream()
         .map(Permission::getPermission)
         .collect(java.util.stream.Collectors.toSet());

      String token = jwtService.generateToken(user, permissions);

      LocalDateTime now = LocalDateTime.now();
      LocalDateTime expiresAt = now.plusHours(24);

      log.info("Successful authentication for user: {}", user.getUsername());

      return LoginResponse.builder()
         .token(token)
         .tokenType("Bearer")
         .expiresIn(86400L)
         .username(user.getUsername())
         .email(user.getEmail())
         .fullName(user.getFullName())
         .role(user.getRole().name())
         .permissions(permissions)
         .issuedAt(now)
         .expiresAt(expiresAt)
         .build();
   }

   public boolean validateToken(String token) {
      try {
         String username = jwtService.extractUsername(token);
         return jwtService.isTokenValid(token, username) && !jwtService.isTokenExpired(token);
      } catch (Exception e) {
         log.error("Error validating token: {}", e.getMessage());
         return false;
      }
   }

   public User getUserFromToken(String token) {
      String username = jwtService.extractUsername(token);
      return userRepository.findByUsername(username)
         .orElseThrow(() -> new RuntimeException("User not found"));
   }
}