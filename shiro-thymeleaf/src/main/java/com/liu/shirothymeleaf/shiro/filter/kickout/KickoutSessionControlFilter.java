package com.liu.shirothymeleaf.shiro.filter.kickout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liu.shirothymeleaf.bean.AjaxResult;
import com.liu.shirothymeleaf.bean.SysUser;
import com.liu.shirothymeleaf.shiro.ShiroConstants;
import com.liu.shirothymeleaf.util.ServletUtils;
import lombok.Data;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 自定义filter 实现 并发登录控制
 * 登录帐号控制过滤器，同一个账号并发登录控制
 * 思路：
 * 1. 读取当前登录用户名，默认在缓存中的sessionId队列
 * 2. 判断队列的长度，大于最大登录限制的时候，按踢出规则
 * 将之前的sessionId中的sessionId域中存入kickout：true，并更新队列缓存
 * 3. 判断当前登录的sessionId域中的kickout如果为true，则将其做退出登录处理，然后再重定向到踢出登陆提示页面
 *
 * @author liurnkai
 */
public class KickoutSessionControlFilter extends AccessControlFilter {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 同一个用户最大会话数,-1不限制，默认为1
     **/
    private int maxSession;
    /**
     * 踢出之前登录的/之后登录的用户 默认false踢出之前登录的用户
     **/
    private Boolean kickoutAfter = false;
    /**
     * 踢出后到的地址
     **/
    private String kickoutUrl;
    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setKickoutAfter(Boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    // 设置Cache的key的前缀
    public void setCacheManager(CacheManager cacheManager) {
        // 必须和ehcache缓存配置中的缓存name一致
        this.cache = cacheManager.getCache(ShiroConstants.SYS_USERCACHE);
    }

    /**
     * 是否允许访问，true表示允许，继续执行下一个过滤器，如果没有下一个过滤器，表示具有了访问某个资源的权限，
     *               如果返回false，则会调用onAccessDenied方法，去实现当过滤不通过时执行的方法
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    /**
     * 表示访问拒绝时是否自己处理，如果返回true表示自己不拦截且继续过滤器链执行，返回false表示自己已经处理了（比如重定向到另一个页面），不会再次走到下一个过滤器
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered() || maxSession == -1) {
            // 如果没有登录或用户最大会话数为-1，直接进行之后的流程
            return true;
        }
        Session session = subject.getSession();
        // 当前登录用户
//            SysUser user = ShiroUtils.getSysUser();
        SysUser user = (SysUser) subject.getPrincipal();
        String loginName = user.getUsername();
        Serializable sessionId = session.getId();

        // 读取缓存用户 没有就存入
        Deque<Serializable> deque = cache.get(loginName);
        if (deque == null) {
            // 初始化队列
            deque = new ArrayDeque<Serializable>();
        }

        // 如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            // 将sessionId存入队列
            deque.push(sessionId);
            // 将用户的sessionId队列缓存
            cache.put(loginName, deque);
        }

        // 如果队列里的sessionId数超出最大会话数，开始踢人
        while (deque.size() > maxSession) {
            Serializable kickoutSessionId = null;
            // 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；
            if (kickoutAfter) {
                // 踢出后者
                kickoutSessionId = deque.removeFirst();
            } else {
                // 踢出前者
                kickoutSessionId = deque.removeLast();
            }
            // 踢出后再更新下缓存队列
            cache.put(loginName, deque);

            try {
                // 获取被踢出的sessionId的session对象
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if (null != kickoutSession) {
                    // 设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute("kickout", true);
                }
            } catch (Exception e) {
                // 面对异常，我们选择忽略
            }
        }

        //如果被踢出了，直接退出，重定向到踢出后的地址
//        if ((Boolean) session.getAttribute("kickout") != null && (Boolean) session.getAttribute("kickout") == true) {
//            //会话被踢出了
//            try {
//                //退出登录
//                subject.logout();
//            } catch (Exception e) { //ignore
//            }
//            saveRequest(request);
//            //重定向
//            WebUtils.issueRedirect(request, response, kickoutUrl);
//            return false;
//        }
//        return true;

        // 如果被踢出了，(前者或后者)直接退出，重定向到踢出后的地址
        if ((Boolean) session.getAttribute("kickout") != null && (Boolean) session.getAttribute("kickout") == true) {
            // 退出登录
            subject.logout();
            saveRequest(request);
            return isAjaxResponse(request, response);
        }
        return true;
    }

    private boolean isAjaxResponse(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (ServletUtils.isAjaxRequest(req)) {
            AjaxResult ajaxResult = AjaxResult.error("您已在别处登录，请您修改密码或重新登录");
            ServletUtils.renderString(res, objectMapper.writeValueAsString(ajaxResult));
        } else {
            WebUtils.issueRedirect(request, response, kickoutUrl);
        }
        return false;
    }
}
