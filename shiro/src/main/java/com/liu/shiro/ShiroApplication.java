package com.liu.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/7 15:49
 */
@SpringBootApplication
public class ShiroApplication {

    public static void main(String[] args) {
        System.setProperty("contextpath", "/shiro");
        SpringApplication.run(ShiroApplication.class, args);
    }
}
