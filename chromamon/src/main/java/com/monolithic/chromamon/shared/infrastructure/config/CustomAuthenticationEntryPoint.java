package com.monolithic.chromamon.shared.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monolithic.chromamon.shared.infrastructure.web.GlobalExceptionHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

   private final ObjectMapper objectMapper;

   @Override
   public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
      log.error("Unauthorized error: {}", authException.getMessage());

      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

      GlobalExceptionHandler.ErrorResponse errorResponse = GlobalExceptionHandler.ErrorResponse.builder()
         .status(HttpStatus.UNAUTHORIZED.value())
         .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
         .message(authException.getMessage() != null ? authException.getMessage() : "Unauthorized")
         .path(request.getRequestURI())
         .build();

      final ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(response.getOutputStream(), errorResponse);
   }
}
