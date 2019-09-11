package com.liu.sc.demo;

/**
 * @Description: JPS命令和jinfo命令的使用
 * @Author:W_LIURUNKAI
 * @Date:2019/9/8 10:01
 */
public class HelloGC {
    public static void main(String[] args) throws Exception{
        long totalMemory = Runtime.getRuntime().totalMemory();// 返回java虚拟机中的内存总量1/64
        long maxMemory = Runtime.getRuntime().maxMemory();// 返回java虚拟机使用的最大内存量1/4
        System.out.println(totalMemory/1024/1024 + "m");
        System.out.println(maxMemory/1024/1024 + "m");
    }
}
