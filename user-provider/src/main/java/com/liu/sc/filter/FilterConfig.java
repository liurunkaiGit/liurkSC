package com.liu.sc.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/5/30 17:36
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        //新建过滤器注册类
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //添加过滤器
        filterRegistrationBean.setFilter(new SessionFilter());
        //设置过滤器的url模式
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
