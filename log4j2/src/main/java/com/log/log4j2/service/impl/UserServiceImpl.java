package com.log.log4j2.service.impl;

import com.log.log4j2.bean.User;
import com.log.log4j2.dao.UserDao;
import com.log.log4j2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

//    @Async
    @Override
    public User getUserById(Long id) {
        log.info("这是=={}==线程执行的任务{}",Thread.currentThread().getName());
        return this.userDao.getUserById(id);
    }
}
