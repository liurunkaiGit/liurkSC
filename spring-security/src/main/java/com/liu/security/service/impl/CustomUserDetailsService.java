package com.liu.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liu.security.bean.SysMenu;
import com.liu.security.bean.SysUser;
import com.liu.security.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/15 18:42
 */

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername,username);
        SysUser sysUser = this.sysUserMapper.selectOne(queryWrapper);
        if (sysUser == null) {
            log.error("账号不存在，username is {}", username);
            //如果用户不存在，返回null，由provider来抛异常，或者也可以自己抛异常，但最好不要
            return null;
        }
        // 查询用户权限并授权
        List<SysMenu> menuList = this.sysUserMapper.selectMenuByUserId(sysUser.getUserId());
        List<String> permList = menuList.stream().map(menu -> menu.getPerms()).collect(Collectors.toList());
        String[] perms = new String[permList.size()];
        permList.toArray(perms);
        UserDetails userDetails = User.withUsername(sysUser.getUsername()).password(sysUser.getPassword()).authorities(perms).build();
        return userDetails;
    }

}
