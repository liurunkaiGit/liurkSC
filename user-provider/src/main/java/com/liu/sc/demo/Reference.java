package com.liu.sc.demo;

/**
 * @Description: 4中引用方式
 * @Author:W_LIURUNKAI
 * @Date:2019/10/11 19:46
 */
public class Reference {

    public static void main(String[] args) {
        //strongReference();
    }

    /**
     * 强引用
     */
    private static void strongReference() {
        Object obj1 = new Object(); //强引用
        //把一个对象赋给一个引用变量，这个引用变量就是强引用,即obj2为强引用
        Object obj2 = obj1;
        obj1 = null;
        System.gc();
        System.out.println(obj2);
    }
}
