package com.liu.datastructure.sort;

import java.util.Arrays;

/**
 * @Description: 快速排序
 * @author: liurunkai
 * @Date: 2020/2/18 10:39
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = {8, 30, 2, 10, 8, 8, 22, 8, 5, -1, 10, 30, 2};
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] array,int left,int right) {
        int l = left;
        int r = right;
        int pivot = array[(left + right) / 2];
        while (l < r) {
            // 从左开始往右找，直到找到一个大于等于pivot的值就结束
            while (array[l] < pivot) {
                l++;
            }
            // 从右开始往左找，直到找到一个小于等于pivot的值就结束
            while (array[r] > pivot) {
                r--;
            }
            // l > r说明已经：左边的值都小于等于pivot，右边的值都大于等于pivot，就结束了
            if(l > r) {
                break;
            }
            // 将pivot左边大于等于pivot的值与pivot右边小于等于pivot的值进行交换
            int temp = array[l];
            array[l] = array[r];
            array[r] = temp;
            // 防止交换的两个值中有等于pivot的值，而造成死循环
            if (array[l] == pivot) {
                l++;
            }
            if (array[r] == pivot) {
                r--;
            }
        }
        // 如果left == right，必须让left++，right--，否则会出现栈溢出
        if(left == right) {
            left++;
            right--;
        }
        // 向左递归
        if(left < r) {
            quickSort(array,left,r);
        }
        // 向右递归
        if (right > l) {
            quickSort(array,l,right);
        }
    }
}
