package com.monolithic.chromamon.login.infrastructure.persistence.mapper;

import com.monolithic.chromamon.login.domain.model.User;
import com.monolithic.chromamon.login.domain.model.response.GetUserResponse;
import com.monolithic.chromamon.login.infrastructure.persistence.database.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/** Mapper for the user object. */
@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

  /**
   * Converts the user entity to a domain user object.
   *
   * @param entity the User Entity.
   * @return the mapped user domain.
   */
  User toDomain(UserEntity entity);

  /**
   * Converts the domain object into a user entity ORM.
   *
   * @param domain the User domain object.
   * @return the Entity ORM.
   */
  UserEntity toEntity(User domain);

  /**
   * Converts the domain object into a GetUserResponse object.
   *
   * @param domain the User domain object.
   * @return the GetUserResponse mapped object.
   */
  @Mapping(target = "isActive", source = "active")
  GetUserResponse toGetUserResponse(User domain);

  void updateEntityFromDomain(User domain, @MappingTarget UserEntity entity);
}
