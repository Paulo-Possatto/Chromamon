package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import static es.paulopossatto.chromamon.analysisservice.infrastructure.mapper.MapperConstants.FORMATTER;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
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
    if (value == null) {
      return null;
    }
    try {
      LocalDateTime ldt = LocalDateTime.parse(value, FORMATTER);
      ZoneOffset offset = ZonedDateTime.now().getOffset();
      return ldt.atOffset(offset);
    } catch (DateTimeParseException e2) {
      throw new IllegalArgumentException("Invalid date format: " + value, e2);
    }
  }

  /**
   * Converts the OffsetDateTime into a String value.
   *
   * @param value the OffsetDateTime value.
   * @return the String value.
   */
  @Named("mapOffsetDateTimeToString")
  default String map(OffsetDateTime value) {
    return value != null ? value.format(FORMATTER) : null;
  }

  /**
   * Converts an BigDecimal value to a String value with 2 decimal digits for precision.
   *
   * @param value the BigDecimal value.
   * @return a String value.
   */
  @Named("mapBigDecimalToString")
  default String map(BigDecimal value) {
    return value != null ? MapperConstants.toStringNumber(value) : null;
  }

  /**
   * Converts a String number value to an BigDecimal value.
   *
   * @param value the String value.
   * @return an BigDecimal value.
   */
  @Named("mapStringNumberToBigDecimal")
  default BigDecimal toBigDecimal(String value) {
    return value != null ? new BigDecimal(value) : null;
  }
}
