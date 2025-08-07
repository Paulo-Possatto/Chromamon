package es.paulopossatto.chromamon.analysisservice.domain.model;

import lombok.Builder;

/**
 * The domain object of the Chromatography data.
 *
 * @param id the chromatography ID.
 * @param hydrogen the value (in ppm) of the hydrogen gas concentration.
 * @param methane the value (in ppm) of the methane gas concentration.
 * @param ethane the value (in ppm) of the ethane gas concentration.
 * @param acetylene the value (in ppm) of the acetylene gas concentration.
 * @param carbonMonoxide the value (in ppm) of the carbon monoxide gas concentration.
 * @param carbonDioxide the value (in ppm) of the carbon dioxide gas concentration.
 * @param dateTimeCreated the date-time in which the data was stored.
 * @param dateTimeModified the date-time in which the data was modified.
 * @param userCreated the user who added the data.
 * @param userModified the user who last modified the data.
 */
@Builder
public record Chromatography(
    String id,
    String hydrogen,
    String methane,
    String ethane,
    String acetylene,
    String carbonMonoxide,
    String carbonDioxide,
    String dateTimeCreated,
    String dateTimeModified,
    String userCreated,
    String userModified) {}
