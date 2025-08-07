package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

/** Constants that are used by the mappers. */
@Component
public class MapperConstants {

  /** The format used when it's date-time param. */
  public static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

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
