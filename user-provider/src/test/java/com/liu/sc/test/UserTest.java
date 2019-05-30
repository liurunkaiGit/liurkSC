package com.liu.sc.test;

import com.liu.sc.bean.User;
import com.liu.sc.common.BaseTest;
import com.liu.sc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.rmi.runtime.Log;

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
}
