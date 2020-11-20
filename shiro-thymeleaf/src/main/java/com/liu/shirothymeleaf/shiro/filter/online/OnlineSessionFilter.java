package com.liu.shirothymeleaf.shiro.filter.online;

import com.liu.shirothymeleaf.bean.SysUser;
import com.liu.shirothymeleaf.shiro.ShiroConstants;
import com.liu.shirothymeleaf.shiro.session.OnlineSession;
import com.liu.shirothymeleaf.shiro.session.OnlineSessionDAO;
import com.liu.shirothymeleaf.util.ShiroUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Description: 自定义访问控制
 * @author: liurunkai
 * @Date: 2020/9/24 16:18
 */
public class OnlineSessionFilter extends AccessControlFilter {

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject == null || subject.getSession() == null)
        {
            return true;
        }
        Session session = onlineSessionDAO.readSession(subject.getSession().getId());
        if (session != null && session instanceof OnlineSession)
        {
            OnlineSession onlineSession = (OnlineSession) session;
            request.setAttribute(ShiroConstants.ONLINE_SESSION, onlineSession);
            // 把user对象设置进去
            boolean isGuest = onlineSession.getUserId() == null || onlineSession.getUserId() == 0L;
            if (isGuest == true)
            {
                SysUser user = ShiroUtils.getSysUser();
                if (user != null)
                {
                    onlineSession.setUserId(user.getUserId());
                    onlineSession.setLoginName(user.getUsername());
//                    onlineSession.setAvatar(user.getAvatar());
//                    onlineSession.setDeptName(user.getDept().getDeptName());
//                    onlineSession.markAttributeChanged();
                }
            }

//            if (onlineSession.getStatus() == OnlineStatus.off_line)
//            {
//                return false;
//            }
        }
        return true;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
