package es.paulopossatto.chromamon.analysisservice.domain.model;

import lombok.Builder;

/**
 * The Gas Extraction domain object.
 *
 * @param gasExtractionId the ID of the gas extraction method.
 * @param gasExtractionTypo the unformatted name of the gas extraction method.
 * @param gasExtractionLiteral the correct name of the gas extraction method.
 * @param dateTimeCreated the date-time in which the data was stored.
 * @param dateTimeModified the date-time in which the data was modified.
 * @param userCreated the user who added the data.
 * @param userModified the user who last modified the data.
 */
@Builder
public record GasExtractionMethod(
    String gasExtractionId,
    String gasExtractionTypo,
    String gasExtractionLiteral,
    String dateTimeCreated,
    String dateTimeModified,
    String userCreated,
    String userModified) {}
