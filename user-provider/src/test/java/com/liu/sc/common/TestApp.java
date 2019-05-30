package com.liu.sc.common;

import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.liu.sc.dao")
@ComponentScan(value = "com.liu.sc.*")
public class TestApp {
    public static void main(String[] args) {
        SpringApplication.run(TestApp.class,args);
    }
}
