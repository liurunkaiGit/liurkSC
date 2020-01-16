package com.liu.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.security.bean.SysUser;
import com.liu.security.mapper.SysUserMapper;
import com.liu.security.service.SysUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/15 18:42
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService, UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername,username);
        SysUser sysUser = this.baseMapper.selectOne(queryWrapper);
        if(sysUser == null) {
            throw new UsernameNotFoundException("账号不存在");
        }
        return null;
    }

}
