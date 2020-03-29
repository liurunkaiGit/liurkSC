package com.liu.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 图的入门
 * @author: liurunkai
 * @Date: 2020/3/7 11:35
 */
public class Graph {

    // 存放顶点的集合
    private List<String> vertexs;
    // 存放边的二维数组(邻接矩阵)
    private int[][] edges;
    // 存放边的个数
    private int edgeNum;
    // 保存节点是否已经被访问
    private boolean[] isVisited;

    // 构造函数
    public Graph(int n) {
        vertexs = new ArrayList<>(n);
        edges = new int[n][n];
        edgeNum = 0;
        isVisited = new boolean[n];
    }

    // 返回顶点个数
    public int vertexNum() {
        return vertexs.size();
    }

    // 返回下标对应的顶点
    public String getValueByIndex(int i) {
        return vertexs.get(i);
    }

    // 查找当前节点的第一个节点
    public int getFirstNextNode(int i) {
        for (int j = 0; j < vertexs.size(); j++) {
            if (edges[i][j] == 1) {
                return j;
            }
        }
        return -1;
    }

    // 寻找以i节点的下一个节点的j的下一个邻接节点eg：a节点的b节点被访问，则寻找a节点开始b节点的下一个邻接节点c，以a通过b找c
    public int getNextNode(int i, int j) {
        for (int k = j + 1; k < vertexs.size(); k++) {
            if (edges[i][k] == 1) {
                return k;
            }
        }
        return -1;
    }

    // 遍历一个顶点的所有节点
    public void dfs(boolean[] isVisited, int i) {
        // 输出当前节点
        System.out.println(getValueByIndex(i) + "->");
        // 表示当前节点已经被访问
        isVisited[i] = true;
        // 查找当前节点的下一个节点
        int w = getFirstNextNode(i);
        // 如果节点存在
        while (w != -1) {
            if (!isVisited[w]) {
                // 节点没有被访问，就以w节点为初始节点访问
                dfs(isVisited, w);
            }
            // 如果当前节点已经被访问，则寻找以i节点的下一个节点的w的下一个邻接节点
            w = getNextNode(i, w);
        }
    }

    // 循环遍历所有顶点
    public void dfs() {
        for (int i = 0; i < vertexs.size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    // 广度优先遍历一个节点
    public void bfs(boolean[] isVisited, int i ) {
        /**
         * 1）访问初始节点v并标记v已访问
         * 2）将节点v加入队列尾部
         * 3）当队列非空时，继续执行，否则对v这个节点的算法结束
         * 4）出队列，取得对列头节点u
         * 5）查找节点u的第一个邻接节点w
         * 6）若节点u的邻接节点w不存在，则转到步骤3，否则循环执行一下三个步骤
         * A. 若节点w未被访问，则访问节点w并标记w已访问
         * B. 节点w入队列
         * C. 若节点已经被访问，则查找节点u继w邻接节点后的下一个邻接节点w，转到步骤6
         */
        int u; //对列头节点u
        int w; //查找节点u的第一个邻接节点w
        LinkedList<Integer> queue = new LinkedList<>(); // 队列
        System.out.println(getValueByIndex(i) + "->");
        isVisited[i] = true;
        queue.addLast(i); // 将节点v加入队列尾部
        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = getFirstNextNode(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.println(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNode(u,w);
            }
        }
    }

    //
    public void bfs() {
        for (int i = 0; i < vertexs.size(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited,i);
            }
        }
    }

    //添加顶点
    public void addVertex(String vertex) {
        vertexs.add(vertex);
    }

    // 添加边
    public void addEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgeNum++;
    }

    // 打印图
    public void print() {
        for (int[] edge : edges) {
            for (int i : edge) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int vertexNum = 5;
        Graph graph = new Graph(vertexNum);
        String[] vertexs = {"A", "B", "C", "D", "E"};
        Arrays.stream(vertexs).forEach(vertex -> graph.addVertex(vertex));
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);
        graph.print();
//        System.out.println("测试图的深度优先遍历");
//        graph.dfs();
        System.out.println("测试图的广度优先遍历");
        graph.bfs();
    }
}
