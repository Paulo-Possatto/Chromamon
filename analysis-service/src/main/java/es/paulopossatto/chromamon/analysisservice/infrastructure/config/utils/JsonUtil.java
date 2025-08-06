package es.paulopossatto.chromamon.analysisservice.infrastructure.config.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.ClassPathResource;

/**
 * Class to convert the JSON string into an object.
 */
public final class JsonUtil {

  private JsonUtil() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static String loadFromFile(String path) throws IOException {
    InputStream inputStream = new ClassPathResource(path).getInputStream();
    return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
  }
}
