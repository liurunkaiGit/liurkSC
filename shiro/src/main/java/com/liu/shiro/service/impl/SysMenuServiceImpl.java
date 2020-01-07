package com.liu.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.shiro.bean.system.SysMenu;
import com.liu.shiro.mapper.SysMenuMapper;
import com.liu.shiro.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/7 13:03
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    /**
     * 根据角色查询用户权限
     * @Author Sans
     * @CreateTime 2019/6/19 10:14
     * @Param  roleId 角色ID
     * @Return List<SysMenuEntity> 权限集合
     */
    @Override
    public List<SysMenu> selectSysMenuByRoleId(Long roleId) {
        return this.baseMapper.selectSysMenuByRoleId(roleId);
    }
}
