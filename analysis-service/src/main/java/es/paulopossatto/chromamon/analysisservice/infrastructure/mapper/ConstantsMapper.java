package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import static es.paulopossatto.chromamon.analysisservice.infrastructure.mapper.MapperConstants.FORMATTER;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

/** Constants default methods used by the Mappers. */
@Mapper(componentModel = "spring")
public interface ConstantsMapper {

  /**
   * Converts the String date-time into an OffsetDateTime object.
   *
   * @param value the String date-time.
   * @return the OffsetDateTime object.
   */
  @Named("stringToOffsetDateTime")
  default OffsetDateTime map(String value) {
    return OffsetDateTime.parse(value, FORMATTER);
  }

  /**
   * Converts the OffsetDateTime into a String value.
   *
   * @param value the OffsetDateTime value.
   * @return the String value.
   */
  @Named("mapOffsetDateTimeToString")
  default String map(OffsetDateTime value) {
    return value.format(FORMATTER);
  }

  /**
   * Converts an BigDecimal value to a String value with 2 decimal digits for precision.
   *
   * @param value the BigDecimal value.
   * @return a String value.
   */
  @Named("mapBigDecimalToString")
  default String map(BigDecimal value) {
    return MapperConstants.toStringNumber(value);
  }

  /**
   * Converts a String number value to an BigDecimal value.
   *
   * @param value the String value.
   * @return an BigDecimal value.
   */
  @Named("mapStringNumberToBigDecimal")
  default BigDecimal toBigDecimal(String value) {
    return new BigDecimal(value);
  }
}
