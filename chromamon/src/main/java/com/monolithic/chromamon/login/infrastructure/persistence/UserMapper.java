package com.monolithic.chromamon.login.infrastructure.persistence;

import com.monolithic.chromamon.login.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapper for the user object.
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

   /**
    * Converts the user entity to a domain user object.
    *
    * @param entity the User Entity.
    * @return the mapped user domain.
    */
   User toDomain(UserEntity entity);

   UserEntity toEntity(User domain);

   void updateEntityFromDomain(User domain, @MappingTarget UserEntity entity);
}