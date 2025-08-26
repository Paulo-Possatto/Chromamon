package com.monolithic.chromamon.shared.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableJpaAuditing
@EnableAsync
@EnableScheduling
public class ApplicationConfig {

   @Bean
   public RestTemplate restTemplate() {
      return new RestTemplate();
   }
}