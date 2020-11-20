package com.liu.shirothymeleaf.controller;

import com.liu.shirothymeleaf.bean.SysUser;
import com.liu.shirothymeleaf.shiro.ShiroConstants;
import com.liu.shirothymeleaf.shiro.session.OnlineSession;
import com.liu.shirothymeleaf.shiro.session.OnlineSessionDAO;
import com.liu.shirothymeleaf.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/13 18:52
 */
@Slf4j
//也可以使用@RestController来代替@Controller，但是在返回字符串方法的上面需要指定返回的格式为纯文本格式，而不是json格式
// eg：@GetMapping(value = "/toLogin", produces = "text/plain;charset=utf-8")
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 跳转到用户登录页面：默认模板名以html结尾
     *
     * @return
     */
    @GetMapping("/toLogin")
    // 当controller类上面使用@RestController注解而不是@Controller注解时，返回字符串格式需要指定produces
//    @GetMapping(value = "/toLogin", produces = "text/plain;charset=utf-8")
    public String toLogin() {
        return "/login";
    }

    /**
     * 跳转到首页：默认模板名以html结尾
     *
     * @return
     */
    @GetMapping("/toIndex")
    public String toIndex() {
        return "/index";
    }

    /**
     * 跳转到未授权页面
     *
     * @return
     */
    @GetMapping("/unauth")
    public String unauth() {
        return "/unauth";
    }

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     * @param modelMap
     * @return
     */
    @PostMapping("/login")
    public String login(String userName, String password, ModelMap modelMap) {
        // 使用shiro进行认证登录操作
        // 1. 获取subject
        Subject subject = SecurityUtils.getSubject();
        // 2. 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        // 3. 执行登录方法
        try {
            // 获取登录时的jessionId
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String requestedSessionId = request.getRequestedSessionId();
            log.info("登录的sessionId是：{}",requestedSessionId);
            //执行这个方法会去ShiroRealm里面执行doGetAuthenticationInfo认证方法
            subject.login(token);
            // 如果没有异常说明登陆成功并跳转到首页
            return "redirect:/login/toIndex";
        } catch (UnknownAccountException e) {
            log.error("用户名不存在，userName is {}",userName);
            modelMap.put("msg","用户名不存在");
            //如果使用重定向，msg消息带不到登录页面
//            return "redirect:/login/toLogin";
            return "/login";
        } catch (IncorrectCredentialsException e) {
            log.error("密码错误，password is {}",password);
            modelMap.put("msg","密码错误");
            //如果使用重定向，msg消息带不到登录页面
//            return "redirect:/login/toLogin";
            return "login";
        }
    }
}
