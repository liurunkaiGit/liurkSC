package com.liu.datastructure.sparseArray;

import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * @Description: 稀疏数组
 * @author: liurunkai
 * @Date: 2020/2/10 20:23
 */
public class SparseArray {

    public static void main(String[] args) {
        // 模拟五子棋：先创建一个原始的11*11的二维数组，0表示没有棋子，1表示黑子，2表示蓝字
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][5] = 2;
        // 输出原始的二维数组
        System.out.println("原始的二维数组。。。");
        for (int[] ints : chessArr) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
        // 遍历二维数组，获取有效数据(非零数据)的个数
        int sum = 0;
        for (int[] ints : chessArr) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    sum ++;
                }
            }
        }
        System.out.println("sum="+sum);
        // 创建对应的稀疏数组
        int sparseArray[][] = new int[sum+1][3];
        // 给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        // 遍历二维数组，将非零的值存放到稀疏数组
        int index = 1; // 用于记录是第几个非零数据，第几个非零数据就是第几行+1行
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr.length; j++) {
                if (chessArr[i][j] != 0) {
                    sparseArray[index][0] = i;
                    sparseArray[index][1] = j;
                    sparseArray[index][2] = chessArr[i][j];
                    index++; // 每处理完一个非零数据后，行数加1
                }
            }
        }
        // 输出稀疏数组
        System.out.println("得到的稀疏数组为");
        for (int[] ints : sparseArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }

        // -----------将稀疏数组恢复成原始的二维数组-----------
        int row = sparseArray[0][0];
        int col = sparseArray[0][1];
        int chessArray2[][] = new int[row][col];
        for (int i = 1; i < sparseArray.length; i++) {
            for (int j = 0; j < sparseArray.length; j++) {
                chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
            }
        }
        System.out.println("恢复后的二维数组是");
        for (int[] ints : chessArray2) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
    }
}
