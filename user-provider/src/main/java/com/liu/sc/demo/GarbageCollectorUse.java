package com.liu.sc.demo;

/**
 * 垃圾收集器的使用
 */
public class GarbageCollectorUse {

    /**
     * 1. G1的使用
     *   -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
     */
    public static void main(String[] args) {
        while (true){
            System.out.println("测试垃圾回收器");
            byte[] bytes = new byte[60 * 1024 * 1024];
        }
    }

}
