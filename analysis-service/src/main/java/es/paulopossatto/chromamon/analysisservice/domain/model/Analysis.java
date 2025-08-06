package es.paulopossatto.chromamon.analysisservice.domain.model;

import lombok.Builder;

@Builder
public record Analysis(
    String id,
    String identifier,
    String transformerSerialNumber,
    String analysisDatetime,
    String labAnalysisDateTime,
    Chromatography chromatography,
    OilType oilType,
    Observation observation,
    String furfuralAnalysis,
    String oilHumidity,
    String dateTimeCreated,
    String dateTimeModified,
    String userCreated,
    String userModified) {}
