package es.paulopossatto.chromamon.analysisservice.domain.model;

import lombok.Builder;

@Builder
public record Chromatography(
    String id,
    String hydrogen,
    String methane,
    String ethane,
    String acetylene,
    String carbonMonoxide,
    String carbonDioxide,
    String dateTimeCreated,
    String dateTimeModified,
    String userCreated,
    String userModified) {}
