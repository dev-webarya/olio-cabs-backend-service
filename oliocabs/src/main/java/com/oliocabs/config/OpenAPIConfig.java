package com.oliocabs.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.annotation.PostConstruct;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication; // <-- ADD THIS IMPORT

@Configuration
public class OpenAPIConfig {

    /**
     * This method runs once after the bean is created.
     * It globally configures SpringDoc to ignore the 'Authentication' parameter
     * in all controller methods, preventing the serialization crash.
     */
    @PostConstruct
    public void configureSpringDoc() {
        SpringDocUtils.getConfig().addRequestWrapperToIgnore(Authentication.class);
    }

    @Bean
    public OpenAPI openAPI() {
        // Define the security scheme (Bearer Token)
        final String securitySchemeName = "bearerAuth";
        SecurityScheme securityScheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("Enter the JWT token obtained from the login endpoint.");

        // Define the security requirement
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securitySchemeName);

        return new OpenAPI()
                .info(new Info().title("Olio Cabs API")
                        .description("Backend API for the Olio Cabs booking service.")
                        .version("v1.0"))
                .addSecurityItem(securityRequirement)
                .components(new Components().addSecuritySchemes(securitySchemeName, securityScheme));
    }
}