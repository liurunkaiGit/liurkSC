package com.liu.shirothymeleaf.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/13 18:52
 */
@Slf4j
@Controller
public class TestController {

    /**
     * 测试页面跳转
     *
     * @return
     */
    @GetMapping("/test")
    public String toTest() {
        return "/test";
    }

}
