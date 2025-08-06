package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import es.paulopossatto.chromamon.analysisservice.domain.model.GasExtractionMethod;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.GasExtractionEntity;
import org.springframework.stereotype.Component;

/** The mapper for the Gas Extraction Method. */
@Component
public class GasExtractionEntityMapper {

  /**
   * Converts the ORM into a domain object.
   *
   * @param entity the ORM.
   * @return the domain object.
   */
  public GasExtractionMethod toDomain(GasExtractionEntity entity) {
    return GasExtractionMethod.builder()
        .gasExtractionId(entity.getGasExtMethodId().toString())
        .gasExtractionTypo(entity.getGasExtMethodTypo())
        .gasExtractionLiteral(entity.getGasExtMethodLiteral())
        .dateTimeCreated(MapperConstants.toStringDate(entity.getDtCreated()))
        .dateTimeModified(MapperConstants.toStringDate(entity.getDtModified()))
        .userCreated(entity.getUserCreated())
        .userModified(entity.getUserUpdated())
        .build();
  }

  /**
   * Converts a domain object into an ORM.
   *
   * @param domain the domain object.
   * @return the ORM.
   */
  public GasExtractionEntity toEntity(GasExtractionMethod domain) {
    return GasExtractionEntity.builder()
        .gasExtMethodId(Long.parseLong(domain.gasExtractionId()))
        .gasExtMethodTypo(domain.gasExtractionTypo().trim().toUpperCase())
        .gasExtMethodLiteral(domain.gasExtractionLiteral())
        .build();
  }
}
