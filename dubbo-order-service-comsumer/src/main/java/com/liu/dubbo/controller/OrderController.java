package com.liu.dubbo.controller;

import com.liu.dubbo.bean.User;
import com.liu.dubbo.service.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/10/27 16:48
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ResponseBody
    @GetMapping(value = "/getUserInfo")
    public User getUserInfo(Long id){
        User userInfo = orderService.getUserInfo(id);
        return userInfo;
    }

}
