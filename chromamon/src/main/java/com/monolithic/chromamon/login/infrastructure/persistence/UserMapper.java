package com.monolithic.chromamon.login.infrastructure.persistence;

import com.monolithic.chromamon.login.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

   User toDomain(UserEntity entity);

   UserEntity toEntity(User domain);

   void updateEntityFromDomain(User domain, @MappingTarget UserEntity entity);
}