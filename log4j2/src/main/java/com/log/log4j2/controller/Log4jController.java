package com.liu.log.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author:W_LIURUNKAI
 * @Date:2019/9/25 18:13
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class Log4jController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/testLog")
    public void testPringLog(){
        log.info("这是log4j.properties日志：info");
        log.debug("这是log4j.properties日志：debug");
        log.error("这是log4j.properties日志：error");

        logger.error("hello world");
        logger.info("hello world");
        logger.debug("hello world");
    }
}
