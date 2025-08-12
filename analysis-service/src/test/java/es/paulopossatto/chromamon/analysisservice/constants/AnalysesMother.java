package es.paulopossatto.chromamon.analysisservice.constants;

import es.paulopossatto.chromamon.analysisservice.application.dto.response.AnalysesResponses;
import es.paulopossatto.chromamon.analysisservice.domain.model.Analysis;
import es.paulopossatto.chromamon.analysisservice.domain.model.Chromatography;
import es.paulopossatto.chromamon.analysisservice.domain.model.GasExtractionMethod;
import es.paulopossatto.chromamon.analysisservice.domain.model.Observation;
import es.paulopossatto.chromamon.analysisservice.domain.model.OilType;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.AnalysisEntity;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.ChromatographyEntity;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.GasExtractionEntity;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.ObservationEntity;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.OilTypesEntity;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

/** Stores all the responses to mock for the unit tests. */
public class AnalysesMother {

  private static final String ANALYSIS_IDENTIFIER = "AN_0000000001";
  private static final String TRANSFORMER_SERIAL_NUMBER = "TRF-132kV-2023-ABC123";
  private static final String OIL_TYPE_TYPO = "MINERALOIL";
  private static final String OIL_TYPE_LITERAL = "Mineral Oil";
  private static final String SAMPLE_CONDITION = "Presence of particles";
  private static final String MODEL_USED = "GC Agilent 7890B";
  private static final String GAS_EXTRACTION_TYPO = "DIRECT";
  private static final String GAS_EXTRACTION_LITERAL = "Direct";

  private static final String DOMAIN_ANALYSIS_ID = "1";
  private static final String DOMAIN_ANALYSIS_IDENTIFIER = "AN_0000000001";
  private static final String DOMAIN_TRANSFORMER_SERIAL_NUMBER = "TRF-132kV-2023-ABC123";
  private static final String DOMAIN_ANALYSIS_DATE_TIME = "25-06-2025 17:19:38";
  private static final String DOMAIN_LAB_ANALYSIS_DATE_TIME = "26-06-2025 08:45:22";
  private static final String DOMAIN_CHROMATOGRAPHY_ID = "1";
  private static final String DOMAIN_HYDROGEN_PPM = "0.15";
  private static final String DOMAIN_METHANE_PPM = "0.21";
  private static final String DOMAIN_ETHANE_PPM = "0.09";
  private static final String DOMAIN_ACETYLENE_PPM = "0.11";
  private static final String DOMAIN_CARBON_MONOXIDE_PPM = "4.45";
  private static final String DOMAIN_CARBON_DIOXIDE_PPM = "915.41";
  private static final String DOMAIN_OIL_TYPE_ID = "1";
  private static final String DOMAIN_OIL_TYPE_TYPO = "MINERALOIL";
  private static final String DOMAIN_OIL_TYPE_LITERAL = "Mineral Oil";
  private static final String DOMAIN_OBSERVATION_ID = "1";
  private static final String DOMAIN_SAMPLE_CONDITION = "Presence of particles";
  private static final String DOMAIN_MODEL_USED = "GC Agilent 7890B";
  private static final String DOMAIN_GAS_EXTRACTION_ID = "1";
  private static final String DOMAIN_GAS_EXTRACTION_TYPO = "DIRECT";
  private static final String DOMAIN_GAS_EXTRACTION_LITERAL = "Direct";
  private static final String DOMAIN_FURFURAL_ANALYSIS = "0.50";
  private static final String DOMAIN_OIL_HUMIDITY = "15.00";

  private static final OffsetDateTime ANALYSIS_DATE_TIME =
      OffsetDateTime.of(2025, 6, 25, 17, 19, 38, 0, ZoneOffset.ofHours(2));
  private static final OffsetDateTime LAB_ANALYSIS_DATE_TIME =
      OffsetDateTime.of(2025, 6, 26, 8, 45, 22, 0, ZoneOffset.ofHours(2));

  private static final BigDecimal HYDROGEN_PPM = new BigDecimal("0.15");
  private static final BigDecimal METHANE_PPM = new BigDecimal("0.21");
  private static final BigDecimal ETHANE_PPM = new BigDecimal("0.09");
  private static final BigDecimal ACETYLENE_PPM = new BigDecimal("0.11");
  private static final BigDecimal CARBON_MONOXIDE_PPM = new BigDecimal("4.45");
  private static final BigDecimal CARBON_DIOXIDE_PPM = new BigDecimal("915.41");
  private static final BigDecimal FURFURAL_ANALYSIS = new BigDecimal("0.50");
  private static final BigDecimal OIL_HUMIDITY = new BigDecimal("15.00");

