package es.paulopossatto.chromamon.analysisservice.application.dto.response;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;

/**
 * The DTO response for the Analyses.
 *
 * @param analyses a list of the AnalysisResponse DTO.
 * @param currentPage the current page of content in the request.
 * @param totalItems the total amount of items.
 * @param totalPages the total amount of pages.
 */
@Builder
@Schema(
    name = "Analyses-Responses",
    description = "The response object that contains a list of all analyses",
    type = "object")
public record AnalysesResponses(
    @ArraySchema(schema = @Schema(implementation = AnalysisResponse.class))
        List<AnalysisResponse> analyses,
    @Schema(description = "The selected page of contents", example = "1", type = "integer")
        int currentPage,
    @Schema(description = "The amount of items in the page", example = "3", type = "number")
        long totalItems,
    @Schema(
            description = "The total number of pages for all the elements",
            example = "5",
            type = "integer")
        int totalPages) {}
