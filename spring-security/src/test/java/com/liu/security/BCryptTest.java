package com.liu.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/1/17 10:12
 */
@Slf4j
public class BCryptTest {

    @Test
    public void testBCrypt() {
        // 加密：对同一个密码每次加密后生成的密码(bCryptPassword)不一样
        String bCryptPassword = BCrypt.hashpw("123456", BCrypt.gensalt());
        log.info("bCryptPassword is {}", bCryptPassword);
        // 密码比对
        boolean checkpw = BCrypt.checkpw("123456", bCryptPassword);
        log.info("checkpw is {}", checkpw);
    }
}
