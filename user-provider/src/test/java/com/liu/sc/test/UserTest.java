package com.liu.sc.test;

import com.liu.sc.bean.User;
import com.liu.sc.common.BaseTest;
import com.liu.sc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.rmi.runtime.Log;

import javax.sound.midi.Soundbank;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/5/30 15:31
 */
@Slf4j
public class UserTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void testThread(){
        log.info("==================start test==========");
        User userById = userService.getUserById(1l);
        log.info("===========end test ===============");
    }

    @Test
    public void test(){
        String str1 = "i";
        String str2 = "i";
        String str3 = new String(str1);
        String str4 = new String(str1);
        String str5 = new String("i");
        System.out.println(str1.equals(str2));
        System.out.println(str1.equals(str3));
        System.out.println(str3.equals(str4));
        System.out.println(str1.equals(str5));
    }
}
