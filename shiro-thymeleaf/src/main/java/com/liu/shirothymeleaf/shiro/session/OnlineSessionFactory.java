package com.liu.shirothymeleaf.shiro.session;

import com.liu.shirothymeleaf.util.IpUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;
import org.springframework.stereotype.Component;
//import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 自定义sessionFactory会话
 * @author: liurunkai
 * @Date: 2020/9/25 10:14
 */
@Component
public class OnlineSessionFactory implements SessionFactory {
    @Override
    public Session createSession(SessionContext initData)
    {
        OnlineSession session = new OnlineSession();
        if (initData != null && initData instanceof WebSessionContext)
        {
            WebSessionContext sessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
            if (request != null)
            {
//                UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
//                // 获取客户端操作系统
//                String os = userAgent.getOperatingSystem().getName();
//                // 获取客户端浏览器
//                String browser = userAgent.getBrowser().getName();
                session.setHost(IpUtils.getIpAddr(request));
//                session.setBrowser(browser);
//                session.setOs(os);
            }
        }
        return session;
    }
}
