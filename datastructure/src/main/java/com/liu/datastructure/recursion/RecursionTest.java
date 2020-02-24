package com.liu.datastructure.recursion;

/**
 * @Description: 递归
 * @author: liurunkai
 * @Date: 2020/2/15 10:04
 */
public class RecursionTest {
    public static void main(String[] args) {
//        test(4);
        System.out.println(factorial(4));
    }

    public static Integer factorial(Integer n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }

    public static void test(Integer n) {
        if (n > 2) {
            test(n - 1);
        } else {
            System.out.println(n);
        }
    }
}
