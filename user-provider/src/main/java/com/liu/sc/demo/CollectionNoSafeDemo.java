package com.liu.sc.demo;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description: 集合类不安全之并发修改异常
 * @Author:W_LIURUNKAI
 * @Date:2019/8/31 11:16
 */
public class CollectionNoSafeDemo {
    // arrayList线程不安全，单线程没问题，多线程报并发修改异常:java util concurrentModificationException
    public static void main(String[] args) {
        //线程不安全
        //List<String> strList = new ArrayList<>();
        // 线程安全1. List<String> strList = new Vector(); 2. List strList = Collections.synchronizedArrayList(new ArrayList());
        CopyOnWriteArrayList<String> strList = new CopyOnWriteArrayList<>();
//        HashSet<Object> set1 = new HashSet<>();
//        //Set<Object> set = Collections.synchronizedSet(new HashSet<>());
//        CopyOnWriteArraySet<Object> set = new CopyOnWriteArraySet<>();
//        HashSet<Object> objects = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                strList.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(strList);
            },"线程"+i).start();
        }
    }
}
