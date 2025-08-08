package es.paulopossatto.chromamon.analysisservice.application.dto.response;

import es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers.constants.SwaggerType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

/**
 * The response DTO for the Chromatography.
 *
 * @param hydrogen the ppm value of hydrogen gas.
 * @param methane the ppm value of methane gas.
 * @param ethane the ppm value of ethane gas.
 * @param acetylene the ppm value of acetylene gas.
 * @param carbonMonoxide the ppm value of carbon monoxide gas.
 * @param carbonDioxide the ppm value of carbon dioxide gas.
 * @param createdDateTime the date and time in which the data was stored in the database.
 * @param lastUpdateDateTime the date and time in which the data was last modified in the database.
 * @param userCreated the user who added the data in the first time.
 * @param userLastUpdated the user who last modified the data.
 */
@Builder
@Schema(
    name = "Chromatography-Response",
    description = "The object that contains the chromatographic information",
    type = "object")
public record ChromatographyResponse(
    @Schema(
            description = "The concentration (in ppm) of hydrogen gas",
            example = "0.18",
            type = SwaggerType.STRING)
        String hydrogen,
    @Schema(
            description = "The concentration (in ppm) of methane gas",
            example = "0.28",
            type = SwaggerType.STRING)
        String methane,
    @Schema(
            description = "The concentration (in ppm) of ethane gas",
            example = "0.11",
            type = SwaggerType.STRING)
        String ethane,
    @Schema(
            description = "The concentration (in ppm) of acetylene gas",
            example = "0.15",
            type = SwaggerType.STRING)
        String acetylene,
    @Schema(
            description = "The concentration (in ppm) of carbon monoxide gas",
            example = "5.20",
            type = SwaggerType.STRING)
        String carbonMonoxide,
    @Schema(
            description = "The concentration (in ppm) of carbon dioxide gas",
            example = "980.75",
            type = SwaggerType.STRING)
        String carbonDioxide,
    @Schema(
            description = "The date-time when the data was inserted in the database",
            example = "22-07-2025 15:43:25",
            type = SwaggerType.STRING)
        String createdDateTime,
    @Schema(
            description = "The date-time when the data was last updated in the database",
            example = "22-07-2025 15:43:25",
            type = SwaggerType.STRING)
        String lastUpdateDateTime,
    @Schema(
            description = "The subject that created the data, took from the from the JWT token",
            example = "AA00AA",
            type = SwaggerType.STRING)
        String userCreated,
    @Schema(
            description =
                "The subject that last updated the data, took from the from the JWT token",
            example = "AA00AA",
            type = SwaggerType.STRING)
        String userLastUpdated) {}
