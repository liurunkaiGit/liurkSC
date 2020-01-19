package com.liu.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/15 18:05
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启方法权限控制
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 定义查询用户信息，实现用户认证功能
     * 到时候会重写UserDetailsService来从数据库获取用户信息
     * @return
     */
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        userDetailsManager.createUser(User.withUsername("zhangsan").password("123").authorities("r1").build());
//        return userDetailsManager;
//    }

    /**
     * 密码编码器
     * NoOpPasswordEncoder：不加密，明文，已弃用
     * BCryptPasswordEncoder：采用BCrypt加密方式，对同一个密码加密后会生成不同的加密密码
     *
     * @return
     */
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder () {
//        return new BCryptPasswordEncoder();
//    }

    /**
     * 安全拦截机制：从上到下执行
     *      一般把细节放前面，eg：.antMatchers("/user/add").hasAuthority("sys:user:add")
     *      把泛型放后面，eg：.anyRequest().permitAll()
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeRequests()
//                .antMatchers("/css/**", "/js/**").permitAll()
//                /**
//                 * 当点击登录按钮的时候需要把登录地址放开
//                 */
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated()
//                /**
//                 * loginPage：配置跳转登录页的地址，直接访问localhost:8080即可跳转到登录页
//                 *            相当于访问localhost:8080/toLogin
//                 */
//                .and().formLogin().loginPage("/toLogin").permitAll()
//                .and().logout().permitAll();
        httpSecurity.authorizeRequests()
                //.antMatchers("/user/add").hasAuthority("sys:user:add") //基于web授权，一般很少使用，都是基于方法授权
                .anyRequest().authenticated() // 所有的请求都需要经过登录认证
                /**
                 * formLogin：允许表单登录
                 * loginPage：配置跳转登录页的地址，直接访问localhost:8081即可跳转到登录页，相当于访问localhost:8080/toLogin
                 *            permitAll()必须加，否则跳转不到登录页面
                 *            .loginPage("/toLogin").permitAll()
                 * successForwardUrl：自定义登录成功的页面地址
                 * loginProcessingUrl：登录地址
                 *      当login.html登录页面form表单使用action="/login"进行登录时需要配置.loginProcessingUrl("/login")
                 *      当login.html登录页面form表单使用th:action="@{/login}"进行登录时则不需要配置.loginProcessingUrl("/login")
                 */
                .and().formLogin().successForwardUrl("/toIndexDefalut").failureUrl("/toError")
                .and().logout().logoutSuccessUrl("/toLogin");
        // csrf：跨域请求
        httpSecurity.csrf().disable();
    }

}
