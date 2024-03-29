package cn.oc.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : Swagger2Config
 * @Author: oc
 * @Date: 2022/03/03/15:07
 * @Description: Swagger2Config 配置类
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//为当前包下的controller生成api文档
                .apis(RequestHandlerSelectors.basePackage("cn.oc.server.controller"))
                .paths(PathSelectors.any())
                .build()
//添加登录认证
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<SecurityContext> securityContexts() {
//设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/hello/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new
                AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new
                AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new
                SecurityReference("Authorization", authorizationScopes));
        return result;
    }

    private ApiInfo apiInfo() {
//设置文档信息
        return new ApiInfoBuilder()
                .title("云E办接口文档")
                .description("云E办接口文档")
                .contact(new Contact("oc",
                        "http:localhost:8081/doc.html", "oc@xxxx.com"))
                .version("1.0")
                .build();
    }

    //设置请求头信息
    private List<ApiKey> securitySchemes() {
//设置请求头信息
        List<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new
                ApiKey("Authorization", "Authorization", "header");
        result.add(apiKey);
        return result;
    }
}

