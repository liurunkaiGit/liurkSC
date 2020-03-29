package com.liu.datastructure.algorithm.kruskal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * @Description: 克鲁斯卡尔算法
 * @author: liurunkai
 * @Date: 2020/3/28 11:32
 */
public class Kruskal {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexNum = data.length;
        int[][] edges = new int[][]{
                {-1, 5, 7, -1, -1, -1, 2},
                {5, -1, -1, 9, -1, -1, 3},
                {7, -1, -1, -1, 8, -1, -1},
                {-1, 9, -1, -1, -1, 4, -1},
                {-1, -1, 8, -1, -1, 5, 4},
                {-1, -1, -1, 4, 5, -1, 6},
                {2, 3, -1, -1, 4, 6, -1},
        };
        // 创建最小生成树
        minTree minTree = new minTree(vertexNum, data, edges);
        // 打印
//        minTree.show();
//        minTree.getEdgeNum();
        // 获取边的集合
        Edge[] edges1 = minTree.getEdges();
//        System.out.println(Arrays.toString(edges1));
        minTree.sort(edges1);
//        System.out.println(Arrays.toString(edges1));
//        // 测试克鲁斯卡尔算法
        Edge[] kruskal = minTree.kruskal(edges1);
        System.out.println(Arrays.toString(kruskal));
//        Edge[] kruskal = minTree.kruskal();
//        System.out.println(Arrays.toString(kruskal));
    }

}

// 构造一棵最小生成树
class minTree {

    private int edgeNum; // 求边的数量
    private int vertexNum; //顶点个数
    private char[] data; //存放村庄
    private int[][] edges; //存放边，即村庄与村庄之间的距离，-1表示不连通

    public minTree(int vertexNum, char[] data, int[][] edges) {
        this.vertexNum = vertexNum;
        this.data = data;
        this.edges = edges;
    }

    public void show() {
        for (int[] edge : this.edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    public int getEdgeNum() {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[0].length; j++) {
                if (edges[i][j] != -1) {
                    edgeNum++;
                }
            }
        }
        edgeNum = edgeNum / 2;
//        System.out.println(edgeNum);
        return edgeNum;
    }

    /**
     * 获取所有的边的集合
     *
     * @return
     */
    public Edge[] getEdges() {
        int index = 0; // 用于存放边集合的下标
        Edge[] edges1 = new Edge[getEdgeNum()];
        for (int i = 0; i < edges.length; i++) {
            for (int j = i + 1; j < edges[0].length; j++) { // 因为是斜着对称的，边是无向的，所以不跟自己比较，即j=i+1
                if (edges[i][j] != -1) {
                    edges1[index++] = new Edge(data[i],data[j],edges[i][j]);
                }
            }
        }
        return edges1;
    }

    // 对边的集合进行从小到大排序
    public void sort(Edge[] e) {
        for (int i = 0; i < e.length - 1; i++) {
            for (int j = 0; j < e.length - i - 1; j++) {
                if (e[j].getWeight() > e[j+1].getWeight()) {
                    Edge temp = e[j];
                    e[j] = e[j+1];
                    e[j+1] = temp;
                }
            }
        }
    }

    /**
     * 获取下标为i的顶点的终点，用于判断两个顶点的终点是否相同
     * @param ends 记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中逐步形成
     * @param i 传入的顶点对应的下标
     * @return 返回的是下标为i的这个顶点对应的终点的下标
     */
    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    // 根据顶点返回对应的下标
    public int getIndex(char vertex) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == vertex) {
                return i;
            }
        }
        return -1;
    }

    // 克鲁斯卡尔算法
    public Edge[] kruskal(Edge[] edges) {
        int index = 0; //用于存放最小生成树的边的下标
        int[] ends = new int[data.length]; // 用于存放顶点对应的终点的下标
        Edge[] result = new Edge[data.length - 1]; // 用于存放结果，即存放最小生成树的所有边
//        Edge[] edges = getEdges(); // 获取到图中的所有边
//        sort(edges); //排序
        for (int i = 0; i < edges.length; i++) {
            int p1 = getIndex(edges[i].getStart()); // 用于记录边的一个端点的下标
            int p2 = getIndex(edges[i].getEnd()); // 用于记录边的另一个端点的下标
            int end1 = getEnd(ends, p1);  // 获取p1端点的终点
            int end2 = getEnd(ends, p2); // 获取p2端点的终点
            if (end1 != end2) { // 如果两个端点对应的终点不是同一个的话，说明这条边可以加进最小生成树的边的集合
                ends[end1] = end2; // 设置end1在“已有最小生成树”中的终点为end2
                result[index++] = edges[i]; // 将这条边加进最小生成树的边的集合
            }
        }
        System.out.println(Arrays.toString(ends));
        return result;
    }

    // 克鲁斯卡尔算法
    public Edge[] kruskal() {
        int index = 0; //用于存放最小生成树的边的下标
        int[] ends = new int[data.length]; // 用于存放顶点对应的终点的下标
        Edge[] result = new Edge[data.length - 1]; // 用于存放结果，即存放最小生成树的所有边
        Edge[] edges = getEdges(); // 获取到图中的所有边
        sort(edges); //排序
        for (int i = 0; i < edges.length; i++) {
            int p1 = getIndex(edges[i].getStart()); // 用于记录边的一个端点的下标
            int p2 = getIndex(edges[i].getEnd()); // 用于记录边的另一个端点的下标
            int end1 = getEnd(ends, p1);  // 获取p1端点的终点
            int end2 = getEnd(ends, p2); // 获取p2端点的终点
            if (end1 != end2) { // 如果两个端点对应的终点不是同一个的话，说明这条边可以加进最小生成树的边的集合
                ends[end1] = end2; // 设置end1在“已有最小生成树”中的终点为end2
                result[index++] = edges[i]; // 将这条边加进最小生成树的边的集合
            }
        }
        return result;
    }
}

// 边对象
class Edge {
    private char start; // 边的一个顶点
    private char end; // 边的另一个顶点
    private int weight; // 边的权值

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public char getStart() {
        return start;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public char getEnd() {
        return end;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}