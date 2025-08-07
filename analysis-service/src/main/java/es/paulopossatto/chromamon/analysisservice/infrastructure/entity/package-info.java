/**
 * This package contains all the Object Relational Mapping (ORM) entities.
 *
 * <p>To be sure about the files (classes, not records) in this package:
 *
 * <ul>
 *   <li>BaseEntity.java -> It's used for every ORM, saying who created or modified and the
 *       date-time of when that happened
 *   <li>AnalysisEntity.java -> The main information for this service starts here
 *   <li>ChromatographyEntity.java -> The information related to the chromatographic values
 *   <li>GasExtractionEntity.java -> An ORM to verify how the oil was extracted from the transformer
 *   <li>ObservationEntity.java -> An ORM that contains Observations about the analysis
 *   <li>OilTypesEntity.java -> The ORM that has all the isolating Oil Types used
 * </ul>
 *
 * @author Paulo Possatto
 * @version 1.0.0
 * @since 2025-08-06
 * @see es.paulopossatto.chromamon.analysisservice.infrastructure.entity.AnalysisEntity
 */
package es.paulopossatto.chromamon.analysisservice.infrastructure.entity;
