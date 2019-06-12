package com.liu.sc.service;

import com.liu.sc.bean.User;

import java.util.List;

public interface UserService {
    /**
     * 根据用户编号获取对应的用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 查询用户集合
     * @param user
     * @return
     */
    List<User> findUserList(User user);

    User addUser(User user);

}
