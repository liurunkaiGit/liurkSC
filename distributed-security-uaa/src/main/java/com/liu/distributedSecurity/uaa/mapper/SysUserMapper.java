package com.liu.distributedSecurity.uaa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.distributedSecurity.uaa.bean.SysMenu;
import com.liu.distributedSecurity.uaa.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/7 11:53
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysMenu> selectMenuByUserId(Long userId);
}
