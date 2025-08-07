package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import es.paulopossatto.chromamon.analysisservice.domain.model.Observation;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.ObservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** Mapper interface for Observation data. */
@Mapper(
    componentModel = "spring",
    uses = {GasExtractionMapper.class, ConstantsMapper.class})
public interface ObservationMapper {
  /**
   * Convert the Observation ORM into an Observation domain object.
   *
   * @param entity the Observation ORM.
   * @return the Observation domain object.
   */
  @Mapping(source = "obsId", target = "observationId")
  @Mapping(source = "modUsed", target = "modelUsed")
  @Mapping(source = "gasExtMethod", target = "gasExtractionMethod")
  @Mapping(source = "sampleCond", target = "sampleCondition")
  @Mapping(source = "userCreated", target = "userCreated")
  @Mapping(source = "userUpdated", target = "userModified")
  @Mapping(
      source = "dtCreated",
      target = "dateTimeCreated",
      qualifiedByName = "mapOffsetDateTimeToString")
  @Mapping(
      source = "dtModified",
      target = "dateTimeModified",
      qualifiedByName = "mapOffsetDateTimeToString")
  Observation toDomain(ObservationEntity entity);

  /**
   * Convert the Observation domain object into an Observation ORM.
   *
   * @param domain the Observation domain object.
   * @return the Observation ORM.
   */
  @Mapping(source = "observationId", target = "obsId")
  @Mapping(source = "modelUsed", target = "modUsed")
  @Mapping(source = "gasExtractionMethod", target = "gasExtMethod")
  @Mapping(source = "sampleCondition", target = "sampleCond")
  ObservationEntity toEntity(Observation domain);
}
