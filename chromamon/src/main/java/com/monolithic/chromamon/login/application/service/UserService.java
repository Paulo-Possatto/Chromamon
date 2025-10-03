package com.monolithic.chromamon.login.application.service;

import com.monolithic.chromamon.login.domain.model.User;
import com.monolithic.chromamon.login.domain.model.request.CreateUserRequest;
import com.monolithic.chromamon.login.domain.model.request.UpdateUserRequest;
import com.monolithic.chromamon.login.domain.model.response.CreateUserResponse;
import com.monolithic.chromamon.login.domain.model.response.GetUserResponse;
import com.monolithic.chromamon.login.domain.model.response.UpdateUserResponse;
import com.monolithic.chromamon.login.domain.port.AuditLoginService;
import com.monolithic.chromamon.login.domain.port.PasswordEncoder;
import com.monolithic.chromamon.login.domain.port.UserRepository;
import com.monolithic.chromamon.login.infrastructure.persistence.mapper.UserMapper;
import com.monolithic.chromamon.shared.application.security.HasPermission;
import com.monolithic.chromamon.shared.application.security.PermissionService;
import com.monolithic.chromamon.shared.domain.security.Permission;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

/** Service class for user-related resources. */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final PermissionService permissionService;
  private final UserMapper userMapper;
  private final AuditLoginService audit;

  /**
   * Service for creating a new user.
   *
   * @param userRequest the CreateUserRequest object
   * @return the User object with the data stored in the database.
   */
  @HasPermission(Permission.USER_CREATE)
  public CreateUserResponse createUser(CreateUserRequest userRequest) {
    log.info("Creating new user: {}", userRequest.username());

    audit.logUserCreation("Creation request for user " + userRequest.username());

    if (userRepository.existsByUsername(userRequest.username())) {
      throw new HttpClientErrorException(
          HttpStatus.CONFLICT, "Username already exists: " + userRequest.username());
    }

    if (userRepository.existsByEmail(userRequest.email())) {
      throw new HttpClientErrorException(
          HttpStatus.CONFLICT, "Email already exists: " + userRequest.email());
    }

    User user =
        User.builder()
            .username(userRequest.username())
            .email(userRequest.email())
            .password(passwordEncoder.encode(userRequest.password()))
            .firstName(userRequest.firstName())
            .lastName(userRequest.lastName())
            .role(userRequest.role())
            .active(userRequest.isActive())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();

    User savedUser = userRepository.save(user);
    log.info("User successfully created: {}", savedUser.username());

    audit.logUserCreation("Created user: " + savedUser.username());

    return CreateUserResponse.builder()
        .id(savedUser.id())
        .username(savedUser.username())
        .email(savedUser.email())
        .firstName(savedUser.firstName())
        .fullName(savedUser.getFullName())
        .role(savedUser.role())
        .isActive(savedUser.active())
        .build();
  }

  /**
   * Returns a pageable of all the users.
   *
   * @param pageable the pageable interface for the query
   * @return a page of the users
   */
  @HasPermission(Permission.USER_READ)
  public Page<GetUserResponse> getAllUsers(Pageable pageable) {
    log.debug(
        "Getting all users... Page number: {}, page size: {}",
        pageable.getPageNumber(),
        pageable.getPageSize());
    audit.logGetAllUsers();
    return userRepository.findAll(pageable).map(userMapper::toGetUserResponse);
  }

  /**
   * Get a specific user
   *
   * @param codeId the user internal id.
   * @return the user information.
   */
  @HasPermission(Permission.USER_READ)
  public GetUserResponse getUserByCodeId(String codeId) {
    audit.logGetUserCodeById("Getting user information by code id: " + codeId);
    return userRepository
        .getByIdCode(codeId)
        .map(userMapper::toGetUserResponse)
        .orElseThrow(
            () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found: " + codeId));
  }

  /**
   * Updated the user information.
   *
   * @param id the id of the user to be updated.
   * @param userUpdates the required information to be changed
   * @return the updated user information
   */
  @HasPermission(Permission.USER_UPDATE)
  public UpdateUserResponse updateUser(Long id, UpdateUserRequest userUpdates) {
    log.info("Updating user: {}", id);

    audit.logUserUpdate("Updating user with ID " + id);

    User existingUser =
        userRepository
            .findById(id)
            .orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found: " + id));
    User.UserBuilder updatedUser = User.builder();

    updatedUser.id(existingUser.id());
    updatedUser.uuid(existingUser.uuid());
    updatedUser.idCode(existingUser.idCode());
    updatedUser.username(existingUser.username());
    updatedUser.password(existingUser.password());
    updatedUser.createdAt(existingUser.createdAt());

    if (userUpdates.email() != null && !userUpdates.email().equals(existingUser.email())) {
      if (userRepository.existsByEmail(userUpdates.email())) {
        throw new HttpClientErrorException(
            HttpStatus.CONFLICT, "Email already exists: " + userUpdates.email());
      }
      updatedUser.email(userUpdates.email());
    }
    if (userUpdates.firstName() != null) {
      updatedUser.firstName(userUpdates.firstName());
    } else {
      updatedUser.firstName(existingUser.firstName());
    }
    if (userUpdates.lastName() != null) {
      updatedUser.lastName(userUpdates.lastName());
    } else {
      updatedUser.lastName(existingUser.lastName());
    }
    if (userUpdates.role() != null) {
      updatedUser.role(userUpdates.role());
    } else {
      updatedUser.role(existingUser.role());
    }
    if (userUpdates.active() != null) {
      updatedUser.active(userUpdates.active());
    } else {
      updatedUser.active(existingUser.active());
    }

    updatedUser.updatedAt(LocalDateTime.now());

    User savedUpdatedUser = userRepository.save(updatedUser.build());

    log.info("User successfully updated: {}", savedUpdatedUser.username());

    audit.logUserUpdate("Updated user: " + savedUpdatedUser.username());

    return userMapper.toUpdateResponse(savedUpdatedUser);
  }

  /**
   * Deletes a user from the database (Will be changed in the future)
   *
   * @param id the id of the user to be deleted.
   */
  @HasPermission(Permission.USER_DELETE)
  public void deleteUser(Long id) {
    log.info("Deleting user: {}", id);

    audit.logUserDelete("Deleting user with ID " + id);

    if (userRepository.findById(id).isEmpty()) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found: " + id);
    }

    userRepository.deleteById(id);
    log.info("User successfully deleted: {}", id);
    audit.logUserDelete("Deleted user with ID " + id);
  }

  /**
   * Grants a specific permission to a user.
   *
   * @param userId the id of the user that the permission will be granted.
   * @param permission the specific permission to be granted.
   */
  @HasPermission(Permission.USER_UPDATE)
  public void grantPermission(Long userId, Permission permission) {
    log.info("Grating permission '{}' for user '{}'", permission, userId);

    User user =
        userRepository
            .findById(userId)
            .orElseThrow(
                () ->
                    new HttpClientErrorException(
                        HttpStatus.NOT_FOUND, "User not found: " + userId));

    Set<Permission> permissions = user.role().getPermissions();
    if (permissions.contains(permission)) {
      throw new HttpClientErrorException(
          HttpStatus.BAD_REQUEST, "User already have the permission: " + permission);
    }

    permissionService.grantPermission(userId, permission);
    log.info("Permission successfully granted");
  }

  /**
   * Revokes a specific permission from a user.
   *
   * @param userId the id of the user.
   * @param permission the permission to be revoked
   */
  @HasPermission(Permission.USER_UPDATE)
  public void revokePermission(Long userId, Permission permission) {
    log.info("Revoking permission {} from user {}", permission, userId);

    User user =
        userRepository
            .findById(userId)
            .orElseThrow(
                () ->
                    new HttpClientErrorException(
                        HttpStatus.NOT_FOUND, "User not found: " + userId));

    Set<Permission> permissions = user.role().getPermissions();
    if (!permissions.contains(permission)) {
      throw new HttpClientErrorException(
          HttpStatus.BAD_REQUEST, "User already doesn't have the permission: " + permission);
    }

    permissionService.revokePermission(userId, permission);
    log.info("Permission successfully revoked");
  }
}
