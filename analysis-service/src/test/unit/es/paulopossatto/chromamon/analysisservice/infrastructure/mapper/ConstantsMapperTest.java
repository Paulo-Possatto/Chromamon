package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.BIG_DECIMAL_NUMBER;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.OFFSET_DATE_TIME;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.STRING_DATE_TIME;
import static es.paulopossatto.chromamon.analysisservice.constants.ValuesConstants.STRING_DECIMAL_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the ConstantsMapper.
 */
@SpringBootTest
@ActiveProfiles("integration-test")
@Tag("integration")
public class ConstantsMapperTest {

  @Autowired
  private ConstantsMapper mapper;

  @Test
  @DisplayName("""
      GIVEN an string date time
      WHEN the map method is called
      THEN should return the OffsetDateTime value
      """)
  void whenMapMethodIsCalled_thenShouldReturnTheOffsetDateTime() {
    // ARRANGE & ACT
    OffsetDateTime odt = mapper.map(STRING_DATE_TIME);

    // ASSERT
    assertEquals(OFFSET_DATE_TIME, odt);
  }

  @Test
  @DisplayName("""
      GIVEN an OffsetDateTime value
      WHEN the map method is called
      THEN should return the date time value
      """)
  void whenMapMethodIsCalled_thenShouldReturnTheDateTime() {
    // ARRANGE & ACT
    String dt = mapper.map(OFFSET_DATE_TIME);

    // ASSERT
    assertEquals(STRING_DATE_TIME, dt);
  }

  @Test
  @DisplayName("""
      GIVEN an BigDecimal value
      WHEN the map method is called
      THEN should return the String value of the number
      """)
  void whenMapMethodIsCalled_thenShouldReturnTheStringNumberValue() {
    // ARRANGE & ACT
    String result = mapper.map(BIG_DECIMAL_NUMBER);

    // ASSERT
    assertEquals(STRING_DECIMAL_NUMBER, result);
  }

}
