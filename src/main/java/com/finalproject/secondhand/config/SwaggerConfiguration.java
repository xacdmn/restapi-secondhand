package com.finalproject.secondhand.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI(@Value("Ini baru pembuatan awal") String appDescription,
                                 @Value("Alpha test") String appVersion) {
        return new OpenAPI().info(
                new Info()
                        .title("SecondHand")
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService("https://swager.io/terms")
                        .contact(new Contact()
                                .name("Kelompok 5")
                                .url("https://gitlab.com/wahyuprh/finalprojetcbinar"))
                        .license(new License()
                                .name("Apache 2.1")
                                .url("https://springdocs.org"))
        );
    }
}
