package com.liu.shirothymeleaf.shiro.filter.online;

import com.liu.shirothymeleaf.shiro.ShiroConstants;
import com.liu.shirothymeleaf.shiro.session.OnlineSession;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/9/24 15:48
 */
public class SyncOnlineSessionFilter extends PathMatchingFilter {

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        OnlineSession session = (OnlineSession) request.getAttribute(ShiroConstants.ONLINE_SESSION);
        return super.onPreHandle(request, response, mappedValue);
    }
}
