package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import es.paulopossatto.chromamon.analysisservice.domain.model.Chromatography;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.ChromatographyEntity;
import org.springframework.stereotype.Component;

/** The mapper that converts domain object to entity and vice versa. */
@Component
public class ChromatographyEntityMapper {

  /**
   * Converts the ORM into a domain object.
   *
   * @param entity the ORM.
   * @return the domain object.
   */
  public Chromatography toDomain(ChromatographyEntity entity) {
    return Chromatography.builder()
        .id(entity.getChrId().toString())
        .hydrogen(MapperConstants.toStringNumber(entity.getHydrogen()))
        .methane(MapperConstants.toStringNumber(entity.getMethane()))
        .ethane(MapperConstants.toStringNumber(entity.getEthane()))
        .acetylene(MapperConstants.toStringNumber(entity.getAcetylene()))
        .carbonMonoxide(MapperConstants.toStringNumber(entity.getCarbonMonoxide()))
        .carbonDioxide(MapperConstants.toStringNumber(entity.getCarbonDioxide()))
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
  public ChromatographyEntity toEntity(Chromatography domain) {
    return ChromatographyEntity.builder()
        .chrId(Long.parseLong(domain.id()))
        .hydrogen(MapperConstants.toBigDecimal(domain.hydrogen()))
        .methane(MapperConstants.toBigDecimal(domain.methane()))
        .ethane(MapperConstants.toBigDecimal(domain.ethane()))
        .acetylene(MapperConstants.toBigDecimal(domain.acetylene()))
        .carbonMonoxide(MapperConstants.toBigDecimal(domain.carbonMonoxide()))
        .carbonDioxide(MapperConstants.toBigDecimal(domain.carbonDioxide()))
        .build();
  }
}
