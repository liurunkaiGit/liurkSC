package com.liu.datastructure.algorithm.prim;

import java.util.Arrays;

/**
 * @Description: 普里姆算法
 * @author: liurunkai
 * @Date: 2020/3/28 11:32
 */
public class Prim {
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
        // 创建图
        Graph graph = new Graph(vertexNum);
        // 创建最小生成树
        minTree minTree = new minTree();
        minTree.createMinTree(graph, vertexNum, data, edges);
        // 打印
        minTree.show(graph);
        // 测试普利姆算法
        minTree.prim(graph,2);
    }

}

// 构造一棵最小生成树
class minTree {

    public void createMinTree(Graph graph, int vertexNum, char[] data, int[][] edges) {
        graph.setData(data);
        graph.setVertexNum(vertexNum);
        graph.setEdges(edges);
    }

    public void show(Graph graph) {
        for (int[] edge : graph.getEdges()) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * 普利姆算法解决修路路径最短问题
     *
     * @param graph 图
     * @param vertexIndex 顶点的下标，从哪个顶点开始找最小生成树
     */
    public void prim(Graph graph, int vertexIndex) {
        Integer max = Integer.MAX_VALUE; // 记录顶点之间最大值，是会不停变化的
        int h1 = -1; // 记录最短路径(边)的一端顶点
        int h2 = -1; // 记录最短路径(边)的另一端顶点
        boolean[] visited = new boolean[graph.getVertexNum()]; // 记录顶点是否已经被访问，默认false，未访问
        visited[vertexIndex] = true; //标注第vertexIndex个顶点被访问
        for (int i = 0; i < graph.getVertexNum() - 1; i++) { // 生成边的个数，有vertexNum个顶点，即vertexNum - 1条边，所以遍历vertexNum - 1次
            // 两次循环遍历寻找被访问过的顶点的最短路径
            for (int j = 0; j < graph.getVertexNum(); j++) {
                for (int z = 0; z < graph.getVertexNum(); z++) {
                    /**
                     * visited[j] 第j个顶点被访问过
                     * visited[z] 第z个顶点未被访问过
                     * graph.getEdges()[j][z] != -1 说明顶点与顶点之间是联通的
                     * graph.getEdges()[j][z] < max 寻找以j为边的起始顶点，z为边的终止顶点的最短路径
                     */
                    if (visited[j] && !visited[z] && graph.getEdges()[j][z] != -1 && graph.getEdges()[j][z] < max) {
                        // 将最短路径max重置为graph.getEdges()[j][z]
                        max = graph.getEdges()[j][z];
                        // 记录最短边的两个顶点下标
                        h1 = j;
                        h2 = z;
                    }
                }
            }
            // 当结束两次循环后，就找到最短的边
            System.out.println("边：<" + graph.getData()[h1] + "," + graph.getData()[h2] + ">，权值" + graph.getEdges()[h1][h2]);
            // 将h2标记为已访问
            visited[h2] = true;
            // 将max重置为最大
            max = Integer.MAX_VALUE;
        }
    }
}

// 初始化图对象
class Graph {
    private int vertexNum; //顶点个数
    private char[] data; //存放村庄
    private int[][] edges; //存放边，即村庄与村庄之间的距离，-1表示不连通

    public Graph(int vertexNum) {
        this.vertexNum = vertexNum;
        data = new char[vertexNum];
        edges = new int[vertexNum][vertexNum];
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public void setVertexNum(int vertexNum) {
        this.vertexNum = vertexNum;
    }

    public char[] getData() {
        return data;
    }

    public void setData(char[] data) {
        this.data = data;
    }

    public int[][] getEdges() {
        return edges;
    }

    public void setEdges(int[][] edges) {
        this.edges = edges;
    }
}
