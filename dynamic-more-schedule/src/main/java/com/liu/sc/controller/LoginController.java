package com.liu.sc.controller;

import com.liu.sc.bean.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/5/30 17:15
 */
@RestController
public class LoginController {

    @PostMapping(value = "/login", consumes = "application/json")
    public String login(@RequestBody User user, HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (user.getName().equals("root") && user.getPwd().equals("root")) {
            user.setName(user.getUserName());
            user.setPwd(user.getPwd());
            session.setAttribute("user", user);
            return "登录成功";
        } else {
            return "用户名或密码错误!";
        }
    }
}
