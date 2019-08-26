package com.liu.sc.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: volatile
 * 1 volatile的可见性代码验证
 * 1.1 加入int number = 0;number变量没有添加volatile关键字，没有可见性
 * 1.2 添加volatile关键字后，可以保证可见性
 * 2 volatile不保证原子性的代码验证
 * 2.1 什么是原子性，跟事务的原子性有所区别
 * 不可分割，完整性，也就是某个线程正在执行某个业务时，中间不可以被加塞或者分割，需要整体完整
 * 要么同时成功，要么同时失败
 * 2.2 不保证原子性的代码演示
 * 2.3 为什么不保证原子性
 * 当多线程的时候，一个线程2刚把自己工作内存中的值刷新到主内存时，还没来得及通知其他线程，
 * 其他线程1也立马把自己的值刷新到主内存，这时会造成线程1的值将线程2的值覆盖，造成线程2的值丢失
 * 2.4 如何保证原子性
 * 2.4.1 加synchronized关键字保证原子性
 * 2.4.2 使用原子性类AtomicInteger保证原子性
 * 3 volatile的使用场景
 * 3.1 单例模式下的使用
 * @Author:W_LIURUNKAI
 * @Date:2019/8/24 17:31
 */
@Slf4j
public class VolatileDemo {

    // 单例模式下使用volatile
    //private static VolatileDemo volatileDemo = null;

    // 多线程下使用volatile和DCL模式，由于指令重排的存在，所以多线程下使用volatile保证线程安全
    private static volatile VolatileDemo volatileDemo = null;

    private VolatileDemo() {
        log.info("我是单例模式的构造函数");
    }

    // 单线程下使用
    /*public static VolatileDemo getVolatileDemo(){
        if(volatileDemo == null){
            volatileDemo = new VolatileDemo();
        }
        return volatileDemo;
    }*/

    // 多线程使用DCL模式（double check lock，双重判定并加锁）,但是DCL机制不一定保证线程安全，由于指令重排存在，不能保证100%单例，加入volatile关键字可以保证
    public static VolatileDemo getVolatileDemo() {
        if (volatileDemo == null) {
            synchronized (VolatileDemo.class) {
                if (volatileDemo == null) {
                    volatileDemo = new VolatileDemo();
                }
            }
        }
        return volatileDemo;
    }

    public static void main(String[] args) {
        // 验证单例模式
        // 如果是单线程log.info("我是单例模式的构造函数");这个日志只会被打印一次,并且下面都是true
//        log.info("{}",VolatileDemo.getVolatileDemo() == VolatileDemo.getVolatileDemo());
//        log.info("{}",VolatileDemo.getVolatileDemo() == VolatileDemo.getVolatileDemo());
//        log.info("{}",VolatileDemo.getVolatileDemo() == VolatileDemo.getVolatileDemo());

        System.out.println("========================");

        // 如果是多线程log.info("我是单例模式的构造函数");这个日志只会被打印多次
        // 解决方式：可以加synchronized关键字
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                VolatileDemo.getVolatileDemo();
            }, "线程".concat(String.valueOf(i))).start();
        }
    }

    // volatile 保证可见性的代码验证
    public void canSeeByVolatile() {
        addNumber addNumber = new addNumber();
        new Thread(() -> {
            log.info("start update,当前线程是{},number是{}", Thread.currentThread().getName(), addNumber.number);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            addNumber.addNumber();
            log.info("end update;当前线程是{},number是{}", Thread.currentThread().getName(), addNumber.number);
        }, "aaa").start();

        // 以下是main线程的执行
        while (addNumber.number == 0) {

        }
        log.info("number!=0,当前线程是{},number是{}", Thread.currentThread().getName(), addNumber.number);
    }

    // volatile 不保证原子性的代码验证
    public void noAtomicVolatile() {
        addNumber addNumber = new addNumber();
        // 启动20个线程来执行number++，每个线程执行1000次，看最后的值是否等于20000，如果不是，说明不保证原子性
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    addNumber.addPlusPlus();
                    addNumber.addPlusPlusAtomic();
                }
            }, "线程".concat(String.valueOf(i))).start();
        }

        // 需要等待上面20个线程全部计算完成后，再用main线程去执行
        // 默认存在两个线程，一个是main线程（用户开启的线程），一个是gc线程，如果正在执行的线程数大于2，说明上面的20个线程还没有全部执行完
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        // 测试结果发现，没有保证原子性，number最终的值小于20000
        log.info("20个线程全部执行完，number是{}", addNumber.number);
        // 测试结果发现，没有保证原子性，number最终的值等于20000
        log.info("20个线程全部执行完，atomicNnumber是{}", addNumber.atomicInteger);
    }
}

class addNumber {
    volatile int number = 0;

    // 测试可见性用的方法
    void addNumber() {
        this.number = 60;
    }

    // 测试不保证原子性用的方法，即便number加了volatile关键字，因为volatile不保证原子性
    // 加synchronized关键字，可以保证原子性public synchronized void addPlusPlus
    public void addPlusPlus() {
        /*number++底层会执行3步
            1. 获取值（从主内存获取）
            2. 执行+1操作
            3. 将值写回去（主内存）*/
        number++;
    }

    // 通过使用AtomicInteger原子类
    AtomicInteger atomicInteger = new AtomicInteger();//默认是0

    void addPlusPlusAtomic() {
        atomicInteger.getAndIncrement();
    }
}
