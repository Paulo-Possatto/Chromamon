package com.monolithic.chromamon.login.infrastructure.security;

import com.monolithic.chromamon.login.domain.port.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordEncoderImpl implements PasswordEncoder {

   private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

   @Override
   public String encode(String rawPassword) {
      return bCryptPasswordEncoder.encode(rawPassword);
   }

   @Override
   public boolean matches(String rawPassword, String encodedPassword) {
      return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
   }
}
