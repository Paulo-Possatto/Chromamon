package es.paulopossatto.chromamon.analysisservice.domain.model;

import lombok.Builder;

@Builder
public record GasExtractionMethod(
    String gasExtractionId,
    String gasExtractionTypo,
    String gasExtractionLiteral,
    String dateTimeCreated,
    String dateTimeModified,
    String userCreated,
    String userModified) {}
