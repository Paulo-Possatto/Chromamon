package es.paulopossatto.chromamon.analysisservice.infrastructure.output.persistence.repositories;

import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.ChromatographyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/** The JPA repository for the chromatography table. */
public interface ChromatographyJpaRepository extends JpaRepository<ChromatographyEntity, Long> {}
