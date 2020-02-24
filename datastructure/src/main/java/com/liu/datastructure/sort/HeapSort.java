package com.liu.datastructure.sort;

import java.util.Arrays;

/**
 * @Description: 堆排序
 * @author: liurunkai
 * @Date: 2020/2/22 11:42
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] array = {4, 6, 8, 5, 3, 9, 0, -1, 10};
//        int[] array = {4, 6, 8, 5, 9, -1, 90, 89, 56, -999};
//        adjustHeap(array, array.length / 2 - 1, array.length);
//        System.out.println(Arrays.toString(array));
//        adjustHeap(array, 0, array.length);
//        System.out.println(Arrays.toString(array));
        // 先将无序数组构建成一个大顶堆，数组中最大的元素就到了第一个
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
//        System.out.println(Arrays.toString(array));
        // 将数组中第一个元素(最大的一个)与数组最后一个交换
        for (int j = array.length - 1; j > 0; j--) {
            int temp = array[j];
            array[j] = array[0];
            array[0] = temp;
            // 每交换完一次就将剩余的元素重新构建成大顶堆
            adjustHeap(array, 0, j);
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 将以i为非叶子节点的树调整成大顶堆，非叶子节点从左到右、从下往上开始
     * 只是调整以i作为父节点的树为大顶堆，并不是调整整个数组为大顶堆
     * eg：第一次非叶子节点为6，左子节点为5，右子节点为9，只是将这三个数构成的树调整为大顶堆
     *
     * @param array  需要调整的数组
     * @param i      非叶子节点的下标 第一次：array.length / 2 - 1 = 1
     * @param length 需要调整的数组的长度，在逐渐变化
     * @return
     */
    public static int[] adjustHeap(int[] array, int i, int length) {
        // 判断以i为父节点，左右子节点的值得大小
        int temp = array[i];
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            //防止i下面没有右子节点，数组越界，说明左子节点的值小于右子节点的值
            if (j + 1 < length && array[j] < array[j + 1]) {
                j++; // j指向右子节点
            }
            if (array[j] > temp) { // 子节点的值大于父节点的值
                // 交换父节点的值与子节点的值，以构成大顶堆
                array[i]  = array[j];
                // i指向j，继续循环下次比较
                i = j;
            } else {
                break;
            }
        }
        // 当for循环结束后，我们已经将i为父节点的树的最大值，放到了根节点的位置，使其构成了大顶堆
        // 将temp放到调整后的位置
        array[i] = temp;
        return array;
    }
}
