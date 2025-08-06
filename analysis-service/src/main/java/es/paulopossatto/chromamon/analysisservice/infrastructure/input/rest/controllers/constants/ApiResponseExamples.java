package es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers.constants;

/** The examples for the Swagger generated file. */
public class ApiResponseExamples {

  public static final String GET_ANALYSES_RESPONSE =
      """
              {
                  "analyses": [
                      {
                          "identifier": "AN_3102554574",
                          "transformerSerialNumber": "TRF-132kV-2023-ABC123",
                          "analysisDateTime": "25-06-2025 15:19:38",
                          "labAnalysisDateTime": "26-06-2025 07:15:11",
                          "chromatography": {
                              "hydrogen": "0,15",
                              "methane": "0,21",
                              "ethane": "0,09",
                              "acetylene": "0,11",
                              "carbonMonoxide": "4,45",
                              "carbonDioxide": "915,41",
                              "createdDateTime": "26-07-2025 18:35:06",
                              "lastUpdateDateTime": "26-07-2025 18:35:06",
                              "userCreated": "AA00AA",
                              "userLastUpdated": "AA00AA"
                          },
                          "oilType": "Mineral Oil",
                          "observation": {
                              "sampleCondition": "Presence of particles",
                              "gasExtractionMethod": "Direct",
                              "modelUsed": "GC Agilent 7890B",
                              "createdDateTime": "26-07-2025 18:35:06",
                              "lastUpdateDateTime": "26-07-2025 18:35:06",
                              "userCreated": "AA00AA",
                              "userLastUpdated": "AA00AA"
                          },
                          "furfuralAnalysis": "0,50",
                          "oilHumidity": "15,00",
                          "createdDateTime": "26-07-2025 18:35:06",
                          "lastUpdateDateTime": "26-07-2025 18:35:06",
                          "userCreated": "AA00AA",
                          "userLastUpdated": "AA00AA"
                      },
                      {
                          "identifier": "AN_5069214547",
                          "transformerSerialNumber": "TRF-220kV-2023-XYZ789",
                          "analysisDateTime": "26-06-2025 06:45:22",
                          "labAnalysisDateTime": "27-06-2025 08:30:05",
                          "chromatography": {
                              "hydrogen": "0,22",
                              "methane": "0,35",
                              "ethane": "0,12",
                              "acetylene": "0,08",
                              "carbonMonoxide": "3,75",
                              "carbonDioxide": "1.020,10",
                              "createdDateTime": "26-07-2025 18:35:06",
                              "lastUpdateDateTime": "26-07-2025 18:35:06",
                              "userCreated": "AA00AA",
                              "userLastUpdated": "AA00AA"
                          },
                          "oilType": "Vegetal Oil",
                          "observation": {
                              "sampleCondition": "Clear with slight discoloration",
                              "gasExtractionMethod": "Manual Pump",
                              "modelUsed": "GC Shimadzu 2030",
                              "createdDateTime": "26-07-2025 18:35:06",
                              "lastUpdateDateTime": "26-07-2025 18:35:06",
                              "userCreated": "AA00AA",
                              "userLastUpdated": "AA00AA"
                          },
                          "furfuralAnalysis": "0,30",
                          "oilHumidity": "12,30",
                          "createdDateTime": "26-07-2025 18:35:06",
                          "lastUpdateDateTime": "26-07-2025 18:35:06",
                          "userCreated": "AA00AA",
                          "userLastUpdated": "AA00AA"
                      },
                      {
                          "identifier": "AN_8369902135",
                          "transformerSerialNumber": "TRF-66kV-2024-DEF456",
                          "analysisDateTime": "27-06-2025 12:30:10",
                          "labAnalysisDateTime": "28-06-2025 06:45:33",
                          "chromatography": {
                              "hydrogen": "0,18",
                              "methane": "0,28",
                              "ethane": "0,11",
                              "acetylene": "0,15",
                              "carbonMonoxide": "5,20",
                              "carbonDioxide": "980,75",
                              "createdDateTime": "26-07-2025 18:35:06",
                              "lastUpdateDateTime": "26-07-2025 18:35:06",
                              "userCreated": "AA00AA",
                              "userLastUpdated": "AA00AA"
                          },
                          "oilType": "Silicon Oil",
                          "observation": {
                              "sampleCondition": "Normal appearance",
                              "gasExtractionMethod": "Electric Pump",
                              "modelUsed": "GC PerkinElmer Clarus 680",
                              "createdDateTime": "26-07-2025 18:35:06",
                              "lastUpdateDateTime": "26-07-2025 18:35:06",
                              "userCreated": "AA00AA",
                              "userLastUpdated": "AA00AA"
                          },
                          "furfuralAnalysis": "0,70",
                          "oilHumidity": "18,72",
                          "createdDateTime": "26-07-2025 18:35:06",
                          "lastUpdateDateTime": "26-07-2025 18:35:06",
                          "userCreated": "AA00AA",
                          "userLastUpdated": "AA00AA"
                      }
                  ],
                  "currentPage": 0,
                  "totalItems": 3,
                  "totalPages": 1
              }
          """;

