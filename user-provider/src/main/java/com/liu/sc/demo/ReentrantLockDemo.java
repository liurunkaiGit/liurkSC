package com.liu.sc.demo;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
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
        // 锁绑定多个条件Condition，reentrantLock实现精确唤醒
        // 题目：线程A打印5次，线程B打印10次，线程C打印15次，紧接着继续执行线程A
        shareResource shareResource = new shareResource();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                shareResource.print5();
            },"A").start();
            new Thread(() -> {
                shareResource.print10();
            },"B").start();
            new Thread(() -> {
                shareResource.print15();
            },"C").start();
        }
        /*sendAlarm sendAlarm = new sendAlarm();
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
        t3.start();*/
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

class shareResource{
    private Integer number = 1; // A:1,B:2,C:3
    private Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            // 判断
            while (number != 1){
                condition1.await();
            }
            // 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
            // 通知
            number=2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print10(){
        lock.lock();
        try {
            // 判断
            while (number != 2){
                condition2.await();
            }
            // 干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
            // 通知
            number=3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try {
            // 判断
            while (number != 3){
                condition3.await();
            }
            // 干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
            // 通知
            number=1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
