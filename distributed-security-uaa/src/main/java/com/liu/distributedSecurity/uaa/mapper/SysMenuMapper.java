package com.liu.distributedSecurity.uaa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.distributedSecurity.uaa.bean.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/7 11:56
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据角色查询用户权限
     *
     * @Author Sans
     * @CreateTime 2019/6/19 10:14
     * @Param roleId 角色ID
     * @Return List<SysMenuEntity> 权限集合
     */
    List<SysMenu> selectSysMenuByRoleId(Long roleId);
}
