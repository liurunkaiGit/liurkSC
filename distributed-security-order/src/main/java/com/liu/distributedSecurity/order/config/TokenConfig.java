package com.liu.distributedSecurity.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

/**
 * @Description: 令牌配置类
 * @author: liurunkai
 * @Date: 2020/1/20 9:40
 */
@Configuration
@Component
public class TokenConfig {

    // 对称秘钥，资源服务器使用该秘钥来验证
    public static final String SIGNING_KEY = "dsuaa";

    /**
     * 令牌存储策略-基于内存
     *
     * @return
     */
//    @Bean //相当于spring的xml文件，在想要使用的地方通过@Autowired注入
//    public TokenStore tokenStore() {
//        // 使用内存方式，生成普通令牌，还有JdbcTokenStore()和JwtTokenStore()
//        return new InMemoryTokenStore();
//    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(SIGNING_KEY);
        return jwtAccessTokenConverter;
    }
}
