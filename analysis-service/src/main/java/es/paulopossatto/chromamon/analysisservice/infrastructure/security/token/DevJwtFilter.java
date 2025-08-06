package es.paulopossatto.chromamon.analysisservice.infrastructure.security.token;

import es.paulopossatto.chromamon.analysisservice.infrastructure.input.rest.controllers.constants.ApiConstants;
import es.paulopossatto.chromamon.analysisservice.infrastructure.security.JwtUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/** Filter for the JWT token. */
@Component
@Profile({"local", "integration-test"})
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@RequiredArgsConstructor
public class DevJwtFilter implements Filter {

  private final LocalJwtService service;
  private final JwtUtils utils;

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;

    if (!isLocalhost(request)) {
      filterChain.doFilter(request, servletResponse);
    }

    String existingToken = request.getHeader(ApiConstants.TOKEN_HEADER_NAME);
    if (existingToken != null && !existingToken.trim().isEmpty()) {
      filterChain.doFilter(request, servletResponse);
    }

    try {
      String devToken = service.generateToken();
      HttpServletRequestWrapper requestWrapper =
          new HttpServletRequestWrapper(request) {
            @Override
            public String getHeader(String name) {
              if (ApiConstants.TOKEN_HEADER_NAME.equalsIgnoreCase(name)) {
                return devToken;
              }
              return super.getHeader(name);
            }

            @Override
            public Enumeration<String> getHeaders(String name) {
              if (ApiConstants.TOKEN_HEADER_NAME.equalsIgnoreCase(name)) {
                return Collections.enumeration(Collections.singletonList(devToken));
              }
              return super.getHeaders(name);
            }

            @Override
            public Enumeration<String> getHeaderNames() {
              Set<String> headerNames = new HashSet<>();
              Enumeration<String> originalHeaders = super.getHeaderNames();
              while (originalHeaders.hasMoreElements()) {
                headerNames.add(originalHeaders.nextElement());
              }
              headerNames.add(ApiConstants.TOKEN_HEADER_NAME);
              return Collections.enumeration(headerNames);
            }
          };

      log.debug(
          "JWT token added automagically to the request: {} {}",
          request.getMethod(),
          request.getRequestURI());

      filterChain.doFilter(requestWrapper, servletResponse);

    } catch (Exception e) {
      log.error("Error processing token for development", e);
      filterChain.doFilter(request, servletResponse);
    }
  }

  private boolean isLocalhost(HttpServletRequest request) {
    String serverName = request.getServerName();
    return "localhost".equals(serverName)
        || "127.0.0.1".equals(serverName)
        || "0.0.0.0".equals(serverName);
  }
}
