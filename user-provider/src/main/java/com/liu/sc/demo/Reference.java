package com.liu.sc.demo;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @Description: 4中引用方式
 * @Author:W_LIURUNKAI
 * @Date:2019/10/11 19:46
 */
public class Reference {

    public static void main(String[] args) {
        //strongReference();
        //softRefenenceHaveMemory();
        //softReferenceNoMemory();
        //weakReference();
        phantomReference();
    }

    /**
     * 虚引用，需要结合引用队列来使用，将其放到引用队列里面执行后续操作
     */
    private static void phantomReference() {
        Object obj1 = new Object();
        ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> objectPhantomReference = new PhantomReference<>(obj1, objectReferenceQueue);
        System.out.println(obj1);
        System.out.println(objectPhantomReference.get());
        System.out.println(objectReferenceQueue.poll());
        obj1 = null;
        System.gc();
        System.out.println(obj1);
        System.out.println(objectPhantomReference.get());
        System.out.println(objectReferenceQueue.poll());
    }

    /**
     * 弱引用，只要发生垃圾回收（GC）该对象就会被回收
     */
    private static void weakReference() {
        Object obj1 = new Object();
        WeakReference<Object> objectWeakReference = new WeakReference<>(obj1);
        System.out.println(obj1);
        System.out.println(objectWeakReference.get());
        obj1 = null;
        System.gc();
        System.out.println(obj1);
        System.out.println(objectWeakReference.get());
    }

    /**
     * 当内存不足时，自动回收软引用对象
     */
    private static void softReferenceNoMemory() {
        Object obj1 = new Object();
        SoftReference<Object> objectSoftReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(objectSoftReference.get());

        obj1 = null;

        try {
            // 创建一个30M的大对象
            // 启动参数里面设置最大内存为 -Xms5m -Xmx5m -XX:+PrintGCDetails
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(obj1);
            System.out.println(objectSoftReference.get());
        }
    }

    /**
     * 内存充足的软引用，即使手动GC，也不会进行垃圾回收
     */
    private static void softRefenenceHaveMemory() {
        Object obj1 = new Object();
        SoftReference<Object> objectSoftReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(objectSoftReference.get());

        obj1 = null;
        System.gc();

        System.out.println(obj1);
        System.out.println(objectSoftReference.get());
    }

    /**
     * `
     * 强引用
     */
    private static void strongReference() {
        Object obj1 = new Object(); //默认强引用
        //把一个对象赋给一个引用变量，这个引用变量就是强引用,即obj2为强引用
        Object obj2 = obj1;
        obj1 = null;
        System.gc();
        System.out.println(obj2);
    }
}
