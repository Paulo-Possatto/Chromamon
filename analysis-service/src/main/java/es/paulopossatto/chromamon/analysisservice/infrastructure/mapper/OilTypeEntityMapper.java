package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import es.paulopossatto.chromamon.analysisservice.domain.model.OilType;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.OilTypesEntity;
import org.springframework.stereotype.Component;

/** Mapper for the Oil Type object. */
@Component
public class OilTypeEntityMapper {

  /**
   * Converts the ORM into a doamin object.
   *
   * @param entity the ORM.
   * @return the domain object.
   */
  public OilType toDomain(OilTypesEntity entity) {
    return OilType.builder()
        .oilTypeId(entity.getOilTypeId().toString())
        .oilTypeTypo(entity.getOilTypeTypo())
        .oilTypeLiteral(entity.getOilTypeLiteral())
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
   * @return the OilType ORM.
   */
  public OilTypesEntity toEntity(OilType domain) {
    return OilTypesEntity.builder()
        .oilTypeId(Long.parseLong(domain.oilTypeId()))
        .oilTypeTypo(domain.oilTypeTypo().trim().toUpperCase())
        .oilTypeLiteral(domain.oilTypeLiteral())
        .build();
  }
}
