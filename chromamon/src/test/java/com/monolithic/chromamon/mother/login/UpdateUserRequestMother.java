package com.monolithic.chromamon.mother.login;

import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.EMAIL;

import com.monolithic.chromamon.login.domain.model.request.UpdateUserRequest;
import com.monolithic.chromamon.shared.domain.security.Role;

/** The mother class for the UpdateUserRequest object. */
public class UpdateUserRequestMother {

  /** The constant for the updated user email. */
  public static final String NEW_EMAIL = "newEmail";

  /** The constant for email different from the one saved. */
  public static final String DIFFERENT_EMAIL = "differentEmail";

  /** The constant for the updated user first name. */
  public static final String NEW_FIRST_NAME = "newFirstName";

  /** The constant for the updated user last name. */
  public static final String NEW_LAST_NAME = "newLastName";

  /** The constant for the updated user role. */
  public static final Role NEW_ROLE = Role.MANAGEMENT;

  /**
   * Returns a stub for an update user request with all the data.
   *
   * @return the UpdateUserRequest object with all the required data.
   */
  public static UpdateUserRequest updateUserRequest() {
    return UpdateUserRequest.builder()
        .email(NEW_EMAIL)
        .firstName(NEW_FIRST_NAME)
        .lastName(NEW_LAST_NAME)
        .role(NEW_ROLE)
        .build();
  }

  /**
   * Returns a stub for an update user request with partial data.
   *
   * @return the UpdateUserRequest object.
   */
  public static UpdateUserRequest updateUserRequestWithPartialData() {
    return UpdateUserRequest.builder().firstName(NEW_FIRST_NAME).build();
  }

  /**
   * Returns a stub for an update user request without email and last name.
   *
   * @return the UpdateUserRequest object.
   */
  public static UpdateUserRequest updateUserRequestWithNullEmailAndLastName() {
    return UpdateUserRequest.builder().firstName(NEW_FIRST_NAME).role(NEW_ROLE).build();
  }

  public static UpdateUserRequest updateUserRequestWithDifferentEmail() {
    return UpdateUserRequest.builder()
        .email(DIFFERENT_EMAIL)
        .firstName(NEW_FIRST_NAME)
        .lastName(NEW_LAST_NAME)
        .role(NEW_ROLE)
        .build();
  }

  public static UpdateUserRequest updateUserWithSameEmail() {
    return UpdateUserRequest.builder()
        .email(EMAIL)
        .firstName(NEW_FIRST_NAME)
        .lastName(NEW_LAST_NAME)
        .role(NEW_ROLE)
        .active(true)
        .build();
  }

  public static UpdateUserRequest updateUserWithoutFirstName() {
    return UpdateUserRequest.builder()
        .email(NEW_EMAIL)
        .lastName(NEW_LAST_NAME)
        .role(NEW_ROLE)
        .active(true)
        .build();
  }
}
