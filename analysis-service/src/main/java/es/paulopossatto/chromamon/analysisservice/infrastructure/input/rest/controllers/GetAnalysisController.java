package es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers;

import es.paulopossatto.chromamon.analysisservice.application.dto.response.AnalysesResponses;
import es.paulopossatto.chromamon.analysisservice.application.dto.response.ErrorResponse;
import es.paulopossatto.chromamon.analysisservice.application.usecases.GetAnalysesUseCase;
import es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.annotations.api.version.ApiVersion;
import es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers.constants.ApiConstants;
import es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers.constants.ApiResponseExamples;
import es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers.constants.SwaggerType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** The controller for Get All Analyses. */
@RequiredArgsConstructor
@RestController
@RequestMapping(ApiConstants.ANALYSES_URI)
@Tag(name = "Get Analysis")
public class GetAnalysisController {

  private final GetAnalysesUseCase useCase;

  /**
   * Endpoint method to get all analyses.
   *
   * @param pageable the pageable interface.
   * @return a ResponseEntity for the AnalysesResponses record.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiVersion(ApiConstants.API_VERSION_1)
  @Operation(
      summary = "Get a list of all analyses",
      description =
          """
              Returns a list of all the analyses stored in the database.

              Roles allowed to access this resource:
              - **ADMIN**
              - **USER**
              - **ENGINEER**
              - **CHEMIST**
              - **LAB_ANALYST**
              """,
      parameters = {
        @Parameter(
            name = "page",
            in = ParameterIn.QUERY,
            description = "The page number",
            schema = @Schema(type = SwaggerType.INTEGER, defaultValue = "0")),
        @Parameter(
            name = "size",
            in = ParameterIn.QUERY,
            description = "Number of items per page",
            schema = @Schema(type = SwaggerType.INTEGER, defaultValue = "20")),
        @Parameter(
            name = "sort",
            in = ParameterIn.QUERY,
            description = "Sorting criteria",
            schema = @Schema(type = SwaggerType.STRING)),
        @Parameter(
            name = ApiConstants.VERSION_HEADER_NAME,
            required = true,
            in = ParameterIn.HEADER,
            description = "The version of the analyses API",
            schema = @Schema(type = SwaggerType.STRING)),
        @Parameter(
            name = ApiConstants.TOKEN_HEADER_NAME,
            required = true,
            in = ParameterIn.HEADER,
            description = "The JWT token used to access this resource",
            schema = @Schema(type = SwaggerType.STRING))
      },
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Give a page of analyses, the amount can be modified in the URI params",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AnalysesResponses.class),
                    examples = {
                      @ExampleObject(
                          name = "Analyses Response",
                          value = ApiResponseExamples.GET_ANALYSES_RESPONSE,
                          description = "The response object that lists all the analyses")
                    })),
        @ApiResponse(
            responseCode = "204",
            description = "No analyses are stored in the database, so nothing returned",
            content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(
            responseCode = "400",
            description = "Some user input that's not been validated by the system",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = {
                      @ExampleObject(
                          name = "Invalid Token Response",
                          value = ApiResponseExamples.INVALID_TOKEN_RESPONSE,
                          description = "The header parameter 'X-Chroma-Token' is invalid"),
                      @ExampleObject(
                          name = "Empty Token Response",
                          value = ApiResponseExamples.EMPTY_TOKEN_RESPONSE,
                          description =
                              "Header parameter 'X-Chroma-Token' is not present in the request"),
                      @ExampleObject(
                          name = "Empty API Version Response",
                          value = ApiResponseExamples.EMPTY_API_VERSION_RESPONSE,
                          description =
                              "The header parameter 'X-API-Version' is not present in the request"),
                      @ExampleObject(
                          name = "Invalid API Version Format Response",
                          value = ApiResponseExamples.API_TOKEN_BAD_FORMAT_RESPONSE,
                          description =
                              "The header parameter 'X-API-Version' is with a wrong format"),
                      @ExampleObject(
                          name = "Invalid sort param",
                          value = ApiResponseExamples.NO_PROPERTY_FOUND_FOR_TYPE,
                          description =
                              "The property used to sort does not exist in the entity object")
                    })),
        @ApiResponse(
            responseCode = "401",
            description = "The Header param 'X-Chroma-Token' is not being validated correctly",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = {
                      @ExampleObject(
                          name = "No Claims Response",
                          value = ApiResponseExamples.EMPTY_CLAIMS_RESPONSE,
                          description = "'X-Chroma-Token' is provided, but without payload"),
                      @ExampleObject(
                          name = "Expired Token Response",
                          value = ApiResponseExamples.EXPIRED_TOKEN_RESPONSE,
                          description =
                              "Header parameter 'X-Chroma-Token' is provided, but it's expired"),
                      @ExampleObject(
                          name = "Wrong Issuer Response",
                          value = ApiResponseExamples.WRONG_ISSUER_RESPONSE,
                          description =
                              "The parameter 'X-Chroma-Token' was not issued by the application"),
                      @ExampleObject(
                          name = "No Roles Response",
                          value = ApiResponseExamples.NO_ROLES_RESPONSE,
                          description =
                              "The header parameter 'X-Chroma-Token' has no roles in its claim set")
                    })),
        @ApiResponse(
            responseCode = "403",
            description = "The user has no permission to this request",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = {
                      @ExampleObject(
                          name = "No Permission Response",
                          value = ApiResponseExamples.NO_PERMISSION_RESPONSE,
                          description = "The user has no role allowed to access the endpoint")
                    })),
        @ApiResponse(
            responseCode = "404",
            description = "The user sent information that was not found in the server",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = {
                      @ExampleObject(
                          name = "No API Version Found Response",
                          value = ApiResponseExamples.API_VERSION_NOT_FOUND,
                          description = "The requested API version was not found nor implemented")
                    })),
        @ApiResponse(
            responseCode = "500",
            description = "Something unhandled happened in the server",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = {
                      @ExampleObject(
                          name = "Internal Server Error Response",
                          value = ApiResponseExamples.INTERNAL_SERVER_ERROR_RESPONSE,
                          description =
                              "Some generic Exception occurred, it's necessary to "
                                  + "check the logs to find out where and why this error happened")
                    }))
      })
  public ResponseEntity<AnalysesResponses> getAnalyses(
      @Parameter(hidden = true) Pageable pageable) {

    AnalysesResponses response = useCase.getAnalysesResponses(pageable);
    if (response.analyses().isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(response);
    }
  }
}
