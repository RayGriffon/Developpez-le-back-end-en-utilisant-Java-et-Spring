package com.openclassrooms.chatop.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
      .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
      .info(new Info()
      .title("Chatop API Documentation")
      .version("1.0")
      .description("Documentation de l'application ChaTop"));
  }

  private SecurityScheme createAPIKeyScheme() {
    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
      .bearerFormat("JWT")
      .scheme("bearer");
  }
}
