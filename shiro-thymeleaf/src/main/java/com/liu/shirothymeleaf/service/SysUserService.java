package com.liu.shirothymeleaf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.shirothymeleaf.bean.SysUser;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/14 13:47
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询实体
     *
     * @Author Sans
     * @CreateTime 2019/6/14 16:30
     * @Param username 用户名
     * @Return SysUserEntity 用户实体
     */
    SysUser selectUserByName(String username);

}
