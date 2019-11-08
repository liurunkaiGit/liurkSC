package com.liu.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.liu.dubbo.bean.User;
import com.liu.dubbo.service.order.OrderService;
import com.liu.dubbo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/10/27 16:45
 */
@Service
public class OrderServiceImpl implements OrderService {

    //@Autowired
    @Reference
    private UserService userService;

    @Override
    public User getUserInfo(Long id) {
        User user = userService.getUserById(id);
        return user;
    }
}
