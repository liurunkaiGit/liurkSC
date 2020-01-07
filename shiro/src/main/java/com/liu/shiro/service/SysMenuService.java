package com.liu.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.shiro.bean.system.SysMenu;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/7 11:50
 */
public interface SysMenuService extends IService<SysMenu> {

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
