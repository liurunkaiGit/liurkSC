package com.liu.distributedSecurity.uaa.controller;

import com.liu.distributedSecurity.uaa.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/16 15:53
 */
@Slf4j
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

    @ResponseBody
    @PostMapping(value = "/add")
    // 基于方法的授权，拥有sys:user:add才可以访问
    @PreAuthorize("hasAuthority('sys:user:add')")
    public void add() {
        UserDetails userDetails = SecurityUtil.getSysUser();
        log.info("有用户新增权限，执行add方法");
    }

    @ResponseBody
    @PostMapping(value = "/update")
    // 基于方法的授权，拥有sys:user:update才可以访问
    @PreAuthorize("hasAuthority('sys:user:update')")
    public void update() {
        log.info("有用户修改权限，执行update方法");
    }
}
