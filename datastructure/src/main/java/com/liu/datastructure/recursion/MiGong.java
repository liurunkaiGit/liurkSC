package com.liu.datastructure.recursion;

/**
 * @Description: 迷宫问题
 * @author: liurunkai
 * @Date: 2020/2/15 16:46
 */
public class MiGong {
    public static void main(String[] args) {
        // 创建一个地图，代表迷宫，用一个8行7列的二维数组表示
        int[][] map = new int[8][7];
        // 设置红色-墙为1，第一行和第8行
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 设置红色-墙为1，第一列和第7列
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置中间的
        map[3][1] = 1;
        map[3][2] = 1;
        System.out.println("地图");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        setWay(map, 1, 1);
        System.out.println("小球地图");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    /**
     * 1：墙代表不能走，0：代表没走过，2：代表可以走，3：走不通
     * 找路规则：下--右--上--左
     *
     * @param map 地图
     * @param i   小球起始位置
     * @param j   小球起始位置
     */
    private static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { //说明已经找到路，第7行第6列
            return true;
        } else {
            if (map[i][j] == 0) {
                // 表示该路还没有走，按照上下左右的规则开始走路
                map[i][j] = 2; //假设这条路可以走
                if (setWay(map, i + 1, j)) {//下
                    return true;
                } else if (setWay(map, i, j + 1)) {//右
                    return true;
                } else if (setWay(map, i - 1, j)) {//上
                    return true;
                } else if (setWay(map, i, j - 1)) {//左
                    return true;
                } else {
                    map[i][j] = 3; //表示此路不通，会回溯
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
