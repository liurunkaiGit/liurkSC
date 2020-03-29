package com.liu.datastructure.algorithm.dijkstra;

import java.util.Arrays;

/**
 * @Description: 迪杰斯特拉算法
 * @author: liurunkai
 * @Date: 2020/3/29 12:47
 */
public class Dijkstra {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexNum = data.length;
        int[][] edges = new int[][]{
                {Integer.MAX_VALUE, 5, 7, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2},
                {5, Integer.MAX_VALUE, Integer.MAX_VALUE, 9, Integer.MAX_VALUE, Integer.MAX_VALUE, 3},
                {7, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 8, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 9, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 4, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 8, Integer.MAX_VALUE, Integer.MAX_VALUE, 5, 4},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 4, 5, Integer.MAX_VALUE, 6},
                {2, 3, Integer.MAX_VALUE, Integer.MAX_VALUE, 4, 6, Integer.MAX_VALUE},
        };
        // 创建最小生成树
        Graph minTree = new Graph(vertexNum, data, edges);
//        minTree.show();
        minTree.dijkstra(2);
        System.out.println("执行完迪杰斯特拉算法后，显示结果");
        minTree.showAll();
    }
}

// 构造一棵最小生成树
class Graph {

    private int vertexNum; //顶点个数
    private char[] data; //存放村庄
    private int[][] edges; //存放边，即村庄与村庄之间的距离，Integer.MAX_VALUE表示不连通
    private VisitedVertex vv;

    public Graph(int vertexNum, char[] data, int[][] edges) {
        this.vertexNum = vertexNum;
        this.data = data;
        this.edges = edges;
    }

    public void show() {
        for (int[] edge : this.edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    // 迪杰斯特拉算法，index：出发顶点的下标
    public void dijkstra(int index) {
        vv = new VisitedVertex(data.length, index);
        // 更新index顶点到周围各个顶点的距离和其它各个顶点的前驱节点
        update(index);
        // 因为上面已经执行过一次update，即已经访问过一个顶点，所以遍历时从1开始即可
        for (int i = 1; i < data.length; i++) {
            int newNode = vv.getNewNode();
            update(newNode);
        }
        System.out.println(1);
    }

    // 更新顶点到其他各个顶点的距离和其它各个顶点的前驱节点,index：顶点的下标
    public void update(int index) {
        for (int i = 0; i < edges[index].length; i++) {
            // 计算出发顶点通过顶点index到顶点i的距离 = getDis(index) + edges[index][i]
            Long dis = Long.valueOf(vv.getDis(index)) + Long.valueOf(edges[index][i]);
            // 如果顶点i未被访问过并且出发顶点通过index顶点到i顶点的距离小于出发顶点直接到i顶点的距离
            if (!vv.isVisited(i) && dis < vv.getDis(i)) {
                // 更新i顶点的前驱节点为index
                vv.updatePre(i, index);
                // 更新出发顶点到i顶点的距离为dis
                int dis2 = Integer.valueOf(dis.toString());
                vv.updateDis(i, dis2);
            }
        }
    }

    // 输出所有数组
    public void showAll() {
        int[] isVisited = vv.getIsVisited();
        System.out.println(Arrays.toString(isVisited));
        int[] dis = vv.getDis();
        System.out.println(Arrays.toString(dis));
        int[] pre = vv.getPre();
        System.out.println(Arrays.toString(pre));
    }
}

class VisitedVertex {
    private int[] isVisited; //被访问过的顶点的数组，0未访问，1已访问
    private int[] dis; // 存放出发顶点到各个顶点的距离
    private int[] pre; // 存放各个顶点对应的前驱节点的下标

    /**
     * @param vertexNum 顶点个数
     * @param index     出发顶点的下标
     */
    public VisitedVertex(int vertexNum, int index) {
        isVisited = new int[vertexNum]; // 初始都为0，即都未被访问
        dis = new int[vertexNum]; // 初始化距离数组
        pre = new int[vertexNum]; // 初始化前驱节点的下标
        // 初始出发顶点到各个顶点的距离设置成Integer.MAX_VALUE,到自己的距离为0
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[index] = 0;
        isVisited[index] = 1;
    }

    // 判断顶点是否被访问过
    public boolean isVisited(int index) {
        return isVisited[index] == 1;
    }

    // 计算出发顶点到该顶点的距离
    public int getDis(int index) {
        return dis[index];
    }

    // 更新顶点j的前驱节点为index
    public void updatePre(int j, int index) {
        pre[j] = index;
    }

    // 更新出发顶点到顶点j的距离
    public void updateDis(int j, int length) {
        dis[j] = length;
    }
    // 返回新的顶点：出发顶点到周围顶点距离最小的顶点即是下一次新的顶点
    public int getNewNode() {
        int index = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < dis.length; i++) {
            // 遍历所有顶点，如果顶点未被访问并且出发顶点到i顶点的距离小于min(min是逐步替换的，目的是找出发顶点到周围顶点距离最小的顶点)
            if (isVisited[i] == 0 && dis[i] < min) {
                min = dis[i]; // 将min(最小距离替换)
                index = i; // 将新的顶点置为i
            }
        }
        // 当遍历完后，就找到出发顶点到周围顶点距离最小的顶点为index
        isVisited[index] = 1; // 设置index顶点为已访问
        return index;
    }

    public int[] getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(int[] isVisited) {
        this.isVisited = isVisited;
    }

    public int[] getDis() {
        return dis;
    }

    public void setDis(int[] dis) {
        this.dis = dis;
    }

    public int[] getPre() {
        return pre;
    }

    public void setPre(int[] pre) {
        this.pre = pre;
    }
}

