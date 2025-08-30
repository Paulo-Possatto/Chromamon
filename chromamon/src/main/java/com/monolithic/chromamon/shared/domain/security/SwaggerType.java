package com.monolithic.chromamon.shared.domain.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The data types used by swagger.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SwaggerType {

   /**
    * Swagger data type for strings (can also be dates).
    */
   public static final String STRING = "string";
   /**
    * Swagger data type for objects.
    */
   public static final String OBJECT = "object";
   /**
    * Swagger data type for array.
    */
   public static final String ARRAY = "array";
   /**
    * Swagger data type for numbers (float, double, ...).
    */
   public static final String NUMBER = "number";
   /**
    * Swagger data type for boolean.
    */
   public static final String BOOLEAN = "boolean";
   /**
    * Swagger data type for integers (int32, int64 aka long).
    */
   public static final String INTEGER = "integer";
   /**
    * Swagger tag name for tags related to Log in resources.
    */
   public static final String TAG_AUTHENTICATION = "Authentication";
}
