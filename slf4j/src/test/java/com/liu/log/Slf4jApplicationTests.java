package com.liu.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Slf4jApplicationTests {

    @Test
    public void contextLoads() {
        log.info("这是log4j.properties日志：info");
        log.debug("这是log4j.properties日志：debug");
        log.error("这是log4j.properties日志：error");
    }

}
