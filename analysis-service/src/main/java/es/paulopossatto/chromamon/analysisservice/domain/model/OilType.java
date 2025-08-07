package es.paulopossatto.chromamon.analysisservice.domain.model;

import lombok.Builder;

/**
 * The Oil Type parameters used after converted into a domain object.
 *
 * @param oilTypeId the ID of the oil type.
 * @param oilTypeTypo the unformatted name of the oil type.
 * @param oilTypeLiteral the correct name of the oil type.
 * @param dateTimeCreated the date-time in which the data was stored.
 * @param dateTimeModified the date-time in which the data was modified.
 * @param userCreated the user who added the data.
 * @param userModified the user who last modified the data.
 */
@Builder
public record OilType(
    String oilTypeId,
    String oilTypeTypo,
    String oilTypeLiteral,
    String dateTimeCreated,
    String dateTimeModified,
    String userCreated,
    String userModified) {}
