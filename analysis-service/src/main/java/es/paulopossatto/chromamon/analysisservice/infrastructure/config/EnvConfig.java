package es.paulopossatto.chromamon.analysisservice.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/** The configuration class to enable a .env file to be used as a secrets' storage. */
@Configuration
@PropertySource("classpath:.env")
public class EnvConfig {}
