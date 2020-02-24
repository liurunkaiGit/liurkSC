package com.liu.datastructure.sort;

import java.util.Arrays;

/**
 * @Description: 希尔排序
 * @author: liurunkai
 * @Date: 2020/2/17 15:50
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        // 移动法
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int j = i;
                int temp = array[j];
                if (array[j] < array[j - gap]) {
                    while (j - gap >= 0 && temp < array[j - gap]) {
                        array[j] = array[j - gap];
                        j -= gap;
                    }
                    array[j] = temp;
                }
            }
            System.out.println(Arrays.toString(array));
        }
        // 交换法
//        for (int gap = array.length / 2; gap > 0; gap /= 2) {
//            for (int i = gap; i < array.length; i++) {
//                for (int j = i - gap; j >= 0; j -= gap) {
//                    if (array[j+gap] < array[j]) {
//                        int temp = array[j+gap];
//                        array[j+gap] = array[j];
//                        array[j] = temp;
//                    }
//                }
//            }
//        }
//        System.out.println(Arrays.toString(array));
//        // 第一轮：array.length/2=5，所以步长为5，被分成5组
//        for (int i = 5; i < array.length; i++) {
//            for (int j = i - 5; j >= 0; j -= 5) { //遍历各组中的所有元素(共5组，每组有两个元素)，步长5
//                if (array[j+5] < array[j]) {
//                    int temp = array[j+5];
//                    array[j+5] = array[j];
//                    array[j] = temp;
//                }
//            }
//        }
//        System.out.println("第一轮");
//        System.out.println(Arrays.toString(array));
//        // 第二轮：步长为5/2=2，被分成2组
//        for (int i = 2; i < array.length; i++) {
//            for (int j = i - 2; j >= 0; j -= 2) {//遍历各组中的所有元素(共2组，每组有5个元素)，步长2
//                if (array[j+2] < array[j]) {
//                    int temp = array[j+2];
//                    array[j+2] = array[j];
//                    array[j] = temp;
//                }
//            }
//        }
//        System.out.println("第2轮");
//        System.out.println(Arrays.toString(array));
//        // 第3轮：步长为2/2=1，被分成1组
//        for (int i = 1; i < array.length; i++) {
//            for (int j = i - 1; j >= 0; j -= 1) {//遍历各组中的所有元素(共1组，每组有10个元素)，步长1
//                if (array[j+1] < array[j]) {
//                    int temp = array[j+1];
//                    array[j+1] = array[j];
//                    array[j] = temp;
//                }
//            }
//        }
//        System.out.println("第3轮");
//        System.out.println(Arrays.toString(array));
    }
}
