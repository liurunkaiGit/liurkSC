package com.liu.sc.demo;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 可重入锁(也叫递归锁)
 * 指的是一个线程在获取一个方法的锁的时候，就获得了该方法内所有方法或者代码块的锁
 * @Author:W_LIURUNKAI
 * @Date:2019/8/31 14:40
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        sendAlarm sendAlarm = new sendAlarm();
        new Thread(() -> {
            sendAlarm.sendSms();
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("======================");

        Thread t3 = new Thread(sendAlarm,"t3");
        t3.start();
    }
}

class sendAlarm implements Runnable{
    public synchronized void sendSms() {
        System.out.println(Thread.currentThread().getName() + "====" + "sendSms");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "====" + "sendEmail");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    private void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "====" + "get()");
            set();
        } finally {
            lock.unlock();
        }
    }

    private void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "====" + "set()");
        } finally {
            lock.unlock();
        }
    }
}
