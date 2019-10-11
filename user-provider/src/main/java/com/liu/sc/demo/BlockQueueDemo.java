package com.liu.sc.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 阻塞队列
 * @Author:W_LIURUNKAI
 * @Date:2019/8/31 22:56
 */
public class BlockQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "a被put成功");
                synchronousQueue.put("a");
                System.out.println(Thread.currentThread().getName() + "b被put成功");
                synchronousQueue.put("b");
                System.out.println(Thread.currentThread().getName() + "c被put成功");
                synchronousQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程1").start();
        /*try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
                synchronousQueue.take();
                System.out.println(Thread.currentThread().getName() + "a被获取");
                TimeUnit.MILLISECONDS.sleep(5000);
                synchronousQueue.take();
                System.out.println(Thread.currentThread().getName() + "b被获取");
                TimeUnit.MILLISECONDS.sleep(5000);
                synchronousQueue.take();
                System.out.println(Thread.currentThread().getName() + "c被获取");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程2").start();
    }

    // ArrayBlockingQueue和LinkedBlockingQueue的使用方法
    void arrayBlockingQueue() {
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(2);
        // put方法阻塞
        try {
            arrayBlockingQueue.put("a");
            arrayBlockingQueue.put("a");
//            System.out.println("==================");
//            arrayBlockingQueue.put("a");
//            arrayBlockingQueue.put("a");
            arrayBlockingQueue.take();
            arrayBlockingQueue.take();
            System.out.println("-------------");
            arrayBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 添加元素
        /*System.out.println(arrayBlockingQueue.add("a")); //true
        System.out.println(arrayBlockingQueue.add("b")); //true
        System.out.println(arrayBlockingQueue.add("c")); //true
        System.out.println(arrayBlockingQueue.add("d")); //抛出异常*/
//        System.out.println(arrayBlockingQueue.offer("a")); //true
//        System.out.println(arrayBlockingQueue.offer("b")); //true
//        System.out.println(arrayBlockingQueue.offer("c")); //true
//        System.out.println(arrayBlockingQueue.offer("d")); //false
        try {
            arrayBlockingQueue.offer("e", 2L, TimeUnit.SECONDS); //阻塞两秒钟，两秒钟后插不进去返回false
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 从队列头部查询元素
//        System.out.println(arrayBlockingQueue.peek());
//        System.out.println(arrayBlockingQueue.element());

        //获取元素
        /*System.out.println(arrayBlockingQueue.remove()); // a 返回第一个元素
        System.out.println(arrayBlockingQueue.poll());   // b 返回第一个元素，因为a元素已经被remove，所以当前阻塞队列的第一个元素是b
        System.out.println(arrayBlockingQueue.remove()); // c 返回第一个元素，因为a/b元素已经被remove，所以当前阻塞队列的第一个元素是c
        System.out.println(arrayBlockingQueue.poll());   // 返回null
        System.out.println(arrayBlockingQueue.remove()); // 当队列为空时，remove方法抛出NoSuchElementException异常*/
        try {
            arrayBlockingQueue.poll(2L, TimeUnit.SECONDS); //阻塞两秒钟，两秒钟后获取不到返回null
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
