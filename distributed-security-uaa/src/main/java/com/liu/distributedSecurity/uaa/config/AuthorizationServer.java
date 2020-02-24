package com.liu.distributedSecurity.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @Description: 授权服务配置
 * @author: liurunkai
 * @Date: 2020/1/20 9:11
 */
@Configuration
@EnableAuthorizationServer //标记为授权服务
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    // 认证管理器，在WebSecurityConfig中重写
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 设置授权码模式的授权码如何存取，暂时采用内存
     *
     * @return
     */
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices() {
//        return new InMemoryAuthorizationCodeServices();
//    }
    // 设置授权码模式的授权码如何存取，使用数据库库
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 用来配置令牌端点的安全约束
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // /oauth/token_key这个endpoint当使用jwtToken且使用非对称加密时，资源服务用于获取
                .tokenKeyAccess("permitAll()")
                // /oauth/check_token这个endpoint完全公开
                .checkTokenAccess("permitAll()")
                // 允许表单认证
                .allowFormAuthenticationForClients();
    }

    /**
     * 将客户端信息存储到数据库
     *
     * @param dataSource
     * @return
     */
    @Bean
    public ClientDetailsService clientDetailsService2(DataSource dataSource) {
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return jdbcClientDetailsService;
    }

    /**
     * 用来配置客户端详情服务(ClientDetailsService)，客户端详情信息在这里进行初始化，
     * 你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 从数据库读取
        clients.withClientDetails(clientDetailsService);
        // 暂时使用内存(inMemory)方式，todo：应该把配置信息放到数据库
//        clients.inMemory()
//                // 配置客户端id
//                .withClient("c1")
//                // 配置客户端秘钥，客户端需要通过客户端id和秘钥来申请令牌(token)
//                .secret(new BCryptPasswordEncoder().encode("secret"))
//                // 客户端可以访问的资源列表
//                .resourceIds("res1")
//                /**
//                 * oauth2支持的5中授权类型："authorization_code","password","client_credentials","implicit","refresh_token"
//                 * 该客户端支持的授权类型
//                 */
//                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
//                // 允许的授权范围，相当于客户端的权限
//                .scopes("all")
//                // false表示跳转到授权页面，让用户授权，true直接发令牌
//                .autoApprove(false)
//                // 客户端回调地址
//                .redirectUris("http://www.baidu.com");
    }

    /**
     * 令牌服务(token services)
     *
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 客户端信息服务
        tokenServices.setClientDetailsService(clientDetailsService);
        // 是否支持刷新令牌
        tokenServices.setSupportRefreshToken(true);
        // 令牌存储策略
        tokenServices.setTokenStore(tokenStore);
        // 令牌默认有效期2小时
        tokenServices.setAccessTokenValiditySeconds(7200);
        // 刷新令牌默认有效期2天
        tokenServices.setRefreshTokenValiditySeconds(7200);
        // 令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        tokenServices.setTokenEnhancer(tokenEnhancerChain);
        return tokenServices;
    }

    /**
     * 用来配置令牌(token)的访问端点
     * /oauth/authorize：授权端点
     * /oauth/token：令牌端点
     * /oauth/confirm_access：用户确认授权提交端点
     * /oauth/error：授权服务错误信息端点
     * /oauth/check_token：用于资源服务访问的令牌解析端点
     * /oauth/token_key：提供公有秘钥的端点，如果你是用JWT令牌的话
     * 需要注意的是授权端点这个url应该被springSecurity保护起来只供授权用户访问
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager) //密码模式需要
                .authorizationCodeServices(authorizationCodeServices) // 授权码模式需要
                .tokenServices(tokenServices()) //令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); //允许post提交访问令牌
    }

}
