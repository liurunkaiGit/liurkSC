package com.liu.datastructure.sort;

import java.util.Arrays;

/**
 * @Description: 归并排序
 * @author: liurunkai
 * @Date: 2020/2/18 14:29
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length - 1, temp);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 分+合
     *
     * @param array
     * @param left
     * @param right
     * @param temp
     */
    private static void mergeSort(int[] array, int left, int right, int[] temp) {
        // 如果left<right说明还没有完全分开，当left==right，说明只剩一个数即分完成
        if (left < right) {
            int mid = (left + right) / 2; //中间索引
            // 向左递归分解
            mergeSort(array, left, mid, temp);
            // 向右递归分解
            mergeSort(array, mid + 1, right, temp);
            // 合并
            merge(array, left, mid, right, temp);
        }
    }

    /**
     * 合并
     *
     * @param array 原始数组
     * @param left  左边索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  临时数组
     */
    private static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left; // 初始化i
        int j = mid + 1; // 初始化j
        int t = 0; //临时数组索引，初始值为0，空数组
        // 1. 先把左右两边(有序)的数据按照规则填充到temp数组
        while (i <= mid && j <= right) { // 说明还没有合并完
            if (array[i] <= array[j]) {
                // 左边的值小于等于右边的值，将左边的值填充到temp中，并将左边和temp后移t++，i++
                temp[t] = array[i];
                t++;
                i++;
            } else {
                // 左边的值大于右边的值，将右边的值填充到temp中，并将右边和temp后移t++，i++
                temp[t] = array[j];
                t++;
                j++;
            }
        }
        // 2. 将左边或者右边剩余的值全部填充到临时数组temp中
        while (i <= mid) {
            temp[t] = array[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = array[j];
            t++;
            j++;
        }
        // 3. 将临时数组的数据填充到原来的数组中，注意：并不是每次都拷贝所有
        int tempIndex = 0;
        int tempLeft = left;
        System.out.println("tempLeft=" + tempLeft + " right=" + right);
        while (tempLeft <= right) {
            array[tempLeft] = temp[tempIndex];
            tempIndex++;
            tempLeft++;
        }
    }
}
