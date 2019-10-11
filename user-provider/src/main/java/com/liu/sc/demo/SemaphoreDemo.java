package com.liu.sc.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description: java信号灯：semaphore
 * @Author:W_LIURUNKAI
 * @Date:2019/8/31 22:16
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i < 7; i++) {
            final int tempI = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("线程"+tempI+"抢到车位");
                    TimeUnit.MILLISECONDS.sleep(3000);
                    System.out.println("停车3秒钟，第"+tempI+"离开车位");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
