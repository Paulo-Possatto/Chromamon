package com.monolithic.chromamon;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.modulith.Modulith;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/** Chromamon main application class. */
@SpringBootApplication
@Slf4j
@Modulith
@EnableJpaRepositories(
    basePackages = {
      "com.monolithic.chromamon.*.infrastructure.persistence",
      "com.monolithic.chromamon.shared.infrastructure.security"
    })
@EnableMongoRepositories(
    basePackages = {"com.monolithic.chromamon.audit.infrastructure.persistence"})
@EnableJpaAuditing
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
public class Application {

  /**
   * Main method for the application to run.
   *
   * @param args arguments used to execute the program.
   */
  public static void main(String[] args) {
    Instant start = Instant.now();
    SpringApplication.run(Application.class, args);
    Instant end = Instant.now();

    log.info(String.join("", Collections.nCopies(100, "-")));
    log.info(
        "--- Chromamon app started successfully in {} ms ---",
        Duration.between(start, end).toMillis());
    log.info(String.join("", Collections.nCopies(100, "-")));
  }
}
