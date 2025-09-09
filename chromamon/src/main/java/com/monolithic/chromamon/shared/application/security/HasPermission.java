package com.monolithic.chromamon.shared.application.security;

import com.monolithic.chromamon.shared.domain.security.Permission;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HasPermission {
  Permission value();
}
