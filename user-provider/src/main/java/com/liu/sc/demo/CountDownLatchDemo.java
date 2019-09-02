package com.liu.sc.demo;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: CountDownLatch实现所有线程等待某个事件执行完才执行或者某个事件等待所有线程执行完才执行
 * @Author:W_LIURUNKAI
 * @Date:2019/8/31 21:13
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            final int tempI = i;
            new Thread(() -> {
                System.out.println("线程"+tempI+"执行");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
            System.out.println("mail线程执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
