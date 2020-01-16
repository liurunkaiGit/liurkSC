package com.liu.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.security.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/15 18:39
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
