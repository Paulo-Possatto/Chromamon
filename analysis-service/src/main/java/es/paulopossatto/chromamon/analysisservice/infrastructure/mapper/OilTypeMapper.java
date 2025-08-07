package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import es.paulopossatto.chromamon.analysisservice.domain.model.OilType;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.OilTypesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** Converter for OilType ORM and domain object. */
@Mapper(componentModel = "spring", uses = ConstantsMapper.class)
public interface OilTypeMapper {

  /**
   * Converts the OilType ORM into a domain object.
   *
   * @param entity the Oil Type ORM.
   * @return the Oil Type domain object.
   */
  @Mapping(target = "oilTypeId", source = "oilTypeId")
  @Mapping(target = "oilTypeTypo", source = "oilTypeTypo")
  @Mapping(target = "oilTypeLiteral", source = "oilTypeLiteral")
  @Mapping(
      source = "dtCreated",
      target = "dateTimeCreated",
      qualifiedByName = "mapOffsetDateTimeToString")
  @Mapping(
      source = "dtModified",
      target = "dateTimeModified",
      qualifiedByName = "mapOffsetDateTimeToString")
  @Mapping(source = "userCreated", target = "userCreated")
  @Mapping(source = "userUpdated", target = "userModified")
  OilType toDomain(OilTypesEntity entity);

  /**
   * Converts the Oil Type domain object into an ORM.
   *
   * @param domain the Oil Type domain object.
   * @return the ORM for the Oil Type.
   */
  @Mapping(target = "oilTypeId", source = "domain.oilTypeId")
  @Mapping(target = "oilTypeTypo", source = "oilTypeTypo")
  @Mapping(target = "oilTypeLiteral", source = "domain.oilTypeLiteral")
  OilTypesEntity toEntity(OilType domain);
}
