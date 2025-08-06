package es.paulopossatto.chromamon.analysisservice.infrastructure.config.enforcer;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.maven.enforcer.rule.api.AbstractEnforcerRule;
import org.apache.maven.enforcer.rule.api.EnforcerRuleException;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;
import org.apache.maven.rtinfo.RuntimeInformation;

/**
 * Enforcer for the file names.
 */
@Named("namingRule")
public class NamingRuleEnforcer extends AbstractEnforcerRule {

  private boolean shouldFail = true;

  private List<String> listParameters;

  private final MavenProject project;
  private final MavenSession session;
  private final RuntimeInformation runtimeInformation;

  /**
   * Constructor for the enforcer.
   */
  @Inject
  public NamingRuleEnforcer(
      MavenProject project, MavenSession session, RuntimeInformation runtimeInformation) {
    this.project = project;
    this.session = session;
    this.runtimeInformation = runtimeInformation;
  }

  @Override
  public void execute() throws EnforcerRuleException {

    getLog().info("Retrieved Target Folder: " + project.getBuild().getDirectory());
    getLog().info("Retrieved ArtifactId: " + project.getArtifactId());
    getLog().info("Retrieved Project: " + project);
    getLog().info("Retrieved Maven version: " + runtimeInformation.getMavenVersion());
    getLog().info("Retrieved Session: " + session);
    getLog().warnOrError("Parameter shouldFail: " + shouldFail);
    getLog().info(() -> "Parameter listParameters: " + listParameters);
    if (this.shouldFail) {
      throw new EnforcerRuleException("Failing because my param said so.");
    }
  }

  @Override
  public String toString() {
    return String.format("MyCustomRule[shouldFail=%b]", shouldFail);
  }
}
