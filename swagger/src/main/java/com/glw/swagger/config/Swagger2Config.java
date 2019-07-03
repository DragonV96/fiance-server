package com.glw.swagger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : glw
 * @date : 2019/7/3
 * @time : 14:35
 * @Description : swagger配置文件
 */
@Configuration
@ComponentScan(basePackages = "com.glw.swagger")
@EnableSwagger2
public class Swagger2Config {

    @Autowired
    private SwaggerInfo swaggerInfo;

    @Bean
    public Docket createRestApi() {
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder clientParam = new ParameterBuilder();
        clientParam.name("clientId")
                .description("客户端ID")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .order(1);
        parameters.add(clientParam.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(swaggerInfo.getGroupName())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerInfo.getBasePackage()))
                .paths(PathSelectors.ant(swaggerInfo.getAntPah()))
                .build()
                .globalOperationParameters(parameters).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(swaggerInfo.getTitle())
                .description(swaggerInfo.getDescription())
                .termsOfServiceUrl("http://localhost:8080/")
                .contact(new Contact("glw", "", ""))
                .version("1.0")
                .build();
    }
}
