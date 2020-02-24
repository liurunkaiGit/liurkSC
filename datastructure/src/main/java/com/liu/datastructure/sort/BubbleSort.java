package com.liu.datastructure.sort;

import java.util.Arrays;
import java.util.concurrent.TransferQueue;

/**
 * @Description: 冒泡排序，总共需要进行array.length-1趟排序，时间复杂度是n^2，因为两层for循环
 * @author: liurunkai
 * @Date: 2020/2/17 10:40
 */
public class BubbleSort {
    public static void main(String[] args) {
        // 从小到大排序
        int[] array = {3, 9, -1, 10, 20};
        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) { //控制排序的趟数，总共需要进行array.length-1趟排序
            boolean flag = false;
            for (int j = 0; j < array.length - 1 - i; j++) { // 排序，交换位置
                if (array[j] > array[j + 1]) {
                    flag = true;
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
            if(!flag) {
                break;
            }
            System.out.println("第" + (i + 1) + "排序后");
            System.out.println(Arrays.toString(array));
        }

//        // 第一趟排序，将最大的数排到最后一位
//        int temp = 0;
//        for (int i = 0; i < array.length - 1 - 0; i++) {
//            if(array[i] > array[i+1]) {
//                temp = array[i+1];
//                array[i+1] = array[i];
//                array[i] = temp;
//            }
//        }
//        System.out.println("第一趟排序后");
//        System.out.println(Arrays.toString(array));
//        // 第2趟排序，将最大的数排到倒数第二位
//        for (int i = 0; i < array.length - 1 - 1; i++) {
//            if(array[i] > array[i+1]) {
//                temp = array[i+1];
//                array[i+1] = array[i];
//                array[i] = temp;
//            }
//        }
//        System.out.println("第2趟排序后");
//        System.out.println(Arrays.toString(array));
//        // 第3趟排序，将最大的数排到倒数第3位
//        for (int i = 0; i < array.length - 1 - 2; i++) {
//            if(array[i] > array[i+1]) {
//                temp = array[i+1];
//                array[i+1] = array[i];
//                array[i] = temp;
//            }
//        }
//        System.out.println("第3趟排序后");
//        System.out.println(Arrays.toString(array));
//        // 第4趟排序，将最大的数排到倒数第4位
//        for (int i = 0; i < array.length - 1 - 3; i++) {
//            if(array[i] > array[i+1]) {
//                temp = array[i+1];
//                array[i+1] = array[i];
//                array[i] = temp;
//            }
//        }
//        System.out.println("第4趟排序后");
//        System.out.println(Arrays.toString(array));
    }
}
