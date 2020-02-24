package com.liu.distributedSecurity.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/22 9:24
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OrderServerConfig extends ResourceServerConfigurerAdapter {

    // 资源服务id，要与授权服务里面的配置一样
    public static final String RESOURCE_ID = "res1";
    @Autowired
    private TokenStore tokenStore;

    /**
     * 令牌解析服务
     *
     * @return
     */
//    @Bean
//    public ResourceServerTokenServices tokenServices() {
//        // 创建远程令牌服务
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        // 校验的授权服务的地址验证token
//        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:8081/dsuaa/oauth/check_token");
//        // 客户端id
//        remoteTokenServices.setClientId("c1");
//        // 客户端密码
//        remoteTokenServices.setClientSecret("secret");
//        return remoteTokenServices;
//    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                // 验证令牌的服务
//                .tokenServices(tokenServices())
                .tokenStore(tokenStore)
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // access("#oauth2.hasScope('all')") 中的all要与授权服务配置文件里面客户端的scope一致
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
