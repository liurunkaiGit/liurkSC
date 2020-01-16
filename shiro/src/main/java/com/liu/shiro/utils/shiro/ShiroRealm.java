package com.liu.shiro.utils.shiro;

import com.liu.shiro.bean.system.SysMenu;
import com.liu.shiro.bean.system.SysRole;
import com.liu.shiro.bean.system.SysUser;
import com.liu.shiro.enums.UserStateEnum;
import com.liu.shiro.service.SysMenuService;
import com.liu.shiro.service.SysRoleService;
import com.liu.shiro.service.SysUserService;
import com.liu.shiro.utils.ShiroUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: shiro权限匹配和账号密码匹配
 * @author: liurunkai
 * @Date: 2020/1/7 11:33
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 授权权限
     * 用户进行权限认证的时候shiro会去缓存中找，如果查不到数据，会执行这个方法去查权限，并放入缓存中
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        // 这里可以进行授权和处理
        Set<String> roleSet = new HashSet<>();
        Set<String> permSet = new HashSet<>();
        // 查询角色和权限
        List<SysRole> roleList = this.sysRoleService.selectSysRoleByUserId(sysUser.getUserId());
        for (SysRole sysRole : roleList) {
            roleSet.add(sysRole.getRoleName());
            List<SysMenu> sysMenuEntityList = sysMenuService.selectSysMenuByRoleId(sysRole.getRoleId());
            for (SysMenu sysMenuEntity : sysMenuEntityList) {
                permSet.add(sysMenuEntity.getPerms());
            }
        }
        // 将查询到的角色和权限分别设置到simpleAuthorizationInfo中
        simpleAuthorizationInfo.setRoles(roleSet);//基于角色的访问控制
        simpleAuthorizationInfo.setStringPermissions(permSet);// 基于资源的访问控制
        return simpleAuthorizationInfo;
    }

    /**
     * 身份认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        // 通过用户名判断用户是否存在，如果存在则进行认证
        // 这里可以做缓存，如果不做，shiro自己也是有时间间隔机制的，2分钟内不会重复执行该方法
        SysUser sysUser = this.sysUserService.selectUserByName(userName);
        // 判断用户是否存在
        if (sysUser == null) {
            throw new UnknownAccountException();
        }
        // 判断账号是否正常
        if (sysUser.getState() == null || sysUser.getState().equals(UserStateEnum.PROHIBIT.getState())) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getSalt()), getName());
        // 验证成功清空缓存
        ShiroUtil.deleteCache(sysUser.getUsername(), true);
        return authenticationInfo;
    }
}
