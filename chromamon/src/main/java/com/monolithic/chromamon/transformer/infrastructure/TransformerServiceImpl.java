package com.monolithic.chromamon.transformer.infrastructure;

import com.monolithic.chromamon.analysis.domain.model.Transformer;
import com.monolithic.chromamon.analysis.domain.port.TransformerService;
import com.monolithic.chromamon.transformer.infrastructure.persistence.TransformerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransformerServiceImpl implements TransformerService {

   private final TransformerJpaRepository repo;

   @Override
   public Optional<Transformer> findById(Long id) {
      return Optional.empty();
   }

   @Override
   public boolean existsById(Long id) {
      return repo.existsById(id);
   }
}
