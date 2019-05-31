package com.liu.sc.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.rmi.registry.Registry;

/**
 * @Description: springboot拦截器配置类
 * @Author:W_LIURUNKAI
 * @Date:2019/5/30 18:09
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public HandlerInterceptor getInterceptor(){
        return new LoginInterceptor();
    }

    public void addInterceptor(InterceptorRegistry interceptorRegistry){
        //不拦截的访问路径
        String[] urlArry={"/login","/register"};
        //addInterceptor用于添加拦截器
        //addPathPatterns用于添加拦截规则，这里拦截url后面的全部链接
        //excludePathPatterns用户排除拦截
        interceptorRegistry.addInterceptor(getInterceptor()).addPathPatterns("/**").excludePathPatterns(urlArry);
        super.addInterceptors(interceptorRegistry);
    }
}
