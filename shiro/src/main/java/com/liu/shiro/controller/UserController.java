package com.liu.shiro.controller;

import com.liu.shiro.bean.system.SysUser;
import com.liu.shiro.utils.ShiroUtil;
import com.liu.shiro.utils.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 用户
 * @author: liurunkai
 * @Date: 2020/1/7 15:34
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping(value = "/login", consumes = "application/json")
    public Response login(@RequestBody SysUser sysUser) {
        try {
            // 验证身份和登录
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());
            // 验证成功进行登录操作
            subject.login(token);
        } catch (AuthenticationException e) {
            log.error("异常，error is {}", e);
            throw new AuthenticationException();
        }
        return Response.success(ShiroUtil.getSession().getId().toString());
    }

    /**
     * 未登录
     *
     * @Author Sans
     * @CreateTime 2019/6/20 9:22
     */
    @RequestMapping("/unauth")
    public Response unauth() {
        return Response.fail(300, "未登录");
    }
}
