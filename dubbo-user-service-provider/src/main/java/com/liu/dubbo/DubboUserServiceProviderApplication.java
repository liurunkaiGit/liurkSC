package com.liu.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class DubboUserServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboUserServiceProviderApplication.class, args);
    }

}
