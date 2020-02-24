package com.liu.datastructure.sort;

import java.util.Arrays;

/**
 * @Description: 基数排序(桶排序)：空间换时间
 * @author: liurunkai
 * @Date: 2020/2/18 16:39
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] array = {53, 3, 542, 748, 14, 214};
        // 获取最大数的位数
        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[maxIndex] < array[i]) {
                maxIndex = i;
            }
        }
        int maxLength = String.valueOf(array[maxIndex]).length();
        // 先创建一个二维数组，用来存放数据
        int[][] bucket = new int[10][array.length];
        // 创建一个一维数组，存放每个桶里有多少个数据
        int[] bucketCount = new int[10];
        for (int z = 0, n = 1; z < maxLength; z++, n *= 10) {
            // 将数据放入桶中
            for (int i = 0; i < array.length; i++) {
                int oneWei = array[i] / n % 10; //取出对应的位数
                bucket[oneWei][bucketCount[oneWei]] = array[i]; // 将数据放入对应的桶中
                bucketCount[oneWei]++; // 对应的桶中的数据量++
            }
            // 将每个桶中的数据取出放入原数组
            int index = 0;
            for (int j = 0; j < bucketCount.length; j++) {
                if (bucketCount[j] > 0) { //只有桶中有数据才往出取
                    for (int l = 0; l < bucketCount[j]; l++) {
                        array[index] = bucket[j][l];
                        index++;
                    }
                    // 第一轮处理后，需要将每个桶中的数据量置0
                    bucketCount[j] = 0;
                }
            }
            System.out.println("第" + (z + 1) + "轮" + Arrays.toString(array));
        }

//        // 第一轮排序
//        // 先创建一个二维数组，用来存放数据
//        int[][] bucket = new int[10][array.length];
//        // 创建一个一维数组，存放每个桶里有多少个数据
//        int[] bucketCount = new int[10];
//        // 将数据放入桶中
//        for (int i = 0; i < array.length; i++) {
//            int oneWei = array[i] % 10; //取出个位数
//            bucket[oneWei][bucketCount[oneWei]] = array[i]; // 将数据放入对应的桶中
//            bucketCount[oneWei]++; // 对应的桶中的数据量++
//        }
//        // 将每个桶中的数据取出放入原数组
//        int index = 0;
//        for (int j = 0; j < bucketCount.length; j++) {
//            if (bucketCount[j] > 0) { //只有桶中有数据才往出取
//                for (int l = 0; l < bucketCount[j]; l++) {
//                    array[index] = bucket[j][l];
//                    index++;
//                }
//                // 第一轮处理后，需要将每个桶中的数据量置0
//                bucketCount[j] = 0;
//            }
//        }
//        System.out.println(Arrays.toString(array));
//        //=================================
//        // 第二轮
//        // 将数据放入桶中
//        for (int i = 0; i < array.length; i++) {
//            int oneWei = array[i] / 10 % 10; //取出个位数
//            bucket[oneWei][bucketCount[oneWei]] = array[i]; // 将数据放入对应的桶中
//            bucketCount[oneWei]++; // 对应的桶中的数据量++
//        }
//        // 将每个桶中的数据取出放入原数组
//        index = 0;
//        for (int j = 0; j < bucketCount.length; j++) {
//            if (bucketCount[j] > 0) { //只有桶中有数据才往出取
//                for (int l = 0; l < bucketCount[j]; l++) {
//                    array[index] = bucket[j][l];
//                    index++;
//                }
//                bucketCount[j] = 0;
//            }
//        }
//        System.out.println(Arrays.toString(array));
//        //=================================
//        // 第三轮
//        // 将数据放入桶中
//        for (int i = 0; i < array.length; i++) {
//            int oneWei = array[i] / 100 % 10; //取出个位数
//            bucket[oneWei][bucketCount[oneWei]] = array[i]; // 将数据放入对应的桶中
//            bucketCount[oneWei]++; // 对应的桶中的数据量++
//        }
//        // 将每个桶中的数据取出放入原数组
//        index = 0;
//        for (int j = 0; j < bucketCount.length; j++) {
//            if (bucketCount[j] > 0) { //只有桶中有数据才往出取
//                for (int l = 0; l < bucketCount[j]; l++) {
//                    array[index] = bucket[j][l];
//                    index++;
//                }
//                bucketCount[j] = 0;
//            }
//        }
//        System.out.println(Arrays.toString(array));
    }
}
