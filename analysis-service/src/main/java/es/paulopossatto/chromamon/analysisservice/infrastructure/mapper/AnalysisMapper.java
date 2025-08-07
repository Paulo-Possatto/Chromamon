package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import es.paulopossatto.chromamon.analysisservice.domain.model.Analysis;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.AnalysisEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** Mapper to convert the Analysis object. */
@Mapper(
    componentModel = "spring",
    uses = {
      ChromatographyMapper.class,
      OilTypeMapper.class,
      ObservationMapper.class,
      ConstantsMapper.class
    })
public interface AnalysisMapper {

  /**
   * Convert the Analysis ORM into an Analysis domain object.
   *
   * @param entity the Analysis ORM.
   * @return the Analysis domain object.
   */
  @Mapping(target = "id", source = "anId")
  @Mapping(target = "identifier", source = "identifier")
  @Mapping(target = "transformerSerialNumber", source = "transSerNum")
  @Mapping(
      target = "analysisDatetime",
      source = "analysisDateTime",
      qualifiedByName = "mapOffsetDateTimeToString")
  @Mapping(
      target = "labAnalysisDateTime",
      source = "labAnalysisDateTime",
      qualifiedByName = "mapOffsetDateTimeToString")
  @Mapping(target = "chromatography", source = "chromatography")
  @Mapping(target = "oilType", source = "oilType")
  @Mapping(target = "observation", source = "observation")
  @Mapping(target = "furfuralAnalysis", source = "furfuralAnalysis")
  @Mapping(target = "oilHumidity", source = "oilHumidity")
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
  Analysis toDomain(AnalysisEntity entity);

  /**
   * Convert the Analysis domain object into an Analysis ORM.
   *
   * @param domain the Analysis domain object.
   * @return the Analysis ORM.
   */
  @Mapping(target = "anId", source = "id")
  @Mapping(target = "identifier", source = "identifier")
  @Mapping(target = "transSerNum", source = "transformerSerialNumber")
  @Mapping(
      target = "analysisDateTime",
      source = "analysisDatetime",
      qualifiedByName = "stringToOffsetDateTime")
  @Mapping(
      target = "labAnalysisDateTime",
      source = "labAnalysisDateTime",
      qualifiedByName = "stringToOffsetDateTime")
  @Mapping(target = "chromatography", source = "chromatography")
  @Mapping(target = "oilType", source = "oilType")
  @Mapping(target = "observation", source = "observation")
  @Mapping(target = "furfuralAnalysis", source = "furfuralAnalysis")
  @Mapping(target = "oilHumidity", source = "oilHumidity")
  AnalysisEntity toEntity(Analysis domain);
}
