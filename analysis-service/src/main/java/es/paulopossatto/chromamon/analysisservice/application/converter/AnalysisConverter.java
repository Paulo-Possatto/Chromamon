package es.paulopossatto.chromamon.analysisservice.application.converter;

import es.paulopossatto.chromamon.analysisservice.application.dto.response.AnalysesResponses;
import es.paulopossatto.chromamon.analysisservice.application.dto.response.AnalysisResponse;
import es.paulopossatto.chromamon.analysisservice.application.dto.response.ChromatographyResponse;
import es.paulopossatto.chromamon.analysisservice.application.dto.response.ObservationResponse;
import es.paulopossatto.chromamon.analysisservice.domain.model.Analysis;
import es.paulopossatto.chromamon.analysisservice.domain.model.Chromatography;
import es.paulopossatto.chromamon.analysisservice.domain.model.Observation;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/** Converter the for the analysis domain and response objects. */
@Component
public class AnalysisConverter {

  /**
   * Converts the Analysis domain object (paginated) into a AnalysesResponses DTO.
   *
   * @param analysisPage the page of the analysis domain object.
   * @return the AnalysesResponses DTO.
   */
  public AnalysesResponses toAnalysesResponse(Page<Analysis> analysisPage) {
    return AnalysesResponses.builder()
        .analyses(analysisPage.map(this::toAnalysisResponse).getContent())
        .currentPage(analysisPage.getNumber())
        .totalItems(analysisPage.getTotalElements())
        .totalPages(analysisPage.getTotalPages())
        .build();
  }

  /**
   * Converts the analysis domain object into a AnalysisResponse DTO.
   *
   * @param analysis the domain object.
   * @return the AnalysisResponse DTO.
   */
  private AnalysisResponse toAnalysisResponse(Analysis analysis) {
    return AnalysisResponse.builder()
        .identifier(analysis.identifier())
        .transformerSerialNumber(analysis.transformerSerialNumber())
        .analysisDateTime(analysis.analysisDatetime())
        .labAnalysisDateTime(analysis.labAnalysisDateTime())
        .chromatography(toChromatographyResponse(analysis.chromatography()))
        .oilType(analysis.oilType().oilTypeLiteral())
        .observation(toObservationResponse(analysis.observation()))
        .furfuralAnalysis(analysis.furfuralAnalysis())
        .oilHumidity(analysis.oilHumidity())
        .createdDateTime(analysis.dateTimeCreated())
        .lastUpdateDateTime(analysis.dateTimeModified())
        .userCreated(analysis.userCreated())
        .userLastUpdated(analysis.userModified())
        .build();
  }

  /**
   * Converts the domain Observation object into a ObservationResponse DTO.
   *
   * @param observation the domain object.
   * @return the ObservationResponse DTO.
   */
  private ObservationResponse toObservationResponse(Observation observation) {
    return ObservationResponse.builder()
        .sampleCondition(observation.sampleCondition())
        .gasExtractionMethod(observation.gasExtractionMethod().gasExtractionLiteral())
        .modelUsed(observation.modelUsed())
        .createdDateTime(observation.dateTimeCreated())
        .lastUpdateDateTime(observation.dateTimeModified())
        .userCreated(observation.userCreated())
        .userLastUpdated(observation.userModified())
        .build();
  }

  /**
   * Converts the Chromatography domain object into a ChromatographyResponse DTO.
   *
   * @param chromatography the domain object.
   * @return the ChromatographyResponse DTO.
   */
  private ChromatographyResponse toChromatographyResponse(Chromatography chromatography) {
    return ChromatographyResponse.builder()
        .hydrogen(chromatography.hydrogen())
        .methane(chromatography.methane())
        .ethane(chromatography.ethane())
        .acetylene(chromatography.acetylene())
        .carbonMonoxide(chromatography.carbonMonoxide())
        .carbonDioxide(chromatography.carbonDioxide())
        .createdDateTime(chromatography.dateTimeCreated())
        .lastUpdateDateTime(chromatography.dateTimeModified())
        .userCreated(chromatography.userCreated())
        .userLastUpdated(chromatography.userModified())
        .build();
  }
}
