package es.paulopossatto.chromamon.analysisservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext
@Testcontainers
public class ApplicationIT {

  @Value("${local.server.port}")
  private Integer port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  @DisplayName("Checks if the service starts up correctly")
  void checkKeepAlive() throws URISyntaxException {
    ResponseEntity<String> entity =
        testRestTemplate.getForEntity(
            new URI(
                String.format("http://localhost:%d/keepalive", port)
            ), String.class
        );
    assertEquals(HttpStatus.OK, entity.getStatusCode());
  }

  @Test
  @DisplayName("Generate Swagger")
  void generateSwagger() throws IOException, URISyntaxException {
    ResponseEntity<String> response =
        testRestTemplate.getForEntity(
            new URI(
                String.format("http://localhost:%d/api-docs.yaml", port)
            ), String.class
        );
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String yamlContent = response.getBody();

    Path outputPath = Paths.get("src/test/resources/swagger");
    Files.createDirectories(outputPath);

    Path filePath = outputPath.resolve("swagger.yaml");
    assertNotNull(yamlContent);
    Files.write(filePath, yamlContent.getBytes());
  }
}
