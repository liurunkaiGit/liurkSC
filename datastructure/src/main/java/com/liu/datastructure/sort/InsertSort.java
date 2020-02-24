package com.liu.datastructure.sort;

import java.util.Arrays;

/**
 * @Description: 插入排序
 * @author: liurunkai
 * @Date: 2020/2/17 12:07
 */
public class InsertSort {
    public static void main(String[] args) {
        // 从小到大排序
        int[] array = {9, 3, -1, 10, -2};
        for (int i = 1; i < array.length; i++) {
            int insertValue = array[i]; //定义待插入的数
            int insertIndex = i - 1; // 定义待插入数的前面一个数的下标，因为要从待插入数的前面一个数开始逐渐往前比较
            // insertIndex >= 0 保证数组不越界，因为会insertIndex--，当insertIndex < 0时(-1)，说明待插入的数应该排在数组第一位
            // insertValue < array[insertIndex] 如果待插入的数小于前面的数，说明没有找到适当的位置，继续与前面的数比较insertIndex--
            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex]; //将待插入的数的前面的数后移
                insertIndex--; // 说明没有找到适当位置，继续往前找
            }
            // 当while循环结束时，说明找到了适当位置，因为是与待插入数的位置的前一个数进行比较，所以待插入数的位置应该是insertIndex+1
            array[insertIndex + 1] = insertValue; // 将待插入的数插入到适当的位置
            System.out.println("第" + i + "次排序后");
            System.out.println(Arrays.toString(array));
        }

//        // 第一轮{9, 3, -1, 10, 20} 》 {3, 9, -1, 10, 20}
//        int insertValue = array[1]; // 定义待插入的数
//        int insertIndex = 1 - 1; // 即待插入数的前面一个数的下标，因为要从待插入数的前面一个数逐渐往前比较
//        // insertIndex >= 0 保证数组下标不越界
//        // insertValue < array[insertIndex] 判断待插入的数是否小于前面的数，如果小于，说明没有找到适当位置，继续往前找
//        while (insertIndex >= 0 && insertValue < array[insertIndex]) {
//            array[insertIndex + 1] = array[insertIndex]; // 将待插入的数的前面的数后移
//            insertIndex--; // 说明没有找到适当位置，继续往前找
//        }
//        // 当结束while循环，说明找到适当位置，insertIndex + 1
//        array[insertIndex + 1] = insertValue;
//        System.out.println("第一轮排序后");
//        System.out.println(Arrays.toString(array));
//        // 第2轮{3, 9, -1, 10, 20} 》 {-1，3, 9, 10, 20}
//        int insertValue2 = array[2]; // 定义待插入的数
//        int insertIndex2 = 2 - 1; // 即待插入数的前面一个数的下标，因为要从待插入数的前面一个数逐渐往前比较
//        // insertIndex >= 0 保证数组下标不越界
//        // insertValue < array[insertIndex] 判断待插入的数是否小于前面的数，如果小于，说明没有找到适当位置，继续往前找
//        while (insertIndex2 >= 0 && insertValue2 < array[insertIndex2]) {
//            array[insertIndex2 + 1] = array[insertIndex2]; // 将待插入的数的前面的数后移
//            insertIndex2--; // 说明没有找到适当位置，继续往前找
//        }
//        // 当结束while循环，说明找到适当位置，insertIndex + 1
//        array[insertIndex2 + 1] = insertValue2;
//        System.out.println("第2轮排序后");
//        System.out.println(Arrays.toString(array));
    }
}
