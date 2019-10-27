package com.liu.dubbo.service.user;

import com.liu.dubbo.bean.User;

import java.util.List;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/10/27 16:31
 */
public interface UserService {

    List<User> getUserList();

    User getUserById(Long id);
}
