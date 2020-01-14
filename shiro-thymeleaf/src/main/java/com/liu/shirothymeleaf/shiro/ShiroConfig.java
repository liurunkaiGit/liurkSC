package com.liu.shirothymeleaf.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: Shiro配置类
 * 需要创建3个对象：ShiroFilterFactoryBean、DefaultWebSecurityManager、Realm(需要自定义Realm：ShiroRealm)
 * @author: liurunkai
 * @Date: 2020/1/13 18:17
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 添加shiro内置过滤器
        /**
         * shiro内置过滤器，可以实现权限相关的拦截。常用的过滤器：
         * anon：无需认证(登录)就可以访问
         * authc：必须认证才可以访问
         * user：如果使用remeberMe的功能可以直接访问
         * perms：该资源必须得到资源权限才可以访问
         * role：该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 设置某些请求不拦截，即不需要经过认证(登录)也可以能访问：anon
        filterChainDefinitionMap.put("/test", "anon");
        filterChainDefinitionMap.put("/login/login", "anon");

        // 授权过滤器，注意：当授权拦截后，shiro会自动跳转到未授权页面
        filterChainDefinitionMap.put("/user/add", "perms[sys:user:add]");
        filterChainDefinitionMap.put("/user/update", "perms[sys:user:update]");
        // 设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/login/unauth");

        //如果没有登录默认跳转到login.jsp页面，修改默认跳转路径为login.html
        shiroFilterFactoryBean.setLoginUrl("/login/toLogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        // 默认拦截所有请求，必须经过认证(登录)才能访问：authc
        // 注意：这个拦截所有请求的这一行必须放到最后(即放到设置不拦截请求的后面，否则即便设置了某些请求不用认证，也不生效)
        filterChainDefinitionMap.put("/**", "authc");
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     *
     * @param shiroRealm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联realm
        defaultWebSecurityManager.setRealm(shiroRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 创建Realm
     *
     * @return
     */
    @Bean(name = "shiroRealm")
    public ShiroRealm getRealm() {
        return new ShiroRealm();
    }

    /**
     * thymeleaf模板引擎和shiro框架整合
     *
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