  /**
   * Set an empty list as result of the analyses.
   *
   * @return the response DTO.
   */
  public static AnalysesResponses emptyListResponse() {
    return AnalysesResponses.builder().analyses(Collections.emptyList()).build();
  }

  /**
   * Set an Analysis domain object for testing.
   *
   * @return the Analysis domain object.
   */
  public static Analysis analysisDomainObjectFilled() {
    return Analysis.builder()
        .id(DOMAIN_ANALYSIS_ID)
        .identifier(DOMAIN_ANALYSIS_IDENTIFIER)
        .transformerSerialNumber(DOMAIN_TRANSFORMER_SERIAL_NUMBER)
        .analysisDatetime(DOMAIN_ANALYSIS_DATE_TIME)
        .labAnalysisDateTime(DOMAIN_LAB_ANALYSIS_DATE_TIME)
        .chromatography(
            Chromatography.builder()
                .id(DOMAIN_CHROMATOGRAPHY_ID)
                .hydrogen(DOMAIN_HYDROGEN_PPM)
                .methane(DOMAIN_METHANE_PPM)
                .ethane(DOMAIN_ETHANE_PPM)
                .acetylene(DOMAIN_ACETYLENE_PPM)
                .carbonMonoxide(DOMAIN_CARBON_MONOXIDE_PPM)
                .carbonDioxide(DOMAIN_CARBON_DIOXIDE_PPM)
                .build())
        .oilType(
            OilType.builder()
                .oilTypeId(DOMAIN_OIL_TYPE_ID)
                .oilTypeTypo(DOMAIN_OIL_TYPE_TYPO)
                .oilTypeLiteral(DOMAIN_OIL_TYPE_LITERAL)
                .build())
        .observation(
            Observation.builder()
                .observationId(DOMAIN_OBSERVATION_ID)
                .sampleCondition(DOMAIN_SAMPLE_CONDITION)
                .modelUsed(DOMAIN_MODEL_USED)
                .gasExtractionMethod(
                    GasExtractionMethod.builder()
                        .gasExtractionId(DOMAIN_GAS_EXTRACTION_ID)
                        .gasExtractionTypo(DOMAIN_GAS_EXTRACTION_TYPO)
                        .gasExtractionLiteral(DOMAIN_GAS_EXTRACTION_LITERAL)
                        .build())
                .build())
        .furfuralAnalysis(DOMAIN_FURFURAL_ANALYSIS)
        .oilHumidity(DOMAIN_OIL_HUMIDITY)
        .build();
  }

  /**
   * Set an Analysis Entity object for testing.
   *
   * @return the Analysis Entity object.
   */
  public static AnalysisEntity analysisEntityObjectFilled() {
    return AnalysisEntity.builder()
        .anId(1L)
        .identifier(ANALYSIS_IDENTIFIER)
        .transSerNum(TRANSFORMER_SERIAL_NUMBER)
        .analysisDateTime(ANALYSIS_DATE_TIME)
        .labAnalysisDateTime(LAB_ANALYSIS_DATE_TIME)
        .chromatography(
            ChromatographyEntity.builder()
                .chrId(1L)
                .hydrogen(HYDROGEN_PPM)
                .methane(METHANE_PPM)
                .ethane(ETHANE_PPM)
                .acetylene(ACETYLENE_PPM)
                .carbonMonoxide(CARBON_MONOXIDE_PPM)
                .carbonDioxide(CARBON_DIOXIDE_PPM)
                .build())
        .oilType(
            OilTypesEntity.builder()
                .oilTypeId(1L)
                .oilTypeTypo(OIL_TYPE_TYPO)
                .oilTypeLiteral(OIL_TYPE_LITERAL)
                .build())
        .observation(
            ObservationEntity.builder()
                .obsId(1L)
                .sampleCond(SAMPLE_CONDITION)
                .gasExtMethod(
                    GasExtractionEntity.builder()
                        .gasExtMethodId(1L)
                        .gasExtMethodTypo(GAS_EXTRACTION_TYPO)
                        .gasExtMethodLiteral(GAS_EXTRACTION_LITERAL)
                        .build())
                .modUsed(MODEL_USED)
                .build())
        .furfuralAnalysis(FURFURAL_ANALYSIS)
        .oilHumidity(OIL_HUMIDITY)
        .build();
  }
}
