package com.monolithic.chromamon.shared.domain.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/** The data types used by swagger. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SwaggerConstants {
  /** The localhost URL for local development. */
  public static final String SERVER_LOCALHOST = "http://localhost:8080";

  /** The development server. */
  public static final String DEV_SERVER = "192.168.1.40";

  /** The authorization name. */
  public static final String AUTH_NAME = "bearerAuth";

  /** Swagger data type for strings (can also be dates). */
  public static final String STRING = "string";

  /** Swagger data type for objects. */
  public static final String OBJECT = "object";

  /** Swagger data type for array. */
  public static final String ARRAY = "array";

  /** Swagger data type for numbers (float, double, ...). */
  public static final String NUMBER = "number";

  /** Swagger data type for boolean. */
  public static final String BOOLEAN = "boolean";

  /** Swagger data type for integers (int32, int64 aka long). */
  public static final String INTEGER = "integer";

  /** Constant for GET endpoints */
  public static final String METHOD_GET = "GET";

  /** Constant for POST endpoints */
  public static final String METHOD_POST = "POST";

  /** Constant for PUT endpoints */
  public static final String METHOD_PUT = "PUT";

  /** Constant for DELETE endpoints */
  public static final String METHOD_DELETE = "DELETE";

  /** Swagger tag name for tags related to Log in resources. */
  public static final String TAG_AUTHENTICATION = "Authentication";

  /** Tag for endpoints related to user operations. */
  public static final String TAG_USER = "User";

  /** Tag for endpoints related to analysis operations. */
  public static final String TAG_ANALYSIS = "Analysis";
}
