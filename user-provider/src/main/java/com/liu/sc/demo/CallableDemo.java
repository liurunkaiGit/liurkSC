package com.liu.sc.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Description: 多线程的实现方式
 * 1. 继承Thread类
 * 2. 实现Runnable接口
 * 3. 实现Callable接口
 * @Author:W_LIURUNKAI
 * @Date:2019/9/7 13:21
 */
@Slf4j
public class CallableDemo {
    public static void main(String[] args) throws Exception {
        Thread aaa = new Thread("aaa");
        aaa.start();
        new Thread(new MyThread(), "bbb").start();
        // 因为new Thread方法没有接受callable参数的构造方法，只有接收runnable参数的构造方法，
        // 而FutureTask类正好实现Runnable接口且new FutureTask（）正好需要callable参数
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        new Thread(futureTask, "ccc").start();
        new Thread(futureTask, "ccc2").start();// ccc和ccc2启动两个线程只会打印一次start callable
        // 如果需要打印两次start callable，则需要
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread2());
        new Thread(futureTask2, "ccc2").start();
        //futureTask.get() 如果线程没有执行完，会阻塞线程，所以建议将其放到最后
        while (!futureTask.isDone()) {
            // 判断是否执行完
        }
        Integer integer = futureTask.get();
        log.info("{}", futureTask.get());
    }
}

@Slf4j
class MyThread implements Runnable {

    @Override
    public void run() {
        log.info("start runnable");
    }
}

@Slf4j
class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        log.info("start callable");
        return 1024;
    }
}