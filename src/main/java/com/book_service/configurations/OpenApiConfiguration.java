package com.book_service.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
@OpenAPIDefinition(info = @Info(title = "Book Service API", version = "v1", description = "Documentation of Book Service API"))
public class OpenApiConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(OpenApiConfiguration.class);
    //http://localhost:8101/v3/api-docs
    //http://localhost:8101/swagger-ui.html
    @Bean
    public OpenAPI customOpenAPI(){
        logger.info("OpenApiConfiguration from Book is initializing");
        return new OpenAPI()
                .components(new Components())
                .info(
                        new io.swagger.v3.oas.models.info.Info()
                                .title("Book Service API")
                                .version("v1")
                                .license(
                                        new License() // Aqui é conforme a organização da empresa
                                                .name("Apache 2.0")
                                                .url("http://springdoc.org")
                                )
                );
    }
}
