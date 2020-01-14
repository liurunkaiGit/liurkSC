package com.liu.shiro.utils.sha256;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @Description: sha-256 加密工具
 * @author: liurunkai
 * @Date: 2020/1/7 10:42
 */
@Slf4j
@NoArgsConstructor
public class SHA256Util {

    // 加密算法
    public static final String ENCRYPT_ALGORITHM = "SHA-256";
    // 循环次数
    public static final Integer CYCLE_NUM = 15;

    /**
     * 加密算法：采用sha-256和盐值加密
     *
     * @param password 密码
     * @param salt 盐值
     * @return
     */
    public static String sha256Encrypt(String password, String salt) {
        return new SimpleHash(ENCRYPT_ALGORITHM, password, salt, CYCLE_NUM).toString();
    }

    public static void main(String[] args) {
        String password = sha256Encrypt("123456", "15");
        log.info("password" + password);
    }

}
