package com.monolithic.chromamon.login.domain.port;

public interface PasswordEncoder {

  String encode(String rawPassword);

  boolean matches(String rawPassword, String encodedPassword);
}
