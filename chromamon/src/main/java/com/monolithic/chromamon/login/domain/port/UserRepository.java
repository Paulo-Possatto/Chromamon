package com.monolithic.chromamon.login.domain.port;

import com.monolithic.chromamon.login.domain.model.User;
import com.monolithic.chromamon.login.domain.model.response.GetUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {

   Optional<User> findByUsername(String username);

   Optional<User> findByEmail(String email);

   Optional<User> findById(Long id);

   User save(User user);

   void deleteById(Long id);

   Page<GetUserResponse> findAll(Pageable pageable);

   boolean existsByUsername(String username);

   boolean existsByEmail(String email);

   void updateLastLoginAt(Long userId, java.time.LocalDateTime lastLoginAt);

   Optional<User> getByIdCode(String idCode);
}
