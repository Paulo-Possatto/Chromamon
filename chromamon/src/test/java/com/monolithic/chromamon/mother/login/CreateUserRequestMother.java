package com.monolithic.chromamon.mother.login;

import com.monolithic.chromamon.login.domain.model.request.CreateUserRequest;
import com.monolithic.chromamon.shared.domain.security.Role;

/** Mother class for CreateUserRequest object. */
public class CreateUserRequestMother {

  /** The constant for username. */
  public static final String USERNAME = "username";

  /** The constant for email. */
  public static final String EMAIL = "email";

  /** The constant for password. */
  public static final String PASSWORD = "password";

  /** The constant for first name. */
  public static final String FIRST_NAME = "firstName";

  /** The constant for last name. */
  public static final String LAST_NAME = "lastName";

  /** The constant for role. */
  public static final Role ROLE = Role.USER;

  /** The constant for isActive. */
  public static final Boolean IS_ACTIVE = true;

  /**
   * CreateUserRequest object for ok response.
   *
   * @return the ok object.
   */
  public static CreateUserRequest createUserRequestOk() {
    return CreateUserRequest.builder()
        .username(USERNAME)
        .email(EMAIL)
        .password(PASSWORD)
        .firstName(FIRST_NAME)
        .lastName(LAST_NAME)
        .role(ROLE)
        .isActive(IS_ACTIVE)
        .build();
  }
}
