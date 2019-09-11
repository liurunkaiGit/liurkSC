package com.liu.sc.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Description: 线程池：工作中不能使用jdk自带的Executors.new来实现，都需要手写线程池
 * @Author:W_LIURUNKAI
 * @Date:2019/9/7 15:36
 */
@Slf4j
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 一个线程池只有一个线程，工作中不能使用
     */
    private void singleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        try {
            for (int i = 0; i < 10; i++) {
                singleThreadExecutor.execute(() -> {
                    log.info("{}线程",Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            singleThreadExecutor.shutdown();
        }
    }

    /**
     * 固定线程池的使用，工作中不能使用
     */
    private void fixedThreadPool() {
        // 创建固定数量的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        try {
            for (int i = 0; i < 10; i++) {
                fixedThreadPool.execute(() -> {
                    log.info("{}线程",Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fixedThreadPool.shutdown();
        }
    }
}
