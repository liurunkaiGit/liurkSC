package com.liurk.shardingsphere.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liurk.shardingsphere.bean.User;
import com.liurk.shardingsphere.mapper.UserMapper;
import com.liurk.shardingsphere.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/7/30 10:31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public List<User> getUserList() {
        return baseMapper.selectList(Wrappers.<User>lambdaQuery());
    }

    @Override
    public User getUserById(Long id) {
        return baseMapper.selectById(id);
    }
}
