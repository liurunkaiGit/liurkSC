package com.liu.sc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(value = "com.liu.sc.*")
@MapperScan("com.liu.sc.dao")
@EnableScheduling
public class DynamicMoreScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicMoreScheduleApplication.class, args);
    }

}
