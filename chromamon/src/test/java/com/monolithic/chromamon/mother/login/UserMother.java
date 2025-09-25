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

import com.monolithic.chromamon.login.domain.model.User;
import java.time.LocalDateTime;
import java.util.UUID;

/** The mother class for the User domain object. */
public class UserMother {

  /** The constant for the user id. */
  public static final Long USER_ID = 1L;

  /** The constant for the user uuid. */
  public static final UUID USER_UUID = UUID.randomUUID();

  /** The constant for the user id code. */
  public static final String ID_CODE = "AB01CD";

  /** The constant for the encoded password. */
  public static final String ENCODED_PASSWORD = "encodedPassword";

  /** The constant for the current date-time. */
  public static final LocalDateTime NOW = LocalDateTime.now();

  /** The constant for the full name. */
  public static final String FULL_NAME = FIRST_NAME + " " + LAST_NAME;

  /**
   * The stub response for the database when creating a new user.
   *
   * @return the User domain object stubbed.
   */
  public static User userObjectOkDbResponse() {
    return User.builder()
        .id(USER_ID)
        .uuid(USER_UUID)
        .idCode(ID_CODE)
        .username(USERNAME)
        .email(EMAIL)
        .password(ENCODED_PASSWORD)
        .firstName(FIRST_NAME)
        .lastName(LAST_NAME)
        .role(ROLE)
        .active(IS_ACTIVE)
        .createdAt(NOW)
        .updatedAt(NOW)
        .build();
  }

  /**
   * A simple stub with user data.
   *
   * @return the stubbed user object.
   */
  public static User userObjectOk() {
    return User.builder()
        .id(USER_ID)
        .uuid(USER_UUID)
        .idCode(ID_CODE)
        .username(USERNAME)
        .email(EMAIL)
        .password(ENCODED_PASSWORD)
        .firstName(FIRST_NAME)
        .lastName(LAST_NAME)
        .role(ROLE)
        .active(IS_ACTIVE)
        .createdAt(NOW)
        .updatedAt(NOW)
        .build();
  }

  /**
   * Stub for the user object when updated and saved in the database.
   *
   * @return the updated user object.
   */
  public static User updatedSavedUser() {
    return User.builder()
        .id(USER_ID)
        .uuid(USER_UUID)
        .idCode(ID_CODE)
        .username(USERNAME)
        .email(NEW_EMAIL)
        .password(ENCODED_PASSWORD)
        .firstName(NEW_FIRST_NAME)
        .lastName(NEW_LAST_NAME)
        .role(NEW_ROLE)
        .active(IS_ACTIVE)
        .createdAt(NOW)
        .updatedAt(NOW)
        .build();
  }
}
