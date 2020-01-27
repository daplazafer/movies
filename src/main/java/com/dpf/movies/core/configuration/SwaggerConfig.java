package com.dpf.movies.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String TITLE = "Movies";
    private static final String DESCRIPTION = "Movies API";
    private static final String VERSION = "0.1";
    private static final String TOS_URL = "";
    private static final Contact CONTACT = new Contact("", "", "");
    private static final String LICENSE = "";
    private static final String LICENSE_URL = "";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dpf.movies"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(TITLE, DESCRIPTION, VERSION, TOS_URL, CONTACT, LICENSE, LICENSE_URL,
                Collections.emptyList());
    }

}
