package com.liu.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


/**
 * dubbo整合springBoot的方式
 * （1）使用@service注解暴露服务和@Reference注解调用服务，启动类里面需要添加@enableDubbo注解
 * （2）使用xml的配置文件，需要在启动类里面添加@ImportResource注解
 * （3）使用api的方式
 */
@EnableDubbo
//@ImportResource(locations = "classpath:consumer.xml")
@SpringBootApplication
public class DubboOrderServiceComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboOrderServiceComsumerApplication.class, args);
    }

}