  public static final String INVALID_TOKEN_RESPONSE =
      """
          {
              "statusCode": 400,
              "message": "Bad Request",
              "details": "Header parameter 'X-Chroma-Token' has a invalid value",
              "timestamp": "22-07-2025 10:23:30"
          }
          """;

  public static final String EMPTY_TOKEN_RESPONSE =
      """
          {
              "statusCode": 400,
              "message": "Bad Request",
              "details": "Header parameter 'X-Chroma-Token' must not be empty",
              "timestamp": "22-07-2025 10:23:30"
          }
          """;

  public static final String EMPTY_API_VERSION_RESPONSE =
      """
          {
              "statusCode": 400,
              "message": "Bad Request",
              "details": "Header parameter 'X-API-Version' must not be empty",
              "timestamp": "22-07-2025 10:23:30"
          }
          """;

  public static final String API_TOKEN_BAD_FORMAT_RESPONSE =
      """
          {
              "statusCode": 400,
              "message": "Bad Request",
              "details": "'X-API-Version' must begin with 'V', followed by the version number",
              "timestamp": "22-07-2025 10:23:30"
          }
          """;

  public static final String NO_PROPERTY_FOUND_FOR_TYPE =
      """
              {
                  "statusCode": 400,
                  "message": "Bad Request",
                  "details": "No property 'abc' found for type 'AnalysisEntity'",
                  "timestamp": "26-07-2025 20:56:52"
              }
          """;

  public static final String EMPTY_CLAIMS_RESPONSE =
      """
          {
              "statusCode": 401,
              "message": "Unauthorized",
              "details": "Header parameter 'X-Chroma-Token' has no claims",
              "timestamp": "22-07-2025 10:23:30"
          }
          """;

  public static final String EXPIRED_TOKEN_RESPONSE =
      """
          {
              "statusCode": 401,
              "message": "Unauthorized",
              "details": "Header parameter 'X-Chroma-Token' is expired, please refresh it",
              "timestamp": "22-07-2025 10:23:30"
          }
          """;

  public static final String WRONG_ISSUER_RESPONSE =
      """
          {
              "statusCode": 401,
              "message": "Unauthorized",
              "details": "Header parameter 'X-Chroma-Token' was not issued by the application",
              "timestamp": "22-07-2025 10:23:30"
          }
          """;

  public static final String NO_ROLES_RESPONSE =
      """
          {
              "statusCode": 401,
              "message": "Unauthorized",
              "details": "Header parameter 'X-Chroma-Token' does not contain roles for the user",
              "timestamp": "22-07-2025 10:23:30"
          }
          """;

  public static final String NO_PERMISSION_RESPONSE =
      """
          {
              "statusCode": 403,
              "message": "Forbidden",
              "details": "The user has no permission to access this resource",
              "timestamp": "22-07-2025 10:23:30"
          }
          """;

  public static final String API_VERSION_NOT_FOUND =
      """
          {
              "statusCode": 404,
              "message": "Not Found",
              "details": "API Version not supported",
              "timestamp": "22-07-2025 10:23:30"
          }
          """;

  public static final String INTERNAL_SERVER_ERROR_RESPONSE =
      """
          {
              "statusCode": 500,
              "message": "Internal Server Error",
              "details": "Something happened. Please contact the user admin to check logs",
              "timestamp": "22-07-2025 10:23:30"
          }
          """;
}
