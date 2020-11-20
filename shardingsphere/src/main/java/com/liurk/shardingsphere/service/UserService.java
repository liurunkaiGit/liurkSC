package com.liurk.shardingsphere.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liurk.shardingsphere.bean.User;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/7/30 10:28
 */
public interface UserService extends IService<User> {
    /**
     * 保存用户信息
     * @param entity
     * @return
     */
    @Override
    boolean save(User entity);

    /**
     * 查询全部用户信息
     * @return
     */
    List<User> getUserList();

    User getUserById(Long id);
}
