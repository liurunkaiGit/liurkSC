package com.liu.distributedSecurity.uaa.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Description: 获取用户相关信息工具类
 * @author: liurunkai
 * @Date: 2020/1/17 11:57
 */
public class SecurityUtil {

    public static UserDetails getSysUser() {
        // 获取当前认证通过的用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取用户身份
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails;
    }
}
