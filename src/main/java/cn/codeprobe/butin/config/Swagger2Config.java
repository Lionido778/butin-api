package cn.codeprobe.butin.config;

import cn.codeprobe.butin.common.constant.SwaggerParam;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwaggerBootstrapUI
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Value("${swagger.package.portal}")
    private String PORTAL_PACKAGE;       // portal controller接口所在的包

    @Value("${swagger.package.admin}")
    private String ADMIN_PACKAGE;       // admin controller接口所在的包

    @Value("${swagger.package.user}")
    private String USER_PACKAGE;       // admin controller接口所在的包

    @Value("${swagger.version}")
    private String VERSION;         // 当前文档的版本

    private final Contact contact = new Contact(SwaggerParam.contact.NAME,
            SwaggerParam.contact.URL, SwaggerParam.contact.EMAIL);


    /**
     * 门户API，接口前缀：portal
     */
    @Bean
    public Docket portalApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 是否启用Swagger
                .enable(SwaggerParam.enable.OPEN)
                // 设置分组
                .groupName(SwaggerParam.group.PORTAL)
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


        return new ApiInfoBuilder()  //定义Swagger页面基本信息
                // 设置标题
                .title(SwaggerParam.title.PORTAL)
                // 设置文档的描述
                .description(SwaggerParam.description.PORTAL)
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
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(SwaggerParam.enable.OPEN)
                .groupName(SwaggerParam.group.ADMIN)
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
                .title(SwaggerParam.title.ADMIN)
                .description(SwaggerParam.description.ADMIN)
                .version(VERSION)
                .contact(contact)
                .build();
    }


    /**
     * 用户管理 API，接口前缀：user
     */
    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(SwaggerParam.enable.OPEN)
                .groupName(SwaggerParam.group.USER)
                .apiInfo(userApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(USER_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }


    /**
     * @return 摘要信息
     */
    private ApiInfo userApiInfo() {


        return new ApiInfoBuilder()
                .title(SwaggerParam.title.USER)
                .description(SwaggerParam.description.USER)
                .version(VERSION)
                .contact(contact)
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
