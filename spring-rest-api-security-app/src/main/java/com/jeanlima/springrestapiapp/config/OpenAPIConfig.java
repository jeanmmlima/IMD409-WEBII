package com.jeanlima.springrestapiapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;



@Configuration
public class OpenAPIConfig {
    
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer");
    }

    @Bean
public OpenAPI openAPI() {
    return new OpenAPI().addSecurityItem(new SecurityRequirement().
            addList("Bearer Authentication"))
        .components(new Components().addSecuritySchemes
            ("Bearer Authentication", createAPIKeyScheme()))
        .info(new Info().title("Minha API Rest")
            .description("alguma descrição personalizada da API.")
            .version("1.0").contact(new Contact().name("Lima, J. M. M.")
                .email( "jean.lima@imd.ufrn.br").url("https://docente.ufrn.br/201900344316/perfil"))
            .license(new License().name("Licença da API")
                .url("API license URL")));
}
}
