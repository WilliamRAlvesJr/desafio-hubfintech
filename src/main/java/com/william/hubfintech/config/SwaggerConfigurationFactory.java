package com.william.hubfintech.config;

import com.google.common.collect.Lists;
import com.google.common.net.HttpHeaders;
import io.swagger.models.auth.In;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

public abstract class SwaggerConfigurationFactory {

    private SwaggerConfigurationFactory() {
        //
    }

    private static final String BASE_PACKAGE = "com.william";
    private static final String REFERENCE = "JWT";

    public static Docket createConfigurationDefault() {

        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private static List<SecurityReference> securityReference() {

        return Collections.singletonList(SecurityReference.builder()
                .reference(REFERENCE)
                .scopes(new AuthorizationScope[0])
                .build());
    }

    private static ApiKey apiKey() {

        return new ApiKey(REFERENCE, HttpHeaders.AUTHORIZATION, In.HEADER.toValue());
    }

    private static SecurityContext securityContext() {

        return SecurityContext.builder()
                .securityReferences(securityReference())
                .forPaths(PathSelectors.any())
                .build();
    }

}
