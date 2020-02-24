package com.liu.datastructure.sort;

import java.util.Arrays;

/**
 * @Description: 选择排序，总共交换array.length-1次，时间复杂度是n^2，因为两层for循环
 * @author: liurunkai
 * @Date: 2020/2/17 11:28
 */
public class SelectSort {
    public static void main(String[] args) {
        // 从小到大排序
        int[] array = {3, 9, -1, 10, 20};
        for (int i = 0; i < array.length - 1; i++) { // 控制交换的次数，总共交换array.length-1次
            int min = array[i]; // 假设当前最小值就是这个值
            int index = i;
            for (int j = i + 1; j < array.length; j++) { // 让当前这个值与后面的值进行比较，所以要j=i+1
                if (array[j] < min) {
                    // 记录最小值及最小值的喜爱奥
                    min = array[j];
                    index = j;
                }
            }
            if(index != i) {
                // 进行交换
                array[index] = array[i];
                array[i] = min;
                System.out.println("第" + (i + 1) + "排序后");
                System.out.println(Arrays.toString(array));
            }
        }
    }
}
