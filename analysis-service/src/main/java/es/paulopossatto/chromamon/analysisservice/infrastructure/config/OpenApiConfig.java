package es.paulopossatto.chromamon.analysisservice.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** The configuration class for the OpenAPI generated document. */
@Configuration
public class OpenApiConfig {

  /**
   * The bean method that contains the configuration for the OpenAPI.
   *
   * @return the configuration.
   */
  @Bean(name = "customOpenAPI")
  public OpenAPI customOpenApi() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Chromamon Analysis Service API")
                .version("1.0")
                .description("API for transformer oil analysis management"));
  }
}
