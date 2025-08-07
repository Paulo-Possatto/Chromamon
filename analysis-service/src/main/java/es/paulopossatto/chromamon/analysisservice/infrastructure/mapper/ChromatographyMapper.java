package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import es.paulopossatto.chromamon.analysisservice.domain.model.Chromatography;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.ChromatographyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** Mapper to convert the Chromatography object. */
@Mapper(componentModel = "spring", uses = ConstantsMapper.class)
public interface ChromatographyMapper {

  /**
   * Convert the Chromatography ORM into a Chromatography domain object.
   *
   * @param entity the Chromatography ORM.
   * @return the Chromatography domain object.
   */
  @Mapping(target = "id", source = "chrId")
  @Mapping(target = "hydrogen", source = "hydrogen")
  @Mapping(target = "methane", source = "methane")
  @Mapping(target = "ethane", source = "ethane")
  @Mapping(target = "acetylene", source = "acetylene")
  @Mapping(target = "carbonMonoxide", source = "carbonMonoxide")
  @Mapping(target = "carbonDioxide", source = "carbonDioxide")
  @Mapping(target = "userCreated", source = "userCreated")
  @Mapping(target = "userModified", source = "userUpdated")
  @Mapping(
      target = "dateTimeCreated",
      source = "dtCreated",
      qualifiedByName = "mapOffsetDateTimeToString")
  @Mapping(
      target = "dateTimeModified",
      source = "dtModified",
      qualifiedByName = "mapOffsetDateTimeToString")
  Chromatography toDomain(ChromatographyEntity entity);

  /**
   * Convert the Chromatography domain object into a Chromatography ORM.
   *
   * @param domain the Chromatography domain object.
   * @return the Chromatography ORM.
   */
  @Mapping(target = "chrId", source = "id")
  @Mapping(target = "hydrogen", source = "hydrogen")
  @Mapping(target = "methane", source = "methane")
  @Mapping(target = "ethane", source = "ethane")
  @Mapping(target = "acetylene", source = "acetylene")
  @Mapping(target = "carbonMonoxide", source = "carbonMonoxide")
  @Mapping(target = "carbonDioxide", source = "carbonDioxide")
  ChromatographyEntity toEntity(Chromatography domain);
}
