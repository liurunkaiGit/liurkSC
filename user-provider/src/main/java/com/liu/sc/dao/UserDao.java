package com.liu.sc.dao;

import com.liu.sc.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    User getUserById(Long id);

    List<User> findUserList(User user);
}
