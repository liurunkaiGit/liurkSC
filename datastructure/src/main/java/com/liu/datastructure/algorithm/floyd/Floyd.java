package com.liu.datastructure.algorithm.floyd;

import java.util.Arrays;

/**
 * @Description: 弗洛伊德算法
 * @author: liurunkai
 * @Date: 2020/4/4 17:06
 */
public class Floyd {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] edges = new int[][]{
                {65535, 5, 7, 65535, 65535, 65535, 2},
                {5, 65535, 65535, 9, 65535, 65535, 3},
                {7, 65535, 65535, 65535, 8, 65535, 65535},
                {65535, 9, 65535, 65535, 65535, 4, 65535},
                {65535, 65535, 8, 65535, 65535, 5, 4},
                {65535, 65535, 65535, 4, 5, 65535, 6},
                {2, 3, 65535, 65535, 4, 6, 65535},
        };
        // 创建最小生成树
        Graph minTree = new Graph(data, edges);
        minTree.floyd();
        minTree.show();
    }
}

class Graph {
    private char[] vertex; // 保存顶点数组
    private int[][] dis; // 保存各个顶点到其它顶点的距离
    private int[][] pre; // 保存各个顶点的前驱节点

    public Graph(char[] vertex, int[][] dis) {
        this.vertex = vertex;
        this.dis = dis;
        this.pre = new int[vertex.length][vertex.length];
        // 初始化前驱节点数组
        for (int i = 0; i < vertex.length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void show() {
        System.out.println("前驱节点为");
        for (int[] ints : pre) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("距离为");
        for (int[] di : dis) {
            System.out.println(Arrays.toString(di));
        }
    }

    public void floyd() {
        int ljk = 0;
        // 遍历中间顶点，中间顶点从A开始
        for (int k = 0; k < vertex.length; k++) {
            // 遍历出发顶点，从A顶点开始出发
            for (int i = 0; i < vertex.length; i++) {
                // 遍历结束顶点，结束顶点从A开始出发
                for (int j = 0; j < vertex.length; j++) {
                    ljk = dis[i][k] + dis[k][j];
                    if (ljk < dis[i][j]) {
                        dis[i][j] = ljk;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}
