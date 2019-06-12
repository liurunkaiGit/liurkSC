package com.liu.sc.service.impl;

import com.liu.sc.bean.User;
import com.liu.sc.dao.UserDao;
import com.liu.sc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

//    @Async
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public User getUserById(Long id) {
        log.info("这是=={}==线程执行的任务{}",Thread.currentThread().getName());
        return this.userDao.getUserById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public List<User> findUserList(User user) {
        return this.userDao.findUserList(user);
    }

    @Override
    public User addUser(User user) {
        this.userDao.addUser(user);
        return user;
    }
}
