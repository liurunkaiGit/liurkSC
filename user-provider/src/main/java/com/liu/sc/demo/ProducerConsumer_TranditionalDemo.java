package com.liu.sc.demo;

import jdk.nashorn.internal.ir.WhileNode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 多线程下生产者消费者模式---传统 ，lock
 * 题目：一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1
 * 线程  操作  资源类
 * 判断  干活  通知
 * 防止  虚假唤醒机制(多线程环境下判断只能用while，不能用if)
 * @Author:W_LIURUNKAI
 * @Date:2019/9/7 9:09
 */
public class ProducerConsumer_TranditionalDemo {
    public static void main(String[] args) {
        syncData syncData = new syncData();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                syncData.increment();
            },"AA").start();
        }

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                syncData.decrement();
            },"BB").start();
        }
    }
}

class syncData{
    private Integer number = 0;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment(){
        lock.lock();
        try {
            // 判断
            while (number != 0){
                condition.await();
            }
            // 干活
            number++;
            System.out.println(Thread.currentThread().getName() + number);
            // 通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();
        try {
            // 判断
            while (number == 0){
                condition.await();
            }
            // 干活
            number--;
            System.out.println(Thread.currentThread().getName() + number);
            // 通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
