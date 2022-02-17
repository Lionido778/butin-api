package cn.codeprobe.butin.common.security.shiro;

import cn.codeprobe.butin.common.security.filter.FilterType;
import cn.codeprobe.butin.common.security.filter.OAuth2Filter;
import cn.codeprobe.butin.common.security.realm.OAuth2Realm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Lionido on 13/2/2022
 */
@Slf4j
@Configuration
public class ShiroConfig {

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(OAuth2Realm oAuth2Realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oAuth2Realm);
        log.error("securityManager 已经注册 oAuth2Realm");
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    /**
     * 这里可能会报错，No SecurityManager accessible to the calling code ...
     * 在shiroFilter 类上加注解  @Scope("prototype")
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager, OAuth2Filter oAuth2Filter) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        filterFactoryBean.setSecurityManager(securityManager);
        log.error("shiroFilter 已经注册 oAuth2Realm");

        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put(FilterType.OAUTH2, oAuth2Filter);
        filterFactoryBean.setFilters(filterMap);

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //swagger
        map.put("/webjars/**","anon");
        map.put("/swagger-ui.html", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/v2/api-docs", "anon");
        //swagger-bootstrap-ui
        map.put("/doc.html", "anon");
        //druid
        map.put("/druid/**", "anon");
        //butin
        map.put("/user/register", "anon");
        map.put("/user/login", "anon");
        map.put("/test/**", "anon");
        map.put("/portal/**", "anon");
        map.put("/**", FilterType.OAUTH2);


        filterFactoryBean.setFilterChainDefinitionMap(map);
        return filterFactoryBean;
    }


//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }

}
