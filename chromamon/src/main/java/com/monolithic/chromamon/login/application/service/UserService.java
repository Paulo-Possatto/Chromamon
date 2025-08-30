package com.monolithic.chromamon.login.application.service;

import com.monolithic.chromamon.login.domain.model.User;
import com.monolithic.chromamon.login.domain.model.request.CreateUserRequest;
import com.monolithic.chromamon.login.domain.port.PasswordEncoder;
import com.monolithic.chromamon.login.domain.port.UserRepository;
import com.monolithic.chromamon.shared.application.security.HasPermission;
import com.monolithic.chromamon.shared.application.security.PermissionService;
import com.monolithic.chromamon.shared.domain.security.Permission;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for user-related resources.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final PermissionService permissionService;

   /**
    * Service for creating a new user.
    *
    * @param userRequest the CreateUserRequest object
    * @return the User object with the data stored in the database.
    */
   @HasPermission(Permission.USER_CREATE)
   @Transactional
   public User createUser(CreateUserRequest userRequest) {
      log.info("Creating new user: {}", userRequest.username());

      if (userRepository.existsByUsername(userRequest.username())) {
         throw new HttpClientErrorException(HttpStatus.CONFLICT, "Username already exists: " + userRequest.username());
      }

      if (userRepository.existsByEmail(userRequest.email())) {
         throw new HttpClientErrorException(HttpStatus.CONFLICT, "Email already exists: " + userRequest.email());
      }

      User user = User.builder()
         .username(userRequest.username())
         .email(userRequest.email())
         .password(passwordEncoder.encode(userRequest.password()))
         .firstName(userRequest.firstName())
         .lastName(userRequest.lastName())
         .role(userRequest.role())
         .active(userRequest.active())
         .createdAt(LocalDateTime.now())
         .updatedAt(LocalDateTime.now())
         .build();

      User savedUser = userRepository.save(user);
      log.info("User successfully created: {}", savedUser.getUsername());

      return savedUser;
   }

   @HasPermission(Permission.USER_READ)
   public List<User> getAllUsers() {
      return userRepository.findAll();
   }

   @HasPermission(Permission.USER_READ)
   public User getUserById(Long id) {
      return userRepository.findById(id)
         .orElseThrow(() -> new RuntimeException("User not found: " + id));
   }

   @HasPermission(Permission.USER_UPDATE)
   @Transactional
   public User updateUser(Long id, User userUpdates) {
      log.info("Updating user: {}", id);

      User existingUser = getUserById(id);

      if (userUpdates.getFirstName() != null) {
         existingUser.setFirstName(userUpdates.getFirstName());
      }
      if (userUpdates.getLastName() != null) {
         existingUser.setLastName(userUpdates.getLastName());
      }
      if (userUpdates.getEmail() != null && !userUpdates.getEmail().equals(existingUser.getEmail())) {
         if (userRepository.existsByEmail(userUpdates.getEmail())) {
            throw new RuntimeException("Email already exists: " + userUpdates.getEmail());
         }
         existingUser.setEmail(userUpdates.getEmail());
      }
      if (userUpdates.getRole() != null) {
         existingUser.setRole(userUpdates.getRole());
      }
      if (userUpdates.getActive() != null) {
         existingUser.setActive(userUpdates.getActive());
      }
      if (userUpdates.getPassword() != null && !userUpdates.getPassword().isEmpty()) {
         existingUser.setPassword(passwordEncoder.encode(userUpdates.getPassword()));
      }

      existingUser.setUpdatedAt(LocalDateTime.now());

      User updatedUser = userRepository.save(existingUser);
      log.info("User successfully updated: {}", updatedUser.getUsername());

      return updatedUser;
   }

   @HasPermission(Permission.USER_DELETE)
   @Transactional
   public void deleteUser(Long id) {
      log.info("Deleting user: {}", id);

      if (!userRepository.findById(id).isPresent()) {
         throw new RuntimeException("User not found: " + id);
      }

      userRepository.deleteById(id);
      log.info("User successfully deleted: {}", id);
   }

   @HasPermission(Permission.USER_UPDATE)
   @Transactional
   public void grantPermission(Long userId, Permission permission) {
      log.info("Grating permission '{}' for user '{}'", permission, userId);

      if (userRepository.findById(userId).isEmpty()) {
         throw new RuntimeException("User not found: " + userId);
      }

      permissionService.grantPermission(userId, permission);
      log.info("Permission successfully granted");
   }

   @HasPermission(Permission.USER_UPDATE)
   @Transactional
   public void revokePermission(Long userId, Permission permission) {
      log.info("Revoking permission {} from user {}", permission, userId);

      if (!userRepository.findById(userId).isPresent()) {
         throw new RuntimeException("User not found: " + userId);
      }

      permissionService.revokePermission(userId, permission);
      log.info("Permission successfully revoked");
   }
}
