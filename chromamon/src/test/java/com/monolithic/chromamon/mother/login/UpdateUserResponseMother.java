package com.monolithic.chromamon.mother.login;

import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.EMAIL;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.FIRST_NAME;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.IS_ACTIVE;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.LAST_NAME;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.ROLE;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.USERNAME;
import static com.monolithic.chromamon.mother.login.UpdateUserRequestMother.NEW_EMAIL;
import static com.monolithic.chromamon.mother.login.UpdateUserRequestMother.NEW_FIRST_NAME;
import static com.monolithic.chromamon.mother.login.UpdateUserRequestMother.NEW_LAST_NAME;
import static com.monolithic.chromamon.mother.login.UpdateUserRequestMother.NEW_ROLE;
import static com.monolithic.chromamon.mother.login.UserMother.USER_ID;

import com.monolithic.chromamon.login.domain.model.response.UpdateUserResponse;

/** The mother class for the UpdateUserResponse object. */
public class UpdateUserResponseMother {

  /** The */
  public static final String NEW_FULL_NAME = NEW_FIRST_NAME + " " + NEW_LAST_NAME;

  private static final String PARTIAL_FULL_NAME = NEW_FIRST_NAME + " " + LAST_NAME;

  private static final String FIRST_NAME_EQUAL = FIRST_NAME + " " + NEW_LAST_NAME;

  /**
   * Returns a stub object for an updated user.
   *
   * @return the UpdateUserResponse object.
   */
  public static UpdateUserResponse updateUserResponse() {
    return UpdateUserResponse.builder()
        .id(USER_ID)
        .username(USERNAME)
        .email(NEW_EMAIL)
        .firstName(NEW_FIRST_NAME)
        .fullName(NEW_FULL_NAME)
        .role(NEW_ROLE)
        .isActive(IS_ACTIVE)
        .build();
  }

  /**
   * Returns a stub object for an updated user with partial data.
   *
   * @return the UpdateUserResponse object.
   */
  public static UpdateUserResponse updateUserResponseWithPartialData() {
    return UpdateUserResponse.builder()
        .id(USER_ID)
        .username(USERNAME)
        .email(EMAIL)
        .firstName(NEW_FIRST_NAME)
        .fullName(PARTIAL_FULL_NAME)
        .role(ROLE)
        .isActive(IS_ACTIVE)
        .build();
  }

  public static UpdateUserResponse updateUserResponseWithSameFirstName() {
    return UpdateUserResponse.builder()
        .id(USER_ID)
        .username(USERNAME)
        .email(NEW_EMAIL)
        .firstName(FIRST_NAME)
        .fullName(FIRST_NAME_EQUAL)
        .role(NEW_ROLE)
        .isActive(IS_ACTIVE)
        .build();
  }
}
