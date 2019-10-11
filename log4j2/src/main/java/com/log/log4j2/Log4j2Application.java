package com.log.log4j2;

import com.log.log4j2.listener.ApplicationStartedEventListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;

import java.util.Set;

@SpringBootApplication
@ComponentScan(value = "com.log.log4j2.*")
@MapperScan("com.log.log4j2.dao")
public class Log4j2Application {

    public static void main(String[] args) {
        System.setProperty("contextpath", "/log4j2");
        SpringApplication.run(Log4j2Application.class, args);
        /*SpringApplication springApplication = new SpringApplication(Log4j2Application.class);
        //Set<ApplicationListener<?>> listeners = springApplication.getListeners();
        ApplicationStartedEventListener applicationStartedEventListener = new ApplicationStartedEventListener();
        springApplication.addListeners(applicationStartedEventListener);
        springApplication.run(args);*/
    }

}
