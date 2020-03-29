package com.liu.datastructure.algorithm.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 动态规划算法-背包问题
 * @author: liurunkai
 * @Date: 2020/3/8 15:23
 */
public class Knapsack {
    public static void main(String[] args) {
        int[] price = {1500, 3000, 2000}; //存放物品的价格
        int[] weight = {1, 4, 3}; //存放物品的重量
        int knapsackWeight = 4; //存放背包的容量
        List<Integer> goodNums = new ArrayList<>(); // 用来存放商品的下标
        int[][] v = new int[price.length + 1][knapsackWeight + 1]; // 存放第i个物品在背包容量为j时的最大价格
        int[][] path = new int[price.length + 1][knapsackWeight + 1]; // 存放存入的商品
        // 初始化二维数组v的第一行与第一列都为0，其实不处理也行，不处理默认初始值就为0
        // 即当背包容量为0时，可以放入0个商品或者当商品数量为0时，也是可以放入0个商品
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0; // 第一列都为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0; // 第一行都为0
        }
        // 动态添加商品
        for (int i = 1; i < v.length; i++) { // 遍历商品
            for (int j = 1; j < v[0].length; j++) { // 背包容量从0开始添加商品
                if (weight[i - 1] > j) { // 第i个商品的重量大于背包的容量，因为i从1开始遍历，所以weight[]要减1
                    v[i][j] = v[i - 1][j];
                } else { // 第i个商品的重量小于等于背包的容量
                    // 判断当第i个商品的价格+放了第i个商品后还能存放的商品的价格和第i-1个商品的价格
                    if (v[i - 1][j] < (price[i - 1] + v[i - 1][j - weight[i - 1]])) {// 因为i从1开始遍历，所以weight[]要减1
                        v[i][j] = price[i - 1] + v[i - 1][j - weight[i - 1]]; // 因为i从1开始遍历，所以weight[]要减1
                        // 记录下放入的商品，因为只有在这种情况下放入的商品价格最大
                        // 这里输出会有冗余的数据，因为这样会把所有的放入情况都得到，但是我们只需要最后一次的放入情况
                        System.out.println(i);
                        goodNums.add(i - 1); //将存放过得商品的下标记录起来
                        path[i][j] = 1; //将存放过得商品的下标记录起来
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        //打印二位数组
        for (int i = 0; i < v.length; i++) { // 遍历二位数组的行
            for (int j = 0; j < v[0].length; j++) { // 遍历二维数组的列
                System.out.print(v[i][j] + " ");
            }
            System.out.println(); // 每遍历完一行换行
        }
        System.out.println("寻找存放的商品");
        for (int i = 0; i < goodNums.size(); i++) {
            System.out.println("第"+(goodNums.get(i)+1)+"个商品放入背包"); //但是这样会有冗余，因为里面存放的是所有存放过得商品
            // 从后往前开始遍历寻找符合的商品，可以先将重复的商品去重，或者用set来存储，因为一个商品只能放入一次
        }
        System.out.println("使用二维数组path记录最终存放的商品====会有冗余商品");
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[0].length; j++) {
                if (path[i][j] == 1) {
                    //但是这样会有冗余，因为里面存放的是所有存放过得商品即所有的放入情况，但我们只需要最后一次的放入情况
                    System.out.println("第"+i+"个商品放入背包");
                }
            }
        }
        System.out.println("使用二维数组path记录最终存放的商品====不会有冗余商品");
        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1; //列的最大下标
        while (i > 0 && j > 0) { // 从path的最后开始找，因为只找最后一次 存放的商品
            if (path[i][j] == 1) {
                System.out.println("第"+i+"个商品放入背包");
                j -= weight[i-1];  // 调整背包的剩余容量
            }
            i--;  // 因为已经找到一个商品，所以要减一个
        }
    }
}
