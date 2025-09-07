package com.monolithic.chromamon.shared.infrastructure.config;

import com.monolithic.chromamon.shared.domain.security.SwaggerConstants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Global configuration for Open API.
 */
@OpenAPIDefinition(
   tags = {
      @Tag(
         name = SwaggerConstants.TAG_AUTHENTICATION,
         description = "Endpoints for authentication and user management"
      )
   },
   info = @Info(
      title = "Chromamon",
      description = "API for chromatographic analysis in electrical substation transformers",
      version = "v0.0.1-SNAPSHOT",
      contact = @Contact(
         name = "Paulo Possatto",
         url = "https://github.com/Paulo-Possatto"
      ),
      license = @License(
         name = "MIT License",
         url = "https://opensource.org/licenses/MIT"
      )
   ),
   servers = {
      @Server(
         description = "Local Development Environment",
         url = SwaggerConstants.SERVER_LOCALHOST
      ),
      @Server(
         description = "Development Server",
         url = SwaggerConstants.DEV_SERVER
      )
   }
)
@SecurityScheme(
   name = SwaggerConstants.AUTH_NAME,
   description = "JWT Bearer Token",
   scheme = "bearer",
   type = SecuritySchemeType.HTTP,
   bearerFormat = "JWT",
   in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
