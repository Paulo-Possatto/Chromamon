package com.monolithic.chromamon.login.infrastructure.persistence;

import com.monolithic.chromamon.login.domain.model.User;
import com.monolithic.chromamon.login.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

   private final UserJpaRepository jpaRepository;
   private final UserMapper userMapper;

   @Override
   public Optional<User> findByUsername(String username) {
      return jpaRepository.findByUsername(username)
         .map(userMapper::toDomain);
   }

   @Override
   public Optional<User> findByEmail(String email) {
      return jpaRepository.findByEmail(email)
         .map(userMapper::toDomain);
   }

   @Override
   public Optional<User> findById(Long id) {
      return jpaRepository.findById(id)
         .map(userMapper::toDomain);
   }

   @Override
   public User save(User user) {
      UserEntity entity = userMapper.toEntity(user);
      UserEntity savedEntity = jpaRepository.save(entity);
      return userMapper.toDomain(savedEntity);
   }

   @Override
   public void deleteById(Long id) {
      jpaRepository.deleteById(id);
   }

   @Override
   public List<User> findAll() {
      return jpaRepository.findAll().stream()
         .map(userMapper::toDomain)
         .toList();
   }

   @Override
   public boolean existsByUsername(String username) {
      return jpaRepository.existsByUsername(username);
   }

   @Override
   public boolean existsByEmail(String email) {
      return jpaRepository.existsByEmail(email);
   }

   @Override
   public void updateLastLoginAt(Long userId, LocalDateTime lastLoginAt) {
      jpaRepository.updateLastLoginAt(userId, lastLoginAt);
   }
}