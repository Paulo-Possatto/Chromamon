package es.paulopossatto.chromamon.analysisservice.infrastructure.mapper;

import es.paulopossatto.chromamon.analysisservice.constants.AnalysesMother;
import es.paulopossatto.chromamon.analysisservice.domain.model.Analysis;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.AnalysisEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test class for the Analysis Mapper.
 */
@SpringBootTest
public class AnalysisMapperTest {

  @Autowired
  private AnalysisMapper analysisMapper;

  @Test
  @DisplayName("""
      GIVEN an Analysis Entity object
      WHEN the toDomain mapper method is called
      THEN should return a domain instance of the object
      AND all the parameters should be type String
      """)
  void whenToDomainMethodIsCalled_thenShouldConvertToDomainObject(){
    // ARRANGE
    AnalysisEntity entity = AnalysesMother.analysisEntityObjectFilled();

    // ACT
    Analysis domain = analysisMapper.toDomain(entity);

    // ASSERT
    assertEquals(AnalysesMother.analysisDomainObjectFilled(), domain);
  }

  @Test
  @DisplayName("""
      GIVEN an Analysis Domain object
      WHEN the toEntity method is called
      THEN should return an Entity Analysis object
      """)
  void whenToEntityMethodIsCalled_thenShouldConvertToEntityObject(){
    // ARRANGE
    Analysis domain = AnalysesMother.analysisDomainObjectFilled();

    // ACT
    AnalysisEntity entity = analysisMapper.toEntity(domain);

    // ASSERT
    assertEquals(AnalysesMother.analysisEntityObjectFilled().toString(), entity.toString());
  }

}
