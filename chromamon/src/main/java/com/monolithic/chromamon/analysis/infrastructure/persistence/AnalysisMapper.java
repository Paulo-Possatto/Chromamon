package com.monolithic.chromamon.analysis.infrastructure.persistence;

import com.monolithic.chromamon.analysis.domain.model.Analysis;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AnalysisMapper {

  AnalysisEntity toEntity(Analysis analysis);

  Analysis toDomain(AnalysisEntity analysisEntity);
}
