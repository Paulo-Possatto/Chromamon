package com.monolithic.chromamon.mother.login;

import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.EMAIL;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.FIRST_NAME;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.IS_ACTIVE;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.LAST_NAME;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.ROLE;
import static com.monolithic.chromamon.mother.login.CreateUserRequestMother.USERNAME;
import static com.monolithic.chromamon.mother.login.UserMother.ID_CODE;
import static com.monolithic.chromamon.mother.login.UserMother.USER_ID;
import static com.monolithic.chromamon.mother.login.UserMother.USER_UUID;

import com.monolithic.chromamon.login.domain.model.response.GetUserResponse;

/** The mother class for the GetUserResponse object. */
public class GetUserResponseMother {

  /** The constant for the user uuid with type string. */
  public static final String USER_UUID_STRING = USER_UUID.toString();

  /**
   * Returns a stub of valid information for the GetUserResponse.
   *
   * @return a GetUserResponse with ok data.
   */
  public static GetUserResponse getUserResponseOk() {
    return GetUserResponse.builder()
        .id(USER_ID)
        .uuid(USER_UUID_STRING)
        .idCode(ID_CODE)
        .username(USERNAME)
        .email(EMAIL)
        .firstName(FIRST_NAME)
        .lastName(LAST_NAME)
        .role(ROLE)
        .isActive(IS_ACTIVE)
        .build();
  }
}
