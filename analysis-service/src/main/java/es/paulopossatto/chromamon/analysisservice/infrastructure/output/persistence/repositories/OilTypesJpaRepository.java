package es.paulopossatto.chromamon.analysisservice.infrastructure.output.persistence.repositories;

import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.OilTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/** The JPA Repository interface for the Oil Types table. */
public interface OilTypesJpaRepository extends JpaRepository<OilTypesEntity, Long> {}
