package com.liu.sc.controller;

import com.liu.sc.bean.User;
import com.liu.sc.service.UserService;
import com.liu.sc.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping(value = "/getUserById/{id}")
    public User getUserById(@PathVariable Long id){
        try {
            User user = this.userService.getUserById(id);
            log.info("user is {}",user);
            return user;
        } catch (Exception e) {
            log.error("发生异常{}",e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @ResponseBody
    @PostMapping(value = "/findUser",consumes = "application/json")
    public Response findUser(@Valid @RequestBody User user){
        List<User> userList = this.userService.findUserList(user);
        //遍历userList
        //1. 下标遍历--效率最高
        for (int i = 0;i < userList.size(); i++){
            log.info("userName is: {}",userList.get(i).getUserName());
        }
        //2. forEach遍历
        userList.forEach(user1 -> {
            log.info("name is : {}",user1.getName());
        });
        //3. stream流遍历
        userList.stream().map(user1 -> user1.getName());
        log.info("stream userName is : {}",userList.stream().map(user1 -> user1.getUserName()));
        return Response.success(userList);
    }

}
