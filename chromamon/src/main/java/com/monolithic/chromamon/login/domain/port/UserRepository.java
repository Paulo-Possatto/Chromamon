package com.monolithic.chromamon.login.domain.port;

import com.monolithic.chromamon.login.domain.model.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository {

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  Optional<User> findById(Long id);

  @Transactional
  User save(User user);

  @Transactional
  void deleteById(Long id);

  Page<User> findAll(Pageable pageable);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  void updateLastLoginAt(Long userId, java.time.LocalDateTime lastLoginAt);

  Optional<User> getByIdCode(String idCode);
}
