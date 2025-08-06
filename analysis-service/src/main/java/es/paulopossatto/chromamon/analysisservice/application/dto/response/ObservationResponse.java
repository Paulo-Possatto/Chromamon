package es.paulopossatto.chromamon.analysisservice.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

/**
 * The DTO of the Observation response.
 *
 * @param sampleCondition information about the sample at the day it was extracted.
 * @param gasExtractionMethod the way used to extract the sample.
 * @param modelUsed the model of the tool used to gather the sample.
 * @param createdDateTime the date and time in which the information was stored in the database.
 */
@Builder
@Schema(
    name = "Observation-Response",
    description = "The object that contains information about the observations while analysing",
    type = "object")
public record ObservationResponse(
    @Schema(
            description = "The description of the sample condition",
            example = "Presence of particles",
            type = "string")
        String sampleCondition,
    @Schema(
            description = "The method in which the gas was extracted",
            example = "Vacuum",
            type = "string")
        String gasExtractionMethod,
    @Schema(
            description = "The model of the tool used to extract the oil for the analysis",
            example = "GC Agilent 7890B",
            type = "string")
        String modelUsed,
    @Schema(
            description = "The date-time when the data was inserted in the database",
            example = "22-07-2025 15:43:25",
            type = "string")
        String createdDateTime,
    @Schema(
            description = "The date-time when the data was last updated in the database",
            example = "22-07-2025 15:43:25",
            type = "string")
        String lastUpdateDateTime,
    @Schema(
            description = "The subject that created the data, took from the from the JWT token",
            example = "AA00AA",
            type = "string")
        String userCreated,
    @Schema(
            description =
                "The subject that last updated the data, took from the from the JWT token",
            example = "AA00AA",
            type = "string")
        String userLastUpdated) {}
