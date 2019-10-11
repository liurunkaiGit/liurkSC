package com.log.log4j2.dao;

import com.log.log4j2.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User getUserById(Long id);
}
