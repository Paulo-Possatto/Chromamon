package com.monolithic.chromamon.login.infrastructure.persistence.impl;

import com.monolithic.chromamon.login.domain.model.User;
import com.monolithic.chromamon.login.domain.port.UserRepository;
import com.monolithic.chromamon.login.infrastructure.persistence.database.UserEntity;
import com.monolithic.chromamon.login.infrastructure.persistence.database.UserJpaRepository;
import com.monolithic.chromamon.login.infrastructure.persistence.mapper.UserMapper;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/** Implementation for user CRUD processes. */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserJpaRepository jpaRepository;
  private final UserMapper userMapper;

  @Override
  public Optional<User> findByUsername(String username) {
    return jpaRepository.findByUsername(username).map(userMapper::toDomain);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return jpaRepository.findByEmail(email).map(userMapper::toDomain);
  }

  @Override
  public Optional<User> findById(Long id) {
    return jpaRepository.findById(id).map(userMapper::toDomain);
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
  public Page<User> findAll(Pageable pageable) {
    Page<UserEntity> entityPage = jpaRepository.findAll(pageable);
    return entityPage.map(userMapper::toDomain);
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

  @Override
  public Optional<User> getByIdCode(String idCode) {
    return jpaRepository.findByIdCode(idCode).map(userMapper::toDomain);
  }
}
