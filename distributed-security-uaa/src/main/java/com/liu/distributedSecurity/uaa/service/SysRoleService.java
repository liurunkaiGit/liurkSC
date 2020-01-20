package com.liu.distributedSecurity.uaa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.distributedSecurity.uaa.bean.SysRole;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/7 11:49
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 通过用户ID查询角色集合
     *
     * @Author Sans
     * @CreateTime 2019/6/18 18:01
     * @Param userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    List<SysRole> selectSysRoleByUserId(Long userId);
}
