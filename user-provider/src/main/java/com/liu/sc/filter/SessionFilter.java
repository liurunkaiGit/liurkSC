package com.liu.sc.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 过滤器实现登录拦截
 * @Author:W_LIURUNKAI
 * @Date:2019/5/30 17:19
 * 过滤器的使用
 * 1：通过注解
 * 在过滤器上添加WebFilter注解@WebFilter(filterName = "sessionFilter",urlPatterns = {"/*"})
 * 在启动类上添加ServletComponentScan注解@ServletComponentScan
 * 2：通过配置类
 */
@Slf4j
public class SessionFilter implements Filter {

    private static final String NO_LOGIN = "please login";

    //不需要登录就可以访问的路径(比如:注册登录等)
    String[] includeUrls = new String[]{"/login","register"};


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        log.info("path {}",request.getRequestURI());
        //是否需要过滤
        boolean flag = isNeedFilter(request.getRequestURI());
        if(flag){
            //需要过滤
            if(session != null && session.getAttribute("user") != null){
                //说明是登录状态，不需要拦截
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                //servletRequest.getRequestDispatcher("/failed").forward(servletRequest, servletResponse);
                String requestType = request.getHeader("X-Requested-With");
                //判断是否是ajax请求
                if(requestType!=null && "XMLHttpRequest".equals(requestType)){
                    response.getWriter().write(SessionFilter.NO_LOGIN);
                }else{
                    //重定向到登录页(需要在static文件夹下建立此html文件)
                    //response.sendRedirect(request.getContextPath()+"/user/login.html");
                    response.getWriter().write(SessionFilter.NO_LOGIN);
                }
            }
        }else{
            //不需要拦截,传给下一个过滤器
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    /**
     * 是否需要过滤
     * @param url
     * @return
     */
    private boolean isNeedFilter(String url) {
        for(String str : includeUrls){
            if(str.equals(url)){
                return false;
            }
        }
        return true;
    }

    @RequestMapping("/failed")
    public Map<String, String> requestFailed() {

        Map<String, String> map = new HashMap<>();
        map.put("code", "-1");
        map.put("msg", "请求错误");
        return map;
    }

    @Override
    public void destroy() {

    }
}
