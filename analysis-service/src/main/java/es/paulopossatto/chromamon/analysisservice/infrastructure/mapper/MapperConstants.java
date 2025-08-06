package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/** Constants that are used by the mappers. */
public class MapperConstants {

  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

  /**
   * Converts the date in string format to an OffsetDateTime value.
   *
   * @param date the String date.
   * @return the OffsetDateTime value.
   */
  public static OffsetDateTime toOffsetDateTime(String date) {
    return OffsetDateTime.parse(date, FORMATTER);
  }

  /**
   * Converts an OffsetDateTime to a String value.
   *
   * @param date the OffsetDateTime.
   * @return the String value.
   */
  public static String toStringDate(OffsetDateTime date) {
    return date.format(FORMATTER);
  }

  /**
   * Converts a String number value to an BigDecimal value.
   *
   * @param value the String value.
   * @return an BigDecimal value.
   */
  public static BigDecimal toBigDecimal(String value) {
    return new BigDecimal(value);
  }

  /**
   * Converts an BigDecimal value to a String value with 2 decimal digits for precision.
   *
   * @param value the BigDecimal value.
   * @return a String value.
   */
  public static String toStringNumber(BigDecimal value) {
    DecimalFormat format = new DecimalFormat();
    format.setMinimumFractionDigits(2);
    format.setMaximumFractionDigits(2);
    return format.format(value);
  }
}
