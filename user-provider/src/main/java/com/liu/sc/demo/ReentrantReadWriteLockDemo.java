package com.liu.sc.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 读写锁
 * @Author:W_LIURUNKAI
 * @Date:2019/8/31 20:33
 */
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int tempI = i;
            new Thread(() -> {
                myCache.put(String.valueOf(tempI),tempI);
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int tempI = i;
            new Thread(() -> {
                myCache.get(String.valueOf(tempI));
            },String.valueOf(i)).start();
        }
    }
}

class MyCache {
    // 使用map充当缓存
    public volatile Map<String, Object> map = new HashMap<>();

    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    // 写
    public void put(String key, Object value) {
        reentrantReadWriteLock.writeLock().lock();

        try {
            System.out.println("开始写缓存，key是" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println("写缓存完成，key是" + key + "value是" + value);
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    // 读
    public void get(String key) {
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println("开始读缓存，key是" + key);
            Object value = map.get(key);
            System.out.println("读缓存完成，key是" + key + "value是" + value);
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}
