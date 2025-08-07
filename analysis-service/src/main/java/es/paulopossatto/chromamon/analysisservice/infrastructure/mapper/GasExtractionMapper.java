package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import es.paulopossatto.chromamon.analysisservice.domain.model.GasExtractionMethod;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.GasExtractionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** Mapper interface for the Gas Extraction Method. */
@Mapper(componentModel = "spring", uses = ConstantsMapper.class)
public interface GasExtractionMapper {

  /**
   * Converts the ORM to a domain Gas Extraction object.
   *
   * @param entity the Gas Extraction ORM.
   * @return the Gas Extraction domain object.
   */
  @Mapping(source = "gasExtMethodId", target = "gasExtractionId")
  @Mapping(source = "gasExtMethodTypo", target = "gasExtractionTypo")
  @Mapping(source = "gasExtMethodLiteral", target = "gasExtractionLiteral")
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
  GasExtractionMethod toDomain(GasExtractionEntity entity);

  /**
   * Converts the domain object into a Gas Extraction ORM.
   *
   * @param domain the Gas Extraction domain object.
   * @return the Gas Extraction ORM.
   */
  @Mapping(source = "gasExtractionId", target = "gasExtMethodId")
  @Mapping(source = "gasExtractionTypo", target = "gasExtMethodTypo")
  @Mapping(source = "gasExtractionLiteral", target = "gasExtMethodLiteral")
  GasExtractionEntity toEntity(GasExtractionMethod domain);
}
