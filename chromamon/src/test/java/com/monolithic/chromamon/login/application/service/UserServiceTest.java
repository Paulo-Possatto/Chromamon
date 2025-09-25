package com.monolithic.chromamon.login.application.service;

import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.EMAIL;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.FIRST_NAME;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.IS_ACTIVE;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.LAST_NAME;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.PASSWORD;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.ROLE;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.USERNAME;
import static com.monolithic.chromamon.mother.login.GetUserResponseMother.USER_UUID_STRING;
import static com.monolithic.chromamon.mother.login.UserMother.ENCODED_PASSWORD;
import static com.monolithic.chromamon.mother.login.UserMother.FULL_NAME;
import static com.monolithic.chromamon.mother.login.UserMother.ID_CODE;
import static com.monolithic.chromamon.mother.login.UserMother.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import com.monolithic.chromamon.login.domain.model.User;
import com.monolithic.chromamon.login.domain.model.response.CreateUserResponse;
import com.monolithic.chromamon.login.domain.model.response.GetUserResponse;
import com.monolithic.chromamon.login.domain.model.response.UpdateUserResponse;
import com.monolithic.chromamon.login.domain.port.AuditLoginService;
import com.monolithic.chromamon.login.domain.port.PasswordEncoder;
import com.monolithic.chromamon.login.domain.port.UserRepository;
import com.monolithic.chromamon.login.infrastructure.persistence.mapper.UserMapper;
import com.monolithic.chromamon.mother.login.CreateUserRequestMother;
import com.monolithic.chromamon.mother.login.GetUserResponseMother;
import com.monolithic.chromamon.mother.login.UpdateUserRequestMother;
import com.monolithic.chromamon.mother.login.UpdateUserResponseMother;
import com.monolithic.chromamon.mother.login.UserMother;
import com.monolithic.chromamon.shared.application.security.PermissionService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/** Unit tests for UserService class. */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @InjectMocks private UserService userService;

  @Mock private UserRepository userRepository;

  @Mock private PasswordEncoder passwordEncoder;

  @Mock private PermissionService permissionService;

  @Mock private UserMapper userMapper;

  @Mock private AuditLoginService auditLoginService;

  @Test
  @DisplayName(
      """
      GIVEN a CreateUserRequest object
      WHEN the createUser method is called
      AND no user has the same username
      AND no user has the same email
      THEN should save the user correctly
      """)
  void givenACreateUserRequest_whenUserDataIsNotConflicted_thenShouldSaveUser() {
    // Arrange
    Mockito.when(userRepository.existsByUsername(USERNAME)).thenReturn(false);

    Mockito.when(userRepository.existsByEmail(EMAIL)).thenReturn(false);

    Mockito.when(passwordEncoder.encode(PASSWORD)).thenReturn(ENCODED_PASSWORD);

    Mockito.when(userRepository.save(any(User.class)))
        .thenReturn(UserMother.userObjectOkDbResponse());

    // Act
    CreateUserResponse response =
        userService.createUser(CreateUserRequestMother.createUserRequestOk());

    // Assert
    Mockito.verify(userRepository).save(any(User.class));
    Mockito.verify(userRepository).existsByUsername(USERNAME);
    Mockito.verify(userRepository).existsByEmail(EMAIL);
    Mockito.verify(passwordEncoder).encode(PASSWORD);
    assertNotNull(response);
    assertEquals(USER_ID, response.id());
    assertEquals(USERNAME, response.username());
    assertEquals(EMAIL, response.email());
    assertEquals(FIRST_NAME, response.firstName());
    assertEquals(FULL_NAME, response.fullName());
    assertEquals(ROLE, response.role());
    assertEquals(IS_ACTIVE, response.isActive());
  }

  @Test
  @DisplayName(
      """
      GIVEN a CreateUserRequest object
      WHEN the createUser method is called
      AND no user has the same username
      AND some user has the same email
      THEN should throw HttpClientErrorException
      """)
  void givenACreateUserRequest_whenUserEmailHasConflict_thenShouldThrowException() {
    // Arrange
    Mockito.when(userRepository.existsByUsername(USERNAME)).thenReturn(false);

    Mockito.when(userRepository.existsByEmail(EMAIL)).thenReturn(true);

    // Act & Assert
    HttpClientErrorException exception =
        assertThrowsExactly(
            HttpClientErrorException.class,
            () -> userService.createUser(CreateUserRequestMother.createUserRequestOk()));

    Mockito.verify(userRepository).existsByUsername(USERNAME);
    Mockito.verify(userRepository).existsByEmail(EMAIL);
    assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    assertEquals("Email already exists: " + EMAIL, exception.getStatusText());
  }

  @Test
  @DisplayName(
      """
      GIVEN a CreateUserRequest object
      WHEN the createUser method is called
      AND some user has the same username
      THEN should throw HttpClientErrorException
      """)
  void givenCreateUserRequest_whenUsernameAlreadyExist_thenThrowClientException() {
    // Arrange
    Mockito.when(userRepository.existsByUsername(USERNAME)).thenReturn(true);

    // Act & Assert
    HttpClientErrorException exception =
        assertThrowsExactly(
            HttpClientErrorException.class,
            () -> userService.createUser(CreateUserRequestMother.createUserRequestOk()));

    Mockito.verify(userRepository).existsByUsername(USERNAME);
    assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    assertEquals("Username already exists: " + USERNAME, exception.getStatusText());
  }

  @Test
  @DisplayName(
      """
     GIVEN a pageable information
     WHEN getAllUsers method is called
     AND has users saved in the database
     THEN return a pageable of users
     """)
  void givenPageableInformation_whenGetAllUsersMethodIsCalled_thenGetAllUsers() {
    // Arrange
    Mockito.when(userMapper.toGetUserResponse(UserMother.userObjectOk()))
        .thenReturn(GetUserResponseMother.getUserResponseOk());

    Page<User> userPage = new PageImpl<>(List.of(UserMother.userObjectOk()));

    Mockito.when(userRepository.findAll(any(Pageable.class))).thenReturn(userPage);

    // Act
    Page<GetUserResponse> responsePage = userService.getAllUsers(mock(Pageable.class));

    // Assert
    Mockito.verify(userRepository).findAll(any(Pageable.class));
    Mockito.verify(userMapper).toGetUserResponse(UserMother.userObjectOk());
    assertNotNull(responsePage);
    GetUserResponse response = responsePage.getContent().getFirst();
    assertNotNull(response);
    assertEquals(USER_ID, response.id());
    assertEquals(USER_UUID_STRING, response.uuid());
    assertEquals(ID_CODE, response.idCode());
    assertEquals(USERNAME, response.username());
    assertEquals(EMAIL, response.email());
    assertEquals(FIRST_NAME, response.firstName());
    assertEquals(LAST_NAME, response.lastName());
    assertEquals(ROLE, response.role());
    assertEquals(IS_ACTIVE, response.isActive());
    assertNull(response.lastLoginAt());
  }

  @Test
  @DisplayName(
      """
     GIVEN a pageable information
     WHEN getAllUsers method is called
     AND has no users saved in the database
     THEN return an empty pageable
     """)
  void givenPageableInformation_whenGetAllUsersMethodIsCalled_thenAnEmptyPageIsReturned() {
    // Arrange
    Mockito.when(userRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());

    // Act
    Page<GetUserResponse> responsePage = userService.getAllUsers(mock(Pageable.class));

    // Assert
    Mockito.verify(userRepository).findAll(any(Pageable.class));
    assertNotNull(responsePage);
    List<GetUserResponse> response = responsePage.getContent();
    assertNotNull(response);
    assertEquals(Collections.emptyList(), response);
  }

  @Test
  @DisplayName(
      """
      GIVEN an user codeId
      WHEN getUserByCodeId method is called
      AND has user with given codeId
      THEN should return the response correctly
      """)
  void givenUserCodeId_whenGetUserByCodeId_thenGetUserByCodeId() {
    // Arrange
    Mockito.when(userMapper.toGetUserResponse(UserMother.userObjectOk()))
        .thenReturn(GetUserResponseMother.getUserResponseOk());

    Mockito.when(userRepository.getByIdCode(ID_CODE))
        .thenReturn(Optional.of(UserMother.userObjectOk()));

    // Act
    GetUserResponse response = userService.getUserByCodeId(ID_CODE);

    // Assert
    Mockito.verify(userMapper).toGetUserResponse(UserMother.userObjectOk());
    Mockito.verify(userRepository).getByIdCode(ID_CODE);
    assertNotNull(response);
    assertEquals(USER_ID, response.id());
    assertEquals(USER_UUID_STRING, response.uuid());
    assertEquals(ID_CODE, response.idCode());
    assertEquals(USERNAME, response.username());
    assertEquals(EMAIL, response.email());
    assertEquals(FIRST_NAME, response.firstName());
    assertEquals(LAST_NAME, response.lastName());
    assertEquals(ROLE, response.role());
    assertEquals(IS_ACTIVE, response.isActive());
    assertNull(response.lastLoginAt());
  }

  @Test
  @DisplayName(
      """
      GIVEN an user codeId
      WHEN getUserByCodeId method is called
      AND no user has the given codeId
      THEN should throw HttpClientErrorException
      """)
  void givenUserCodeId_whenGetUserByCodeId_thenThrowHttpClientErrorException() {
    // Arrange
    Mockito.when(userRepository.getByIdCode(ID_CODE)).thenReturn(Optional.empty());

    // Act & Assert
    HttpClientErrorException exception =
        assertThrowsExactly(
            HttpClientErrorException.class, () -> userService.getUserByCodeId(ID_CODE));

    // Assert
    Mockito.verify(userRepository).getByIdCode(ID_CODE);
    assertNotNull(exception);
    assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    assertEquals("User not found: " + ID_CODE, exception.getStatusText());
  }

  @Test
  @DisplayName(
      """
      GIVEN an UpdateUserRequest
      AND an userId
      WHEN the updateUser method is called
      AND the user exists
      AND the email is different same
      THEN should update the user data
      """)
  void givenUserId_whenUpdateUser_thenUpdateUser() {
    // Arrange
    Mockito.when(userRepository.findById(USER_ID))
        .thenReturn(Optional.of(UserMother.userObjectOk()));

    Mockito.when(userRepository.save(any(User.class))).thenReturn(UserMother.updatedSavedUser());

    Mockito.when(userMapper.toUpdateResponse(UserMother.updatedSavedUser()))
        .thenReturn(UpdateUserResponseMother.updateUserResponse());

    // Act
    UpdateUserResponse response =
        userService.updateUser(USER_ID, UpdateUserRequestMother.updateUserRequest());

    // Assert
    Mockito.verify(auditLoginService, times(2)).logUserUpdate(anyString());
    Mockito.verify(userRepository).findById(USER_ID);
    Mockito.verify(userMapper).toUpdateResponse(UserMother.updatedSavedUser());
    assertNotNull(response);
    assertEquals(UpdateUserResponseMother.updateUserResponse(), response);
  }

  @Test
  @DisplayName(
      """
      GIVEN an UpdateUserRequest
      AND an userId
      WHEN the updateUser method is called
      AND the user does not exists
      THEN should throw HttpClientErrorException
      """)
  void givenUserId_whenUpdateUserThatDoesNotExists_thenThrowHttpClientErrorException() {
    // Arrange
    Mockito.when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());

    // Act
    HttpClientErrorException exception =
        assertThrowsExactly(
            HttpClientErrorException.class,
            () -> userService.updateUser(USER_ID, UpdateUserRequestMother.updateUserRequest()));

    // Assert
    Mockito.verify(auditLoginService).logUserUpdate(anyString());
    Mockito.verify(userRepository).findById(USER_ID);
    assertNotNull(exception);
    assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    assertEquals("User not found: " + USER_ID, exception.getStatusText());
  }
}
