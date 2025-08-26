package com.monolithic.chromamon.shared.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
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
         description = "Development Environment",
         url = "http://localhost:8080"
      )
   }
)
@SecurityScheme(
   name = "bearerAuth",
   description = "JWT Bearer Token",
   scheme = "bearer",
   type = SecuritySchemeType.HTTP,
   bearerFormat = "JWT",
   in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
