package es.paulopossatto.chromamon.analysisservice.infrastructure.output.persistence.repositories;

import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.AnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/** The JPA repository for the analyses table. */
public interface AnalysisJpaRepository extends JpaRepository<AnalysisEntity, Long> {}
