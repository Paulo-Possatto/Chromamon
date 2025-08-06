package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import es.paulopossatto.chromamon.analysisservice.domain.model.Observation;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.ObservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/** The mapper that converts domain object to entity and vice versa. */
@Component
@RequiredArgsConstructor
public class ObservationEntityMapper {

  private final GasExtractionEntityMapper mapper;

  /**
   * Converts the ORM into a domain object.
   *
   * @param entity the ORM for the Observation data.
   * @return the domain object for the Observation data.
   */
  public Observation toDomain(ObservationEntity entity) {
    return Observation.builder()
        .observationId(entity.getObsId().toString())
        .sampleCondition(entity.getSampleCond())
        .gasExtractionMethod(mapper.toDomain(entity.getGasExtMethod()))
        .modelUsed(entity.getModUsed())
        .dateTimeCreated(MapperConstants.toStringDate(entity.getDtCreated()))
        .dateTimeModified(MapperConstants.toStringDate(entity.getDtModified()))
        .userCreated(entity.getUserCreated())
        .userModified(entity.getUserUpdated())
        .build();
  }

  /**
   * Converts the domain object into an ORM.
   *
   * @param domain the domain object for the Observation data.
   * @return the Observation ORM.
   */
  public ObservationEntity toEntity(Observation domain) {
    return ObservationEntity.builder()
        .obsId(Long.parseLong(domain.observationId()))
        .sampleCond(domain.sampleCondition())
        .gasExtMethod(mapper.toEntity(domain.gasExtractionMethod()))
        .modUsed(domain.modelUsed())
        .build();
  }
}
