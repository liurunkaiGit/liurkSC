package com.liu.dubbo.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/10/28 20:09
 */
@Configuration
public class dubboConfig {

    @Bean
    public ApplicationConfig getApplicationConfig(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig getRegistryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        return registryConfig;
    }

}
