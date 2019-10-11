package com.liu.sc.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 生产者消费者模式---blockQueue实现
 * volatile/atomicInteger/CAS/blockQueue/线程交互原子类
 * @Author:W_LIURUNKAI
 * @Date:2019/9/7 10:59
 */
@Slf4j
public class ProducerConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        myResource myResource = new myResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            log.info("{},生产线程启动",Thread.currentThread().getName());
            myResource.myProducer();
        },"producer").start();
        new Thread(() -> {
            log.info("{},消费线程启动",Thread.currentThread().getName());
            myResource.myConsumer();
        },"consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5L);
            log.info("5秒钟时间到，生产者消费者线程结束{}",Thread.currentThread().getName());
            myResource.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

@Slf4j
class myResource {
    private volatile Boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    // 构造方法注入
    public myResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        log.info("blockQueue is {}",blockingQueue.getClass().getName());
    }

    public void myProducer() {
        String data;
        boolean offer;
        try {
            while (flag) {
                data = String.valueOf(atomicInteger.incrementAndGet());
                offer = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if(offer){
                    log.info("生产者{}，成功",Thread.currentThread().getName() + data);
                }else {
                    flag = false;
                    log.info("生产者{}，失败，消费推出",Thread.currentThread().getName() + data);
                    return;
                }
                TimeUnit.SECONDS.sleep(1L);
            }
            log.info("{}，flag == false,生产者线程停止",Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void myConsumer() {
        String result = null;
        try {
            while (flag){
                result = blockingQueue.poll(2L,TimeUnit.SECONDS);
                if(StringUtils.isBlank(result)){
                    log.info("超过2秒没有取到值");
                }
                log.info("消费者{},消费成功",Thread.currentThread().getName()+result);
                TimeUnit.SECONDS.sleep(1L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        flag = false;
    }
}
