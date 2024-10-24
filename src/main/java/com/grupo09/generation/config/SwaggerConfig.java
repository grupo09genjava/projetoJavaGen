package com.grupo09.generation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info()
                              .title("Generation School API")
                              .version("1.0")
                              .description("This service is responsible for management at the school"))
                .addSecurityItem(new SecurityRequirement()
                                         .addList(securitySchemeName))
                .components(new Components()
                                    .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT")));
    }
}
