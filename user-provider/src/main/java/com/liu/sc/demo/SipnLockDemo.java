package com.liu.sc.demo;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: 自旋锁，一直尝试获取锁，好处减少线程上下文切换，缺点循环会消耗cpu
 * @Author:W_LIURUNKAI
 * @Date:2019/8/31 19:29
 */
public class SipnLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println("线程" + thread.getName() + "=====" + LocalDateTime.now() + "开始获取锁");
        while (!atomicReference.compareAndSet(null, thread)) {
            System.out.println("线程" + thread.getName() + "尝试获取锁");
        }
        System.out.println("线程" + thread.getName() + "=====" + LocalDateTime.now() + "获取锁完成");
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println("线程" + thread.getName() + "释放锁");
    }

    public static void main(String[] args) {
        SipnLockDemo sipnLockDemo = new SipnLockDemo();

        new Thread(() -> {
            sipnLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sipnLockDemo.myUnLock();
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            sipnLockDemo.myLock();
            sipnLockDemo.myUnLock();
        }, "t2").start();
    }
}
