package es.paulopossatto.chromamon.analysisservice;

import es.paulopossatto.chromamon.analysisservice.infrastructure.config.PostgresqlTestContainer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests to check the application status and generate the swagger.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(PostgresqlTestContainer.class)
@DirtiesContext
@ActiveProfiles("integration-test")
@Tag("integration")
class ApplicationIT {

  @LocalServerPort
  private int port;

  @Autowired
  PostgreSQLContainer<?> container;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void checkKeepAlive() throws Exception {
    ResponseEntity<String> entity = testRestTemplate.getForEntity(
        new URI("http://localhost:" + port + "/keepalive"), String.class);
    assertEquals(HttpStatus.OK, entity.getStatusCode());
  }

  @Test
  void generateSwagger() throws Exception {
    ResponseEntity<String> response = testRestTemplate.getForEntity(
        new URI("http://localhost:" + port + "/api-docs.yaml"), String.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    String yamlContent = response.getBody();
    assertNotNull(yamlContent);

    Path outputPath = Paths.get("src/test/resources/swagger");
    Files.createDirectories(outputPath);
    Path filePath = outputPath.resolve("swagger.yaml");
    Files.write(filePath, yamlContent.getBytes());
  }
}
