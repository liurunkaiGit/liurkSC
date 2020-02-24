package com.liu.datastructure.search;

/**
 * @Description: 插值查找算法
 * @author: liurunkai
 * @Date: 2020/2/19 11:37
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int searchValue = 1;
        int i1 = insertValueSearch(array, 0, array.length - 1, searchValue);
        System.out.println(i1);
    }

    /**
     * 插值查找算法
     *
     * @param array
     * @param left
     * @param right
     * @param searchValue
     */
    private static int insertValueSearch(int[] array, int left, int right, int searchValue) {
        System.out.println("***");
        int mid = left + (right - left) * (searchValue - array[left]) / (array[right] - array[left]);
        // 注意：searchValue < array[0] || searchValue > array[array.length - 1] 这个判断必须有，否则可能造成下标越界
        if (left > right || searchValue < array[0] || searchValue > array[array.length - 1]) {
            return -1;
        }
        if (searchValue > array[mid]) {
            return insertValueSearch(array, mid + 1, right, searchValue);
        } else if (searchValue < array[mid]) {
            return insertValueSearch(array, 0, mid - 1, searchValue);
        } else {
            return mid;
        }
    }
}
