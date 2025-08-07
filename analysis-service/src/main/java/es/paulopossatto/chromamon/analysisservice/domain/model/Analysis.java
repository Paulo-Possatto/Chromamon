package es.paulopossatto.chromamon.analysisservice.domain.model;

import lombok.Builder;

/**
 * The domain object for the analysis data.
 *
 * @param id the analysis ID.
 * @param identifier the identifier for the analysis generated in the database.
 * @param transformerSerialNumber the serial number of the transformer analysed.
 * @param analysisDatetime the date-time in which the analysis took place.
 * @param labAnalysisDateTime the date-time if when the sample arrived in the laboratory.
 * @param chromatography the Chromatography data.
 * @param oilType the Oil Type data.
 * @param observation the Observation data.
 * @param furfuralAnalysis the furfural analysis value (in ppm) to verify the degradation of the
 *     isolating paper.
 * @param oilHumidity the percentage of moisture found in the sample.
 * @param dateTimeCreated the date-time in which the data was stored.
 * @param dateTimeModified the date-time in which the data was modified.
 * @param userCreated the user who added the data.
 * @param userModified the user who last modified the data.
 */
@Builder
public record Analysis(
    String id,
    String identifier,
    String transformerSerialNumber,
    String analysisDatetime,
    String labAnalysisDateTime,
    Chromatography chromatography,
    OilType oilType,
    Observation observation,
    String furfuralAnalysis,
    String oilHumidity,
    String dateTimeCreated,
    String dateTimeModified,
    String userCreated,
    String userModified) {}
