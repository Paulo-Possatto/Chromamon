package es.paulopossatto.chromamon.analysisservice.domain.model;

import lombok.Builder;

@Builder
public record Observation(
    String observationId,
    String sampleCondition,
    GasExtractionMethod gasExtractionMethod,
    String modelUsed,
    String dateTimeCreated,
    String dateTimeModified,
    String userCreated,
    String userModified) {}
