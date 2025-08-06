package es.paulopossatto.chromamon.analysisservice.infrastructure.output.persistence.repositories;

import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.ObservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/** The JPA repository for the observation table. */
public interface ObservationJpaRepository extends JpaRepository<ObservationEntity, Long> {}
