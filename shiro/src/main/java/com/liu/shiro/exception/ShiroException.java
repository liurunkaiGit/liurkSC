package com.liu.shiro.exception;

import com.liu.shiro.utils.response.Response;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description: 自定义异常处理shiro权限拦截异常
 * @author: liurunkai
 * @Date: 2020/1/7 10:22
 */
// @ControllerAdvice 使用这个注解需要在对应的方法上面添加@ResponseBody注解
/*使用这个注解不需要在对应的方法上面添加@ResponseBody注解，但是保证所有方法都返回json格式的数据时才可以使用该注解，
  如果有需要返回字符串的，则不能使用，需要使用上面那种方式*/
@RestControllerAdvice
public class ShiroException {

    @ExceptionHandler(value = AuthorizationException.class)
    public Response noPermissionException() {
        return Response.fail(403, "权限不足");
    }
}
