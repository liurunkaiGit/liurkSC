package com.liu.sc.demo;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Description: CyclicBarrier实现所有线程都执行完再执行某一个事件
 * @Author:W_LIURUNKAI
 * @Date:2019/8/31 21:46
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("所有线程都执行完"));
        for (int i = 1; i < 6; i++) {
            final int tempI = i;
            new Thread(() -> {
                System.out.println("这是第" + tempI + "个线程");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
