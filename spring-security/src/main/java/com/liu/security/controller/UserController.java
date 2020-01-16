package com.liu.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/16 15:53
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 跳转到首页：默认模板名以html结尾
     *
     * @return
     */
    @GetMapping("/toIndex")
    public String toIndex() {
        return "/index";
    }
}
