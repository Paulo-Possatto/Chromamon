package es.paulopossatto.chromamon.analysisservice.infrastructure.config.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import es.paulopossatto.chromamon.analysisservice.infrastructure.config.utils.JsonUtil;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/** Configuration for the defined wiremocks. */
@Configuration
@Profile("local")
@Slf4j
public class WiremockConfig {

  private static final String METEO_PATH = "https://archive-api.open-meteo.com/v1/archive";

  /** Bean that create the mocks for the external APIs. */
  @Bean
  public WireMockServer wireMockServer() throws IOException {
    int wiremockPort = 8089;
    log.info("Wiremock server at port {}", wiremockPort);

    WireMockServer wireMockServer =
        new WireMockServer(WireMockConfiguration.options().port(wiremockPort));

    wireMockServer.stubFor(
        get(urlPathTemplate(METEO_PATH))
            .withQueryParam("latitude", equalTo("36.7285982"))
            .withQueryParam("longitude", equalTo("-4.4756972"))
            .withQueryParam("start_date", equalTo("2024-01-01"))
            .withQueryParam("end_date", equalTo("2024-01-01"))
            .withQueryParam("hourly", equalTo("temperature_2m"))
            .withQueryParam("timezone", equalTo("auto"))
            .willReturn(
                okJson(
                    JsonUtil.loadFromFile(
                        "wiremock/__files/meteo/ok-200-los-ramos-substation-meteo.json"))));

    wireMockServer.start();
    log.info("Wiremock started!");

    return wireMockServer;
  }
}
