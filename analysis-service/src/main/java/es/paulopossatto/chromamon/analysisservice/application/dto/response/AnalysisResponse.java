package es.paulopossatto.chromamon.analysisservice.application.dto.response;

import es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers.constants.SwaggerType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(
    name = "Analysis-Response",
    description = "The object that contains the information about a single analysis",
    type = SwaggerType.OBJECT)
public record AnalysisResponse(
    @Schema(
            description =
                "The identifier of the analysis, starting with 'AN_' followed by 10 numbers",
            example = "AN_5168350147",
            type = SwaggerType.STRING)
        String identifier,
    @Schema(
            description = "The serial number of the transformer that was analysed",
            example = "TRF-132kV-2023-ABC123",
            type = SwaggerType.STRING)
        String transformerSerialNumber,
    @Schema(
            description = "The date and time the analysis was carried out",
            example = "25-06-2025 17:19:38",
            type = SwaggerType.STRING)
        String analysisDateTime,
    @Schema(
            description = "The time the analysis was started in the laboratory",
            example = "26-06-2025 09:15:11",
            type = SwaggerType.STRING)
        String labAnalysisDateTime,
    @Schema(implementation = ChromatographyResponse.class, type = SwaggerType.OBJECT)
        ChromatographyResponse chromatography,
    @Schema(
            description = "The type of isolating oil used in the transformer",
            example = "Mineral Oil",
            type = SwaggerType.STRING)
        String oilType,
    @Schema(implementation = ObservationResponse.class, type = SwaggerType.OBJECT)
        ObservationResponse observation,
    @Schema(
            description =
                "The value that determines the condition of the isolating paper (value in mg/L)",
            example = "0.15",
            type = SwaggerType.STRING)
        String furfuralAnalysis,
    @Schema(
            description = "The percentage of humidity detected in the isolating oil",
            example = "15.00",
            type = SwaggerType.STRING)
        String oilHumidity,
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
