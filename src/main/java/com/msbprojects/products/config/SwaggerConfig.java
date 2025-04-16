package com.msbprojects.products.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomSwaggerConfig(){
        return new OpenAPI()
                .info(
                new Info().title("My Product Application")
                        .description("My Mithlesh Singh Bisht")
                        .version("V1")
                        .contact(new Contact().name("Mithlesh Singh Bisht")
                                .url("https://www.pixar.com/404"))
                )
                .externalDocs(new ExternalDocumentation().description("Bluetooh Songs")
                        .url("https://www.youtube.com/watch?v=gqQRpQOtNG4&t=258s"))
                .servers(Arrays.asList(new Server().url("http://localhost:8080").description("LOCAL"),
                        new Server().url("http:://localhost:9000").description("PROD")));
    }
}
