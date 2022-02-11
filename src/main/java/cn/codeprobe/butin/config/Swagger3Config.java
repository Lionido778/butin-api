package cn.codeprobe.butin.config;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableOpenApi
public class Swagger3Config {

    @Value("${swagger.package.portal}")
    private String PORTAL_PACKAGE;       // portal controller接口所在的包

    @Value("${swagger.package.admin}")
    private String ADMIN_PACKAGE;       // admin controller接口所在的包

    @Value("${swagger.version}")
    private String VERSION;         // 当前文档的版本


    /**
     * 门户API，接口前缀：portal
     */
    @Bean
    public Docket portalApi() {
        return new Docket(DocumentationType.OAS_30)
                // 是否启用Swagger
                .enable(true)
                // 设置分组
                .groupName("前端门户")
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(portalApiInfo())
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
                .apis(RequestHandlerSelectors.basePackage(PORTAL_PACKAGE))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }


    /**
     * @return 摘要信息
     */
    private ApiInfo portalApiInfo() {

        Contact contact = new Contact("Lionido", "www.codeprobe.cn", "codeprobe@163.com");

        return new ApiInfoBuilder()  //定义Swagger页面基本信息
                // 设置标题
                .title("博客系统-门户接口文档")
                // 设置文档的描述
                .description("门户接口文档")
                // 设置文档的版本信息-> 1.0 Version information
                .version(VERSION)
                // 作者信息
                .contact(contact)
                .build();
    }


    /**
     * 管理中心API，接口前缀：admin
     */
    @Bean
    public Docket adminApi() {
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .groupName("管理中心")
                .apiInfo(adminApiInfo())
                .select()
                //哪些类中的方法会出现在Swagger上面
                .apis(RequestHandlerSelectors.basePackage(ADMIN_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());

    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("博客系统-管理中心接口文档")
                .description("管理中心接口")
                .version(VERSION)
                .build();
    }


    /**
     * 安全模式，这里指定token通过Authorization请求头传递
     * ApiKey: 用户需要输入什么参数
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", In.HEADER.toValue()));
        return apiKeyList;
    }

    /**
     * @return List<SecurityContext> 令牌上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .build());
        return securityContexts;
    }


    /**
     * AuthorizationScope()：JWT认证在Swagger中的作用域
     *
     * @return List<SecurityReference> 令牌的作用域
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }


}
