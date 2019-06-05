package com.liu.sc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: springboot拦截器
 * @Author:W_LIURUNKAI
 * @Date:2019/5/30 18:06
 * 实现HandlerInterceptor接口
 * 拦截器配置类继承WebMvcConfigurerAdapter
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("user") != null){
            return true;
        }else {
            response.sendRedirect("/user/login");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest requt, HttpServletResponse rponse, Object handler, Exception ex) throws Exception {

    }
}
