package com.monolithic.chromamon.shared.infrastructure.security;

import com.monolithic.chromamon.shared.application.security.HasPermission;
import com.monolithic.chromamon.shared.application.security.PermissionService;
import com.monolithic.chromamon.shared.domain.security.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class PermissionAspect {

   private final PermissionService permissionService;

   @Around("@annotation(hasPermission)")
   public Object checkPermission(ProceedingJoinPoint joinPoint, HasPermission hasPermission) throws Throwable {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (authentication == null || !authentication.isAuthenticated()) {
         throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "User Not Authenticated");
      }
      Object[] array = authentication.getAuthorities().toArray();
      if(authentication.getAuthorities().isEmpty() || array[0].toString().contains("ANONYMOUS")) {
         throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Token not provided");
      }

      log.info("Checking permissions for user {}", authentication.getName());
      Jwt jwt = (Jwt) authentication.getPrincipal();
      Long userId = jwt.getClaim("userId");
      String roleStr = jwt.getClaim("role");
      Role role = Role.valueOf(roleStr);

      if (!permissionService.hasPermission(userId, role, hasPermission.value())) {
         throw new AccessDeniedException("Insufficient permission: " + hasPermission.value().getPermission());
      }

      return joinPoint.proceed();
   }
}
