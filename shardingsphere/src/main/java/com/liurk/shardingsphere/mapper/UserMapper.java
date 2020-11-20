package com.liurk.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liurk.shardingsphere.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/7/30 10:28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
