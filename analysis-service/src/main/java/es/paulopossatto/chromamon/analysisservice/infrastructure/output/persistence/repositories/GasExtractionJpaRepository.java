package es.paulopossatto.chromamon.analysisservice.infrastructure.output.persistence.repositories;

import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.GasExtractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/** The JPA Repository interface for the Gas Extraction table. */
public interface GasExtractionJpaRepository extends JpaRepository<GasExtractionEntity, Long> {}
