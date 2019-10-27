package com.liu.dubbo.service.impl;

import com.liu.dubbo.bean.User;
import com.liu.dubbo.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/10/27 16:35
 */
@com.alibaba.dubbo.config.annotation.Service // 暴露服务
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUserList() {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        User user = new User(id, "zs", "beijing");
        return user;
    }
}
