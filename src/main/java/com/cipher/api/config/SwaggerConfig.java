package com.cipher.api.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Value("v1")
    private String version;
    @Bean
    public OpenAPI api() {

        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("STAR WARS FILM API")
                        .description("Api that adds comment to film.")
                        .version(version));
    }


    @Bean
    public GroupedOpenApi userEndpoint() {
        return GroupedOpenApi
                .builder()
                .group("FILM")
                .pathsToMatch("/films/**").build();
    }
    @Bean
    public GroupedOpenApi eventEndpoint() {
        return GroupedOpenApi
                .builder()
                .group("COMMENT")
                .pathsToMatch("/comments/**").build();
    }
}

