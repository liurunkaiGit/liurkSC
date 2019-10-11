package com.log.log4j2.controller;

import com.log.log4j2.bean.User;
import com.log.log4j2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping(value = "/getUserById/{id}")
    public User getUserById(@PathVariable Long id){
        log.info("info start getUserById");
        try {
            User user = this.userService.getUserById(id);
            log.info("info user is {}",user);
            return user;
        } catch (Exception e) {
            log.error("发生异常{}",e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
