package com.liu.sc.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description: CAS中ABA问题解决
 * @Author:W_LIURUNKAI
 * @Date:2019/8/25 16:37
 */
@Slf4j
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);// A

    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(10, 1);

    public static void main(String[] args) {
        // ABA问题展示Demo
//        new Thread(() -> {
//            boolean b1 = atomicReference.compareAndSet(100, 1000);// B
//            log.info("b1 is {},value is {}", b1, atomicReference.get());
//            boolean b = atomicReference.compareAndSet(1000, 100);// A
//            log.info("b is {},value is {}", b, atomicReference.get());
//        }, "thread1").start();
//
//        new Thread(() -> {
//            try {
//                // 让thread2休眠3秒钟，保证thread1里面的ABA问题执行完
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            boolean b = atomicReference.compareAndSet(100, 101);
//            log.info("b is {},value is {}", b, atomicReference.get());
//            // 返回true，可以修改为101，预期值是100，但是预期值是被thread1修改两次后变为的100
//        }, "thread2").start();

        // ABA问题解决demo
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            log.info("线程{}的第1次版本号{}", Thread.currentThread().getName(), stamp);
            try {
                // 休眠一秒钟让thread4也拿到最初始的版本号
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicStampedReference.compareAndSet(10, 12, stamp, stamp + 1);
            log.info("线程{}的第2次版本号{}", Thread.currentThread().getName(), atomicStampedReference.getStamp());
            log.info("b is {},value is {},stamp is {}",b,atomicStampedReference.getReference(),atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(12, 10, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            log.info("线程{}的第3次版本号{}", Thread.currentThread().getName(), atomicStampedReference.getStamp());
            log.info("b is {},value is {},stamp is {}",b,atomicStampedReference.getReference(),atomicStampedReference.getStamp());
        }, "thread3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            log.info("线程{}的第1次版本号{}", Thread.currentThread().getName(), stamp);
            try {
                // 让thread4休眠3秒钟，保证thread3里面的ABA问题执行完
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicStampedReference.compareAndSet(10, 11, stamp, stamp + 1);
            log.info("线程{}的第2次版本号{}", Thread.currentThread().getName(), atomicStampedReference.getStamp());
            log.info("b is {},value is {},stamp is {}",b,atomicStampedReference.getReference(),atomicStampedReference.getStamp());
        },"thread4").start();
    }
}
