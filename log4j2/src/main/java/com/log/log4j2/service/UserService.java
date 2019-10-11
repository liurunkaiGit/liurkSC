package com.log.log4j2.service;

import com.log.log4j2.bean.User;

import java.util.List;

public interface UserService {
    /**
     * 根据用户编号获取对应的用户
     * @param id
     * @return
     */
    User getUserById(Long id);

}
