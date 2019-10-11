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
        Object obj1 = new Object();
        Object obj2 = obj1;
        obj1 = null;
        System.gc();
        System.out.println(obj2);
    }
}
