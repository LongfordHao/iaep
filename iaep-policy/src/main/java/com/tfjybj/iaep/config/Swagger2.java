package com.tfjybj.iaep.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置
 *
 * @author 刘雅雯
 * @version 1.0.0
 * @since 1.0.0 2019-1-24 11:36:03
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tfjybj.iaep.provider.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("策略器服务 Api")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeys;
    }

    //private List<SecurityContext> securityContexts() {
    //    List<SecurityContext> securityContexts = new ArrayList<>();
    //    securityContexts.add(SecurityContext.builder()
    //            .securityReferences(defaultAuth())
    //            .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
    //    return securityContexts;
    //}

    //private List<SecurityReference> defaultAuth() {
    //    AuthorizationScope authorizationScope = new AuthorizationScope(
    //            "global", "accessEverything");
    //    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    //    authorizationScopes[0] = authorizationScope;
    //    List<SecurityReference> securityReferences = new ArrayList<>();
    //    securityReferences.add(new SecurityReference("Authorization",
    //            authorizationScopes));
    //    return securityReferences;
    //}
}
