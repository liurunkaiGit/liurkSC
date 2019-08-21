package com.liu.sc.test;

import com.liu.sc.utils.enumUtils.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Description: 测试不需要spring环境
 * @Author:W_LIURUNKAI
 * @Date:2019/8/21 10:40
 */
@Slf4j
public class TestNoWithSpring {

    @Test
    public void testSwithchJava12() {
        StatusEnum statusEnum = StatusEnum.DEL;
        Integer status = 0;
        // java12之前的用法
        switch (statusEnum) {
            case DEL:
                status = StatusEnum.DEL.getStatus();
                break;
            case NODEL:
                status = StatusEnum.NODEL.getStatus();
                break;
            default:
                throw new RuntimeException("status not exist");
        }
        log.info("java8 status is {}", status);
        // java12 用法,可以省略break
        /*var status12 = switch(statusEnum){
            case NODEL -> StatusEnum.NODEL.getStatus();
            case DEL -> StatusEnum.DEL.getStatus();
            // 多个条件的使用
            case DEL,NODEL -> 0;
            default -> throw new RuntimeException("status not exist");
        }
        log.info("java12 status12 is {}", status12);*/
    }
}
