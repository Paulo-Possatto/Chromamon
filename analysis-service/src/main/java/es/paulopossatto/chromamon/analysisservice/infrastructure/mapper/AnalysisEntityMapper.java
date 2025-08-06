package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import es.paulopossatto.chromamon.analysisservice.domain.model.Analysis;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.AnalysisEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/** The mapper that converts domain object to entity and vice versa. */
@Component
@RequiredArgsConstructor
@Slf4j
public class AnalysisEntityMapper {

  private final ChromatographyEntityMapper chromatographyMapper;
  private final ObservationEntityMapper observationMapper;
  private final OilTypeEntityMapper oilTypeMapper;

  /**
   * Converts the ORM to a domain-related object.
   *
   * @param entity the ORM of the analysis data.
   * @return the domain object.
   */
  public Analysis toDomain(AnalysisEntity entity) {
    log.info("Converting to Analysis Domain: {}", entity.getIdentifier());
    return Analysis.builder()
        .id(entity.getAnId().toString())
        .identifier(entity.getIdentifier())
        .transformerSerialNumber(entity.getTransSerNum())
        .analysisDatetime(MapperConstants.toStringDate(entity.getAnalysisDateTime()))
        .labAnalysisDateTime(MapperConstants.toStringDate(entity.getLabAnalysisDateTime()))
        .chromatography(chromatographyMapper.toDomain(entity.getChromatography()))
        .oilType(oilTypeMapper.toDomain(entity.getOilType()))
        .observation(observationMapper.toDomain(entity.getObservation()))
        .furfuralAnalysis(MapperConstants.toStringNumber(entity.getFurfuralAnalysis()))
        .oilHumidity(MapperConstants.toStringNumber(entity.getOilHumidity()))
        .dateTimeCreated(MapperConstants.toStringDate(entity.getDtCreated()))
        .dateTimeModified(MapperConstants.toStringDate(entity.getDtModified()))
        .userCreated(entity.getUserCreated())
        .userModified(entity.getUserUpdated())
        .build();
  }

  /**
   * Converts the Analysis domain object to an ORM.
   *
   * @param domain the Analysis domain object.
   * @return the ORM.
   */
  public AnalysisEntity toEntity(Analysis domain) {
    log.info("Converting to Analysis Entity: {}", domain.identifier());
    return AnalysisEntity.builder()
        .anId(Long.parseLong(domain.id()))
        .identifier(domain.identifier())
        .transSerNum(domain.transformerSerialNumber())
        .analysisDateTime(MapperConstants.toOffsetDateTime(domain.analysisDatetime()))
        .labAnalysisDateTime(MapperConstants.toOffsetDateTime(domain.labAnalysisDateTime()))
        .chromatography(chromatographyMapper.toEntity(domain.chromatography()))
        .oilType(oilTypeMapper.toEntity(domain.oilType()))
        .observation(observationMapper.toEntity(domain.observation()))
        .furfuralAnalysis(MapperConstants.toBigDecimal(domain.furfuralAnalysis()))
        .oilHumidity(MapperConstants.toBigDecimal(domain.oilHumidity()))
        .build();
  }
}
