package com.alexfaster.project.config.swagger;

import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(
                        new ApiInfo(
                                "Task Planner",
                                "Task Planner desc",
                                "v1",
                                "http://galerasoftware.com",
                                new Contact(
                                        "galerasoftwareltd",
                                        "http://galerasoftware.com",
                                        "galerasoftwareltd@gmail.com"
                                ),
                                "License",
                                "http://l2.com",
                                new ArrayList<>()
                        )
                );
    }
}
