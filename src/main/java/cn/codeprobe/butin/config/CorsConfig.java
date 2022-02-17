package cn.codeprobe.butin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Lionido on 17/2/2022
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .maxAge(3600)
                // 解决开启Cookie时报错  allowedOrigin响应头不能为 “*”
                .allowedOriginPatterns("*")
                // 是否允许证书Cookie 不再默认开启
                .allowCredentials(false);
    }

}
