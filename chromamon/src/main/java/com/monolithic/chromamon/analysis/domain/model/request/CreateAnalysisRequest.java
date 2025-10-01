package com.monolithic.chromamon.analysis.domain.model.request;

import com.monolithic.chromamon.analysis.domain.model.Analysis;
import com.monolithic.chromamon.analysis.domain.model.AnalysisStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;

/**
 * The request body for adding new analysis data to the database.
 *
 * @param analysisDate when the analysis was made.
 * @param sampleDate when the sample was extracted.
 * @param laboratory the laboratory that made the analysis.
 * @param method the method used in the analysis.
 * @param sampleTemperatureCelsius the sample temperature in degrees celsius.
 * @param hydrogenH2Ppm the concentration of hydrogen gas in the sample in parts per million (PPM).
 * @param methaneCh4Ppm the concentration of methane gas in the sample in parts per million (PPM).
 * @param acetyleneC2h2ppm the concentration of acetylene gas in the sample in parts per million
 *     (PPM).
 * @param ethyleneC2h4Ppm the concentration of ethylene gas in the sample in parts per million
 *     (PPM).
 * @param ethaneC2h6Ppm the concentration of ethane gas in the sample in parts per million (PPM).
 * @param carbonMonoxideCoPpm the concentration of carbon monoxide gas in the sample in parts per
 *     million (PPM).
 * @param carbonDioxideCo2Ppm the concentration of carbon dioxide gas in the sample in parts per
 *     million (PPM).
 * @param oxygenO2Ppm the concentration of oxygen gas in the sample in parts per million (PPM).
 * @param nitrogenN2Ppm the concentration of nitrogen gas in the sample in parts per million (PPM).
 * @param totalDissolvedGasTdgPpm the total amount of gases dissolved in the sample.
 * @param totalCombustibleGasTcgPpm the total amount of combustible gases in the sample.
 * @param observations observations about the oil sample.
 * @param sampleCondition the condition of the sample.
 */
@Builder
public record CreateAnalysisRequest(
    Long transformerId,
    LocalDate analysisDate,
    LocalDate sampleDate,
    String laboratory,
    String method,
    BigDecimal sampleTemperatureCelsius,
    BigDecimal hydrogenH2Ppm,
    BigDecimal methaneCh4Ppm,
    BigDecimal acetyleneC2h2ppm,
    BigDecimal ethyleneC2h4Ppm,
    BigDecimal ethaneC2h6Ppm,
    BigDecimal carbonMonoxideCoPpm,
    BigDecimal carbonDioxideCo2Ppm,
    BigDecimal oxygenO2Ppm,
    BigDecimal nitrogenN2Ppm,
    BigDecimal totalDissolvedGasTdgPpm,
    BigDecimal totalCombustibleGasTcgPpm,
    String observations,
    String sampleCondition) {

  public Analysis toAnalysis() {
    return Analysis.builder()
        .transformerId(transformerId)
        .analysisDate(analysisDate)
        .sampleDate(sampleDate)
        .laboratory(laboratory)
        .method(method)
        .sampleTemperatureCelsius(sampleTemperatureCelsius)
        .hydrogenH2Ppm(hydrogenH2Ppm)
        .methaneCh4Ppm(methaneCh4Ppm)
        .acetyleneC2h2Ppm(acetyleneC2h2ppm)
        .ethyleneC2h4Ppm(ethyleneC2h4Ppm)
        .ethaneC2h6Ppm(ethaneC2h6Ppm)
        .carbonMonoxideCoPpm(carbonMonoxideCoPpm)
        .carbonDioxideCo2Ppm(carbonDioxideCo2Ppm)
        .oxygenO2Ppm(oxygenO2Ppm)
        .nitrogenN2Ppm(nitrogenN2Ppm)
        .totalDissolvedGasTdgPpm(totalDissolvedGasTdgPpm)
        .totalCombustibleGasTcgPpm(totalCombustibleGasTcgPpm)
        .observations(observations)
        .sampleCondition(sampleCondition)
        .status(AnalysisStatus.PENDING)
        .build();
  }
}
