package com.liu.sc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 配置文件客户端controller，看是否获取到配置文件内容
 * @Author:W_LIURUNKAI
 * @Date:2019/5/27 11:51
 */
@RestController
public class ConfigClientController {

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @GetMapping("/datasourcePassword")
    public String hello(){
        return this.datasourcePassword;
    }
}
