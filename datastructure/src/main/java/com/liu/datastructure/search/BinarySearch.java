package com.liu.datastructure.search;

import sun.plugin.javascript.navig.LinkArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 二分查找：递归
 * @author: liurunkai
 * @Date: 2020/2/19 10:25
 */
public class BinarySearch {
    public static void main(String[] args) {
//        int[] array = {12, 1, 3, 6, 12, 12, 12, 22, 34, 101, 12};
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int searchValue = 1;
        int i = binarySearch(array, 0, array.length - 1, searchValue);
        System.out.println(i);
//        List<Integer> integers = binarySearch2(array, 0, array.length - 1, searchValue);
//        System.out.println(Arrays.toString(integers.toArray()));
    }

    /**
     * 二分递归查找：找到一个就返回
     *
     * @param array       数组
     * @param left        左边索引
     * @param right       右边索引
     * @param searchValue 要查找的值
     */
    private static int binarySearch(int[] array, int left, int right, int searchValue) {
        System.out.println("*");
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (searchValue > array[mid]) { // 向右递归查找
            return binarySearch(array, mid + 1, right, searchValue);
        } else if (searchValue < array[mid]) {
            return binarySearch(array, 0, mid - 1, searchValue);
        } else {
            return mid;
        }
    }

    /**
     * 二分递归查找：返回所有查找到的值
     *
     * @param array       数组
     * @param left        左边索引
     * @param right       右边索引
     * @param searchValue 要查找的值
     */
    private static List<Integer> binarySearch2(int[] array, int left, int right, int searchValue) {
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        if (searchValue > array[mid]) { // 向右递归查找
            return binarySearch2(array, mid + 1, right, searchValue);
        } else if (searchValue < array[mid]) {
            return binarySearch2(array, 0, mid - 1, searchValue);
        } else {
            List<Integer> list = new ArrayList<>();
            // 查找mid左边
            int temp = mid - 1;
            while (true) {
                if (temp < 0) {
                    break;
                }
                if (array[temp] == searchValue) {
                    list.add(temp);
                }
                temp--;
            }
            list.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp > array.length - 1) {
                    break;
                }
                if (array[temp] == searchValue) {
                    list.add(temp);
                }
                temp++;
            }
            return list;
        }
    }
}
