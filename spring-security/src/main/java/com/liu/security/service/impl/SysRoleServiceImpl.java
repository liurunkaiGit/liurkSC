package com.liu.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.security.bean.SysRole;
import com.liu.security.mapper.SysRoleMapper;
import com.liu.security.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/7 13:01
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    /**
     * 通过用户ID查询角色集合
     *
     * @Author Sans
     * @CreateTime 2019/6/18 18:01
     * @Param userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    @Override
    public List<SysRole> selectSysRoleByUserId(Long userId) {
        return this.baseMapper.selectSysRoleByUserId(userId);
    }
}
