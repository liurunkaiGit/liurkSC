package com.liu.sc.demo;

import org.omg.CORBA.TIMEOUT;

import java.util.PrimitiveIterator;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 死锁代码
 * @Author:W_LIURUNKAI
 * @Date:2019/9/7 19:56
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA,lockB),"AAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"BBB").start();
    }
}

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName()+"自己持有"+lockA+"尝试获得"+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"自己持有"+lockB+"尝试获得"+lockA);
            }
        }
    }
}
