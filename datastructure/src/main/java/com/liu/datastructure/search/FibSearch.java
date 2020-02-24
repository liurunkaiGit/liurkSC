package com.liu.datastructure.search;

import java.util.Arrays;

/**
 * @Description: 斐波那契查找算法
 * @author: liurunkai
 * @Date: 2020/2/19 15:27
 */
public class FibSearch {
    public static void main(String[] args) {
        int[] array = {1, 8, 10, 89, 1000, 102400};
        int i = fibSearch(array, 102400);
        System.out.println(i);
    }

    /**
     * 构建斐波那契数列
     *
     * @param array
     * @return
     */
    public static int[] fib(int[] array) {
        int[] f = new int[array.length];
        f[0] = 1;
        f[1] = 1;
        for (int k = 2; k < f.length; k++) {
            f[k] = f[k - 1] + f[k - 2];
        }
        return f;
    }

    public static int fibSearch(int[] array, int searchValue) {
        int low = 0; // 相当于left
        int high = array.length - 1; // 相当于right
        int k = 0; //
        int[] f = fib(array); // 斐波那契数列
        while (high > f[k] - 1) {
            k++;
        }
        // 因为f[k]可能大于数组array的长度，因此我们需要使用Arrays类构造一个新的数组，并指向temp[]，不足的部分默认用0填充
        int[] temp = Arrays.copyOf(array, f[k]);
        // 将temp数组用0填充的部分改为用数组的最后一个值填充{1, 8, 10, 89, 1000, 1024, 0, 0} => {1, 8, 10, 89, 1000, 1024, 1024, 1024};
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = array[high];
        }
        // 寻找searchValue
        while (low <= high) {
            int mid = low + f[k - 1] - 1;
            if (searchValue < temp[mid]) {
                high = mid - 1;
                /**
                 * 1. 全部元素 = 前面的元素 + 后面的元素
                 * 2. f[k] = f[k-1] + f[k-2]
                 * 3. 因为前面有f[k-1]个元素，所以可以继续拆分为f[k-1] = f[k-2] + f[k-3]
                 * 即在f[k-1]的前面继续查找k--，mid = low + f[k-1-1]-1
                 */
                k--;
            } else if (searchValue > temp[mid]) {
                low = mid + 1;
                /**
                 * 1. 全部元素 = 前面的元素 + 后面的元素
                 * 2. f[k] = f[k-1] + f[k-2]
                 * 3. 因为后面有f[k-2]个元素，所以可以继续拆分为f[k-2] = f[k-3] + f[k-4]
                 * 即在f[k-1]的前面继续查找k-=2，mid = low + f[k-1-2]-1
                 */
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
