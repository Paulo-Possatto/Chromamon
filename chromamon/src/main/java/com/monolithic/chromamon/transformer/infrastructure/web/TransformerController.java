package com.monolithic.chromamon.transformer.infrastructure.web;

import com.monolithic.chromamon.shared.application.security.HasPermission;
import com.monolithic.chromamon.shared.domain.security.Permission;
import com.monolithic.chromamon.transformer.infrastructure.persistence.TransformerEntity;
import com.monolithic.chromamon.transformer.infrastructure.persistence.TransformerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transformers")
@RequiredArgsConstructor
public class TransformerController {

   private final TransformerJpaRepository repo;

   @HasPermission(Permission.TRANSFORMER_READ)
   @GetMapping
   public List<TransformerEntity> list() {
      return repo.findAll();
   }

   @HasPermission(Permission.TRANSFORMER_CREATE)
   @PostMapping
   public TransformerEntity create(@RequestBody TransformerEntity t) {
      return repo.save(t);
   }

   @HasPermission(Permission.TRANSFORMER_UPDATE)
   @PutMapping("/{id}")
   public TransformerEntity update(@PathVariable Long id, @RequestBody TransformerEntity t) {
      t.setId(id);
      return repo.save(t);
   }

   @HasPermission(Permission.TRANSFORMER_DELETE)
   @DeleteMapping("/{id}")
   public void delete(@PathVariable Long id) {
      repo.deleteById(id);
   }
}
