package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRequestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.basePackage("com.demo.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("用户中心API")
                    .description("用户中心相关API接口")
                    .termsOfServiceUrl("http://localhost:9002")
                    .contact(new Contact("Giles", "http://giles.org", "373205347@qq.com"))
                    .version("1.0")
                    .license("蜗牛学院")
                    .build();
    }
}
