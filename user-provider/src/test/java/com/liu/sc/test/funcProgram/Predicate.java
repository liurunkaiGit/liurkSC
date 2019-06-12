package com.liu.sc.test.funcProgram;

import com.liu.sc.bean.User;
import com.liu.sc.common.BaseTest;
import com.liu.sc.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: predicate<T>接口，返回类型boolean
 * @Author:W_LIURUNKAI
 * @Date:2019/6/6 16:41
 * 源码：
 * public interface Predicate<T>{
 *     boolean test(T t);
 * }
 */
public class Predicate extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void testPredicate(){
        User user = this.userService.getUserById(4l);
        //Predicate<Integer> atLeast5 = x -> x > 5;
    }
}
