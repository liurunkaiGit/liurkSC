package com.liu.sc.demo;

import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: CAS
 * 1. CAS概念
 *  比较交换：compareAndSet
 * @Author:W_LIURUNKAI
 * @Date:2019/8/25 12:26
 */
@Slf4j
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        // 两个参数expect：预期值，update：要替换的值，返回boolean，如果返回true，说明修改成功
        boolean b = atomicInteger.compareAndSet(5, 50);
        log.info("b is {},atomicInterger is {}",b,atomicInteger.get()); // 返回true，返回50
        boolean b1 = atomicInteger.compareAndSet(5, 500);
        log.info("b1 is {},atomicInterger is {}",b1,atomicInteger.get()); // 返回false，返回50
    }
}
