package com.liu.security.controller;

import com.liu.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/15 18:43
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 跳转到用户登录页面：默认模板名以html结尾
     *
     * @return
     */
    @GetMapping(value = "/toLogin")
    public String toLogin() {
        return "/login";
    }

    /**
     * 跳转到首页：默认模板名以html结尾
     *
     * @return
     */
    @GetMapping("/toIndex")
    public String toIndex() {
        return "/index";
    }

    /**
     * 跳转到未授权页面
     *
     * @return
     */
    @GetMapping("/unauth")
    public String unauth() {
        return "/unauth";
    }

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     * @param modelMap
     * @return
     */
    @PostMapping("/login")
    public String login(String userName, String password, ModelMap modelMap) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
        return null;
    }
}
