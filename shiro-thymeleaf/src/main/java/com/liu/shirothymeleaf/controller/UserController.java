package com.liu.shirothymeleaf.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/13 18:59
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @ResponseBody
    @PostMapping(value = "/add")
    public void add() {
        log.info("有用户新增权限，执行add方法");
    }

    @RequiresPermissions("sys:user:update")
    @ResponseBody
    @PostMapping(value = "/update")
    public void update() {
        log.info("有用户修改权限，执行update方法");
    }
}
