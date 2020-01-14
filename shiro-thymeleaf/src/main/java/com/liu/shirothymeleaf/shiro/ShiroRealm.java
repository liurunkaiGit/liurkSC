package com.liu.shirothymeleaf.shiro;

import com.liu.shirothymeleaf.bean.SysUser;
import com.liu.shirothymeleaf.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 自定义realm
 * @author: liurunkai
 * @Date: 2020/1/13 18:17
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 执行授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行授权逻辑");
        // 给资源进行授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取当前登录用户，当认证(登录通过后)，认证方法会通过new SimpleAuthenticationInfo(sysUser);将用户信息返回，通过securityUtils获取用户登录信息
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        // 给资源添加授权字符串
        authorizationInfo.addStringPermission(sysUser.getPerms());
        return authorizationInfo;
    }

    /**
     * 执行认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("执行认证逻辑");
        // 编写shiro判断逻辑，判断用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser sysUser = this.sysUserService.selectUserByName(token.getUsername());
        if (sysUser == null) {
            // 用户不存在，shiro底层会抛出UnknownAccountException异常
            return null;
        }
        // 判断密码进行验证，当验证通过后通过第一个参数（sysUser）将用户信息返回，以便通过securityUtils.getSubject()来获取当前登录用户信息
        return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), sysUser.getUsername());
    }
}
