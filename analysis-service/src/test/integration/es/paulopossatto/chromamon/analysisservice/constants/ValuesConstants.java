package es.paulopossatto.chromamon.analysisservice.constants;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ValuesConstants {

  public static final String API_VERSION_HEADER_NAME = "X-API-Version";

  public static final String API_VERSION_HEADER_VALUE_V1 = "v1";

  public static final String BAD_API_VERSION_VALUE = "abc123";

  public static final String EXISTING_SERIAL_NUMBER = "TRF-132kV-2023-ABC123";

  public static final String NON_EXISTING_SERIAL_NUMBER = "ABC-132kV-1994-TRF123";

  public static final String EXISTING_IDENTIFIER = "AN_0000000001";

  public static final String NON_EXISTING_IDENTIFIER = "AN_9999999999";

  public static final OffsetDateTime START_DATE_WITH_ANAYLSES = OffsetDateTime.of(
      2025, 6, 25, 0, 0, 0, 0, ZoneOffset.ofHours(2)
  );

  public static final OffsetDateTime END_DATE_WITH_ANAYLSES = OffsetDateTime.of(
      2025, 6, 26, 23, 59, 59, 999, ZoneOffset.ofHours(2)
  );

  public static final OffsetDateTime START_DATE_WITHOUT_ANAYLSES = OffsetDateTime.of(
      2022, 6, 25, 0, 0, 0, 0, ZoneOffset.ofHours(2)
  );

  public static final OffsetDateTime END_DATE_WITHOUT_ANAYLSES = OffsetDateTime.of(
      2022, 6, 26, 23, 59, 59, 999, ZoneOffset.ofHours(2)
  );

  public static final String STRING_DATE_TIME = "25-06-2025 17:19:38";

  public static final OffsetDateTime OFFSET_DATE_TIME = OffsetDateTime.of(
      2025, 6, 25, 17, 19, 38, 0,  ZoneOffset.ofHours(2)
  );

  public static final BigDecimal BIG_DECIMAL_NUMBER =  new BigDecimal(
      "12.34"
  );

  public static final String STRING_DECIMAL_NUMBER = "12.34";

}
