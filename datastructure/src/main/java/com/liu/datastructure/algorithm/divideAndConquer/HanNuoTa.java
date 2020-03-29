package com.liu.datastructure.algorithm.divideAndConquer;

/**
 * @Description: 分治算法-汉诺塔
 * @author: liurunkai
 * @Date: 2020/3/8 13:23
 */
public class HanNuoTa {
    public static void main(String[] args) {
        hanNuoTa(3, 'A', 'B', 'C');
    }

    /**
     * @param num 圆盘数量
     * @param a   柱子A
     * @param b
     * @param c
     */
    public static void hanNuoTa(int num, char a, char b, char c) {
        if (num == 1) {
            // 如果只有一个圆盘
            System.out.println("第一个圆盘从 " + a + "到" + c);
        } else {
            // 如果圆盘数量大于等于2，则看成两个圆盘：1.最下面一个2.除了最下面一个上面的所有圆盘
            // 将上面的所有圆盘从a移到b，其中借助c
            hanNuoTa(num - 1, a, c, b);
            // 将最下面一个圆盘(即第num个圆盘)从a到c，其中借助b
            System.out.println("第" + num + "个圆盘从 " + a + "到" + c);
            // 然后再将剩下的圆盘从b移到c，其中借助a
            hanNuoTa(num - 1, b, a, c);
        }
    }
}
