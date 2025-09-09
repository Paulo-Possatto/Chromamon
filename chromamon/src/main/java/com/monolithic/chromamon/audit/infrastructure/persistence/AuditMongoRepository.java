package com.monolithic.chromamon.audit.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditMongoRepository extends MongoRepository<AuditDocument, String> {}
