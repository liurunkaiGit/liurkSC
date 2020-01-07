package com.liu.shiro.utils;

import com.liu.shiro.bean.system.SysUser;
import com.liu.shiro.utils.spring.SpringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;

import java.util.Collection;
import java.util.Objects;

/**
 * @Description: shiro工具类
 * @author: liurunkai
 * @Date: 2020/1/7 10:59
 */
public class ShiroUtil {

    private static RedisSessionDAO redisSessionDAO = SpringUtil.getBean(RedisSessionDAO.class);

    /**
     * 获取当前用户session
     *
     * @return
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 用户退出登录
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    public static SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 删除用户缓存信息
     *
     * @param userName          用户名称
     * @param isRemoveSession 是否删除session信息
     */
    public static void deleteCache(String userName, boolean isRemoveSession) {
        // 从缓存中获取session
        Session session = null;
        Collection<Session> activeSessions = redisSessionDAO.getActiveSessions();
        Object attribute = null;
        SysUser sysUser = null;
        for (Session activeSession : activeSessions) {
            // 遍历activeSessions，找到该用户名对应的session
            attribute = activeSession.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (attribute == null) {
                continue;
            }
            sysUser = (SysUser) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
            if (sysUser == null) {
                continue;
            }
            if (Objects.equals(sysUser.getUsername(), userName)) {
                session = activeSession;
                break;
            }
        }
        if (session == null || attribute == null) {
            return;
        }
        // 删除session
        if (isRemoveSession) {
            redisSessionDAO.delete(session);
        }
        // 删除cache，在访问受限接口时会重新授权
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        Authenticator authenticator = securityManager.getAuthenticator();
        ((LogoutAware) authenticator).onLogout((SimplePrincipalCollection) attribute);
    }

}
