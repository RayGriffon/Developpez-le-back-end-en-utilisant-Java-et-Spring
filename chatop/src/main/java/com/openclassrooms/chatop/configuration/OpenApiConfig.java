package com.openclassrooms.chatop.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().info(new Info()
      .title("Chatop API Documentation")
      .version("0.1")
      .description("Documentation de l'application ChaTop"));
  }
}
