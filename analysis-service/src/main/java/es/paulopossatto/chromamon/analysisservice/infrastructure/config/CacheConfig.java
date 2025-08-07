package es.paulopossatto.chromamon.analysisservice.infrastructure.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Configuration for the cache. */
@Configuration
@EnableCaching
public class CacheConfig {

  /**
   * Bean configuration for the cache.
   *
   * @return the cache bean.
   */
  @Bean
  public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    cacheManager.setCaffeine(
        Caffeine.newBuilder().expireAfterAccess(10, TimeUnit.MINUTES).maximumSize(100));
    return cacheManager;
  }
}
