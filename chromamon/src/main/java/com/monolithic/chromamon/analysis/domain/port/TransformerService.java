package com.monolithic.chromamon.analysis.domain.port;

import com.monolithic.chromamon.analysis.domain.model.Transformer;

import java.util.Optional;

public interface TransformerService {
   Optional<Transformer> findById(Long id);

   boolean existsById(Long id);
}
