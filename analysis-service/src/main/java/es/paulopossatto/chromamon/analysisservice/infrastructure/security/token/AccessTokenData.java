package es.paulopossatto.chromamon.analysisservice.infrastructure.security.token;

import com.google.gson.annotations.SerializedName;
import es.paulopossatto.chromamon.analysisservice.application.exception.BadRequestException;
import java.util.Date;
import lombok.Getter;

/** The object for the token claim set. */
@Getter
public class AccessTokenData {
  @SerializedName("sub")
  private final String subject;

  @SerializedName("iss")
  private final String issuer;

  @SerializedName("department")
  private final String department;

  @SerializedName("nbf")
  private final Long notBefore;

  @SerializedName("iat")
  private final Long issuedAt;

  @SerializedName("exp")
  private final Long expirationTime;

  @SerializedName("requester")
  private final Requester requester;

  private AccessTokenData() {
    this.subject = null;
    this.issuer = null;
    this.department = null;
    this.notBefore = null;
    this.issuedAt = null;
    this.expirationTime = null;
    this.requester = null;
  }

  /** The constructor for the token. */
  public AccessTokenData(
      String subject,
      String issuer,
      String department,
      Long notBefore,
      Long issuedAt,
      Long expirationTime,
      Requester requester) {
    this.subject = subject;
    this.issuer = issuer;
    this.department = department;
    this.notBefore = notBefore;
    this.issuedAt = issuedAt;
    this.expirationTime = expirationTime;
    this.requester = requester;
    this.validate();
  }

  final void validate() {
    if (subject == null || subject.isEmpty() || !subject.matches("[A-Z]{2}[0-9]{2}[A-Z]{2}")) {
      throw new BadRequestException("subject claim is null, empty or wrong");
    }
    if (issuer == null || issuer.isEmpty() || !issuer.equalsIgnoreCase("chroma-app")) {
      throw new BadRequestException("issuer claim is null, empty or wrong");
    }
    if (notBefore == null) {
      throw new BadRequestException("notBefore claim is null");
    }
    if (department == null || department.isEmpty()) {
      throw new BadRequestException("department claim is null, empty or wrong");
    }
    if (issuedAt == null || issuedAt == 0) {
      throw new BadRequestException("issuedAt claim is null or wrong");
    }
    if (expirationTime == null || expirationTime.compareTo(new Date().getTime()) < 0) {
      throw new BadRequestException("expirationTime claim is null or the token is expired");
    }
  }
}
