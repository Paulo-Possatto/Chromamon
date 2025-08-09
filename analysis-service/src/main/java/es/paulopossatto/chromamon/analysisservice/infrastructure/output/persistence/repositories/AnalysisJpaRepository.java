package es.paulopossatto.chromamon.analysisservice.infrastructure.output.persistence.repositories;

import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.AnalysisEntity;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
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
   * Checks if an analysis exists for a given identifier.
   *
   * @param identifier the analysis identifier (AN_1234567890).
   * @return true if it exists or false if it doesn't.
   */
  @Query("SELECT COUNT(a)>0 FROM AnalysisEntity a WHERE a.identifier=:identifier")
  boolean isIdentifierExists(@Param("identifier") String identifier);

  /**
   * Get the analysis of a given identifier.
   *
   * @param identifier the analysis identifier.
   * @return an optional of the analysis entity.
   */
  @Query("SELECT a FROM AnalysisEntity a WHERE a.identifier=:identifier")
  Optional<AnalysisEntity> findByIdentifier(@Param("identifier") String identifier);

  /**
   * Get all analyses of a given transformer serial number.
   *
   * @param serialNumber the transformer serial number
   * @return the list of analyses for the given serial number.
   */
  @Query("SELECT a FROM AnalysisEntity a WHERE a.transSerNum=:serialNumber")
  List<AnalysisEntity> findBySerialNumber(@Param("serialNumber") String serialNumber);

  /**
   * Get all analyses done in a given period.
   *
   * @param startDate the start date of the analysis period.
   * @param endDate the end date of the analysis period.
   * @return a list of analyses done in the given period.
   */
  @Query("SELECT a FROM AnalysisEntity a WHERE a.analysisDateTime BETWEEN :startDate AND :endDate")
  List<AnalysisEntity> findByPeriod(
      @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate);
}
