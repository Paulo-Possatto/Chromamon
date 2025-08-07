package es.paulopossatto.chromamon.analysisservice.domain.model;

import lombok.Builder;

/**
 * The domain object for the Observation data.
 *
 * @param observationId the ID of the observation.
 * @param sampleCondition the condition of the sample when extracted.
 * @param gasExtractionMethod the Gas Extraction method object.
 * @param modelUsed the model of the tool used to extract the sample.
 * @param dateTimeCreated the date-time in which the data was stored.
 * @param dateTimeModified the date-time in which the data was modified.
 * @param userCreated the user who added the data.
 * @param userModified the user who last modified the data.
 */
@Builder
public record Observation(
    String observationId,
    String sampleCondition,
    GasExtractionMethod gasExtractionMethod,
    String modelUsed,
    String dateTimeCreated,
    String dateTimeModified,
    String userCreated,
    String userModified) {}
