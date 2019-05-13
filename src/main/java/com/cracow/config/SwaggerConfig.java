package com.cracow.config;//package com.cracow.config;


import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2

public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.cracow.controllers"))
                //.paths(PathSelectors.ant("/api/**"))
                .paths(getSwaggerPaths())
                .build()
                //Global messages
                .globalResponseMessage(RequestMethod.GET, newArrayList(
                        new ResponseMessageBuilder()
                                .code(200)
                                .message("200 : OK")
                                .build(),
                        new ResponseMessageBuilder()
                                .code(400)
                                .message("400 : Bad Request!")
                                .build(),
                        new ResponseMessageBuilder()
                                .code(401)
                                .message("401 : Unauthorized!")
                                .build(),
                        new ResponseMessageBuilder()
                                .code(403)
                                .message("403 : Forbidden!")
                                .build(),
                        new ResponseMessageBuilder()
                                .code(404)
                                .message("404 : Not Found!")
                                .build(),
                        new ResponseMessageBuilder()
                                .code(409)
                                .message("409 : Conflict!")
                                .build(),
                        new ResponseMessageBuilder()
                                .code(500)
                                .message("500 : Internal Server Error!")
                                .build(),
                        new ResponseMessageBuilder()
                                .code(502)
                                .message("502 : Bad Gateway!")
                                .build()

                        )
                );
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("\"API of the page-pocket project.\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Project-Zaczek", "www.example.com", "myeaddress@company.com"))
                .build();
    }

    private Predicate<String> getSwaggerPaths() {
        return or(
                regex("/api.*"),
                regex("/test.*"));
    }


}