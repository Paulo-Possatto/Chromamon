package es.paulopossatto.chromamon.analysisservice.infrastructure.output.persistence.repositories;

import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.AnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** The JPA repository for the analyses table. */
public interface AnalysisJpaRepository extends JpaRepository<AnalysisEntity, Long> {

  /**
   * Checks if an analysis exists for a given transformer serial number.
   *
   * @param serialNumber the transformer serial number.
   * @return true if it exists or false if it doesn't.
   */
  @Query("SELECT COUNT(a)>0 FROM AnalysisEntity a WHERE a.transSerNum=:serialNumber")
  boolean isSerialNumberExists(@Param("serialNumber") String serialNumber);

  /**
   * Checks if an analysis exists for a given identifier
   *
   * @param identifier
   * @return
   */
  @Query("SELECT COUNT(a)>0 FROM AnalysisEntity a WHERE a.identifier=:identifier")
  boolean isIdentifierExists(@Param("identifier") String identifier);
}
