package es.paulopossatto.chromamon.analysisservice;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import es.paulopossatto.chromamon.analysisservice.application.exception.BadRequestException;
import es.paulopossatto.chromamon.analysisservice.application.exception.ForbiddenException;
import es.paulopossatto.chromamon.analysisservice.application.exception.InternalServerError;
import es.paulopossatto.chromamon.analysisservice.application.exception.UnauthorizedException;
import es.paulopossatto.chromamon.analysisservice.constants.contract.AnalysesResponseContract;
import es.paulopossatto.chromamon.analysisservice.infrastructure.entity.AnalysisEntity;
import es.paulopossatto.chromamon.analysisservice.infrastructure.output.persistence.repositories.AnalysisJpaRepository;
import es.paulopossatto.chromamon.analysisservice.infrastructure.security.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;

@Provider("analysisService")
@PactFolder("src/test/resources/pacts")
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationPactTest {

  private static final String TOKEN = "token";
  private static final String ROLE_ADMIN = "ADMIN";
  private static final String ROLE_USER = "USER";
  private static final String ROLE_ENGINEER = "ENGINEER";
  private static final String ROLE_CHEMIST = "CHEMIST";
  private static final String ROLE_LAB_ANALYST = "LAB_ANALYST";

  @Value("${local.server.port}")
  private Integer port;

  @MockitoBean
  private AnalysisJpaRepository repository;

  @MockitoBean
  private JwtUtils jwtUtils;

  @BeforeEach
  void before(PactVerificationContext context) {
    context.setTarget(new HttpTestTarget("localhost", port));
  }

  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  void pactVerificationTestTemplate(PactVerificationContext context) {
    context.verifyInteraction();
  }

  @State("analyses response is not empty")
  public void analysisExists() {
    Mockito.doNothing().when(jwtUtils).checkJwtAndPermissions(
        ArgumentMatchers.any()
    );

    List<AnalysisEntity> mockEntities = AnalysesResponseContract.analysesResponse200Ok();
    Page<AnalysisEntity> mockPage = new PageImpl<>(mockEntities);

    Mockito.when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(mockPage);
  }

  @State("analyses response is an list")
  public void analysisListWithoutPaginationExists() {
    Mockito.doNothing().when(jwtUtils).checkJwtAndPermissions(
        ArgumentMatchers.any()
    );

    List<AnalysisEntity> mockEntities = AnalysesResponseContract.analysesResponse200WithMoreElementsOk();
    Page<AnalysisEntity> mockPage = new PageImpl<>(mockEntities);

    Mockito.when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(mockPage);
  }

  @State("analyses response has a list os analyses")
  public void analysisListExists() {
    Mockito.doNothing().when(jwtUtils).checkJwtAndPermissions(
        ArgumentMatchers.any()
    );

    List<AnalysisEntity> mockEntities = List.of(
        AnalysesResponseContract.analysesResponse200WithMoreElementsOk().getFirst()
    );

    Page<AnalysisEntity> mockPage = new PageImpl<>(
        mockEntities,
        PageRequest.of(0, 1, Sort.by("analysisDate").ascending()),
        3
    );

    Mockito.when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(mockPage);
  }

  @State("analyses response is empty")
  public void analysisDoesNotExists() {
    Mockito.doNothing().when(jwtUtils).checkJwtAndPermissions(
        eq(TOKEN),
        ArgumentMatchers.any()
    );

    List<AnalysisEntity> mockEntities = AnalysesResponseContract.analysisResponse204NoContent();
    Page<AnalysisEntity> mockPage = new PageImpl<>(mockEntities);

    Mockito.when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(mockPage);
  }

  @State("analyses request with bad token")
  public void analysisRequestHasInvalidToken() {
    BadRequestException exception = new BadRequestException(
        "Header parameter 'X-Chroma-Token' has a invalid value"
    );
    Mockito.doThrow(exception).when(jwtUtils).checkJwtAndPermissions(
        eq(TOKEN),
        eq(ROLE_ADMIN),
        eq(ROLE_USER),
        eq(ROLE_ENGINEER),
        eq(ROLE_CHEMIST),
        eq(ROLE_LAB_ANALYST)
    );
  }

  @State("analyses request with empty token")
  public void analysisRequestHasEmptyToken() {
    BadRequestException exception = new BadRequestException(
        "Header parameter 'X-Chroma-Token' must not be empty"
    );
    Mockito.doThrow(exception).when(jwtUtils).checkJwtAndPermissions(
        eq(TOKEN),
        eq(ROLE_ADMIN),
        eq(ROLE_USER),
        eq(ROLE_ENGINEER),
        eq(ROLE_CHEMIST),
        eq(ROLE_LAB_ANALYST)
    );
  }

  @State("analyses request with empty api version")
  public void analysisRequestHasEmptyApiVersion() {
    BadRequestException exception = new BadRequestException(
        "Header parameter 'X-API-Version' must not be empty"
    );
    Mockito.doThrow(exception).when(jwtUtils).checkJwtAndPermissions(
        eq(TOKEN),
        eq(ROLE_ADMIN),
        eq(ROLE_USER),
        eq(ROLE_ENGINEER),
        eq(ROLE_CHEMIST),
        eq(ROLE_LAB_ANALYST)
    );
  }

  @State("analyses request with invalid api version")
  public void analysisRequestHasInvalidApiVersion() {
    BadRequestException exception = new BadRequestException(
        "'X-API-Version' must begin with 'V', followed by the version number"
    );
    Mockito.doThrow(exception).when(jwtUtils).checkJwtAndPermissions(
        eq(TOKEN),
        eq(ROLE_ADMIN),
        eq(ROLE_USER),
        eq(ROLE_ENGINEER),
        eq(ROLE_CHEMIST),
        eq(ROLE_LAB_ANALYST)
    );
  }

  @State("analyses request without token claim")
  public void analysisRequestHasNoTokenClaimSet() {
    UnauthorizedException exception = new UnauthorizedException(
        "Header parameter 'X-Chroma-Token' has no claims"
    );
    Mockito.doThrow(exception).when(jwtUtils).checkJwtAndPermissions(
        eq(TOKEN),
        eq(ROLE_ADMIN),
        eq(ROLE_USER),
        eq(ROLE_ENGINEER),
        eq(ROLE_CHEMIST),
        eq(ROLE_LAB_ANALYST)
    );
  }

  @State("analyses request with expired token")
  public void analysisRequestHasExpiredToken() {
    UnauthorizedException exception = new UnauthorizedException(
        "Header parameter 'X-Chroma-Token' is expired, please refresh it"
    );
    Mockito.doThrow(exception).when(jwtUtils).checkJwtAndPermissions(
        eq(TOKEN),
        eq(ROLE_ADMIN),
        eq(ROLE_USER),
        eq(ROLE_ENGINEER),
        eq(ROLE_CHEMIST),
        eq(ROLE_LAB_ANALYST)
    );
  }

  @State("analyses request with token not issued by the application")
  public void analysisRequestHasWrongIssuerToken() {
    UnauthorizedException exception = new UnauthorizedException(
        "Header parameter 'X-Chroma-Token' was not issued by the application"
    );
    Mockito.doThrow(exception).when(jwtUtils).checkJwtAndPermissions(
        eq(TOKEN),
        eq(ROLE_ADMIN),
        eq(ROLE_USER),
        eq(ROLE_ENGINEER),
        eq(ROLE_CHEMIST),
        eq(ROLE_LAB_ANALYST)
    );
  }

  @State("analyses request without token roles")
  public void analysisRequestHasNoRolesInToken() {
    UnauthorizedException exception = new UnauthorizedException(
        "Header parameter 'X-Chroma-Token' does not contain roles for the user"
    );
    Mockito.doThrow(exception).when(jwtUtils).checkJwtAndPermissions(
        eq(TOKEN),
        eq(ROLE_ADMIN),
        eq(ROLE_USER),
        eq(ROLE_ENGINEER),
        eq(ROLE_CHEMIST),
        eq(ROLE_LAB_ANALYST)
    );
  }

  @State("analyses request without permission")
  public void analysisRequestHasNoPermission() {
    ForbiddenException exception = new ForbiddenException(
        "The user has no permission to access this resource"
    );
    Mockito.doThrow(exception).when(jwtUtils).checkJwtAndPermissions(
        eq(TOKEN),
        eq(ROLE_ADMIN),
        eq(ROLE_USER),
        eq(ROLE_ENGINEER),
        eq(ROLE_CHEMIST),
        eq(ROLE_LAB_ANALYST)
    );
  }

  @State("analyses resource api version not exists")
  public void apiVersionDoesNotExists() {
    Mockito.doNothing().when(jwtUtils).checkJwtAndPermissions(
        ArgumentMatchers.any()
    );

    List<AnalysisEntity> mockEntities = AnalysesResponseContract.analysisResponse204NoContent();
    Page<AnalysisEntity> mockPage = new PageImpl<>(mockEntities);

    Mockito.when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(mockPage);
  }

  @State("analyses request with exception")
  public void analysisRequestThrownException() {
    InternalServerError exception = new InternalServerError(
        "Something happened. Please contact the user admin to check logs"
    );
    Mockito.doThrow(exception).when(jwtUtils).checkJwtAndPermissions(
        eq(TOKEN),
        eq(ROLE_ADMIN),
        eq(ROLE_USER),
        eq(ROLE_ENGINEER),
        eq(ROLE_CHEMIST),
        eq(ROLE_LAB_ANALYST)
    );
  }
}
