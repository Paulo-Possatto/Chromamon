package es.paulopossatto.chromamon.analysisservice.domain.model;

import lombok.Builder;

@Builder
public record OilType(
    String oilTypeId,
    String oilTypeTypo,
    String oilTypeLiteral,
    String dateTimeCreated,
    String dateTimeModified,
    String userCreated,
    String userModified) {}
