package com.liu.shirothymeleaf.config;

import com.liu.shirothymeleaf.shiro.filter.kickout.KickoutSessionControlFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/9/25 17:35
 */
@Configuration
public class ShiroFilterRegisterConfig {
    @Bean
    public FilterRegistrationBean shiroLoginFilteRegistration(KickoutSessionControlFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}
