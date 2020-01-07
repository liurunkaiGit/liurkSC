package com.liu.shiro.utils.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 * @Description: 自定义sessionId生成器
 * @author: liurunkai
 * @Date: 2020/1/7 11:25
 */
public class ShiroSessionIdGenerator implements SessionIdGenerator {

    /**
     * 自定义sessionId生成器
     *
     * @param session
     * @return
     */
    @Override
    public Serializable generateId(Session session) {
        Serializable sessionId = new JavaUuidSessionIdGenerator().generateId(session);
        return String.format("login_token_%s", sessionId);
    }
}
