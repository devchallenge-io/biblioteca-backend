package com.biblioteca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.biblioteca")).paths(PathSelectors.any())
                .build().apiInfo(metadata()).useDefaultResponseMessages(false);
    }

    public static ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("API - Desafio back-end Biblioteca")
                .description("Desafio de criar o backend para um sistema de gerenciamento de uma biblioteca!")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/abnerwerley")
                .contact(contact()).build();
    }

    private static Contact contact() {
        return new Contact("Abner Werley Silva", "https://github.com/abnerwerley", "abnerwerley77@gmail.com");
    }
}
