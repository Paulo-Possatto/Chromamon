package com.monolithic.chromamon.transformer.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransformerJpaRepository extends JpaRepository<TransformerEntity, Long> {
}
