package com.liu.datastructure.search;

/**
 * @Description: 线性查找
 * @author: liurunkai
 * @Date: 2020/2/19 10:09
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] array = {1, 3, 2, 6, 5};
        int searchValue = 3;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == searchValue) {
                System.out.println(i);
            }
        }
    }
}
