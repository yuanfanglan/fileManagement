package com.yfl.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2//重要！
@EnableWebMvc
@ComponentScan(basePackages = "com.yfl.web.controller")//扫描control所在的package请修改为你control所在package
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("家庭私有云项目接口文档")
                .description("")
                .version("1.0.0")
                .termsOfServiceUrl("http://192.168.0.150:8089/fileManagement/swagger-ui.html")
                .license("")
                .licenseUrl("")
                .build();
    }
}