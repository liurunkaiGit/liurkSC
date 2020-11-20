package com.liurk.shardingsphere.controller;

import com.liurk.shardingsphere.bean.User;
import com.liurk.shardingsphere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/7/30 10:32
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/select")
    public List<User> select() {
        return userService.getUserList();
    }

    @GetMapping("/selectById")
    public User selectById(Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/insert")
    public Boolean insert(User user) {
        return userService.save(user);
    }
}
