package es.paulopossatto.chromamon.analysisservice.constants.unit;

import es.paulopossatto.chromamon.analysisservice.application.dto.response.AnalysesResponses;
import es.paulopossatto.chromamon.analysisservice.domain.model.Analysis;
import es.paulopossatto.chromamon.analysisservice.domain.model.Chromatography;
import es.paulopossatto.chromamon.analysisservice.domain.model.Observation;
import java.util.Collections;

/** Stores all the responses to mock for the unit tests. */
public class AnalysesMother {

  /**
   * Set an empty list as result of the analyses.
   *
   * @return the response DTO.
   */
  public static AnalysesResponses emptyListResponse() {
    return AnalysesResponses.builder().analyses(Collections.emptyList()).build();
  }

  /**
   * Set an Analysis domain object.
   *
   * @return the Analysis domain object.
   */
  public static Analysis analysisDomainObjectFilled() {
    return Analysis.builder()
        .transformerSerialNumber("TRF-132kV-2023-ABC123")
        .analysisDate("25-06-2025 19:17:38")
        .labAnalysisDate("26-06-2025 09:15:11")
        .chromatography(
            Chromatography.builder()
                .hydrogen("0.15")
                .methane("0.21")
                .ethane("0.09")
                .acetylene("0.11")
                .carbonMonoxide("4.45")
                .carbonDioxide("995.41")
                .build())
        .oilType("Mineral Oil")
        .observation(
            Observation.builder()
                .sampleCondition("Presence of particles")
                .extractionMethod("Vacuum")
                .modelUsed("GC Agilent 7890B")
                .build())
        .furfuralAnalysis("0.50")
        .oilHumidity("15.00")
        .build();
  }
}
