package com.liu.datastructure.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: 马踏棋盘算法
 * @author: liurunkai
 * @Date: 2020/4/5 16:10
 */
public class HorseChessBoard {
    private static final int X = 8; // 棋盘的行
    private static final int Y = 8; // 棋盘的列
    private static boolean[] visited; // 用于保存当前位置是否被访问，相当于下标从0-63
    private static boolean finished = false; // 用于标记是否完成马踏棋盘

    public static void main(String[] args) {
        int[][] chessBoard = new int[X][Y];
        visited = new boolean[X * Y];
        long start = System.currentTimeMillis();
        System.out.println(start);
        horseChessBoard(chessBoard,0,0,1);
        long end = System.currentTimeMillis();
        System.out.println(end);
        System.out.println(end - start);

        for (int[] ints : chessBoard) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 马踏棋盘算法
     *
     * @param chessBoard 棋盘
     * @param row        当前位置所处的行
     * @param column     当前位置所处的列
     * @param step       当前已经走过的步数
     */
    public static void horseChessBoard(int[][] chessBoard, int row, int column, int step) {
        chessBoard[row][column] = step; //将这个位置标明是第几步走的
        visited[row * X + column] = true; // 将这个位置标明为已访问
        // 获取下一步可以走的所有位置
        List<Point> pointList = next(new Point(column, row));
        /**
         * 使用贪心算法对马踏棋盘算法优化，即对pointList进行排序
         * 排序的规则是pointList的所有的point对象下一步的位置的数目进行非递减排序
         */
        sortPointList(pointList);
        while (!pointList.isEmpty()) {
            Point point = pointList.remove(0);
            if (!visited[point.y * X + point.x]) {
                // 表示该位置未访问
                horseChessBoard(chessBoard, point.y, point.x, step + 1);
            }
        }
        if (step < X * Y && !finished) {
            chessBoard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     *
     * @param pointList
     */
    private static void sortPointList(List<Point> pointList) {
        pointList.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // 获取到o1的下个位置的所有个数
                int count1 = next(o1).size();
                // 获取到o2的下个位置的所有个数
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

    /**
     * 获取马儿下一步可以走的所有位置
     *
     * @param curPoint
     * @return
     */
    public static List<Point> next(Point curPoint) {
        List<Point> pointList = new ArrayList<Point>();
        // 其中point.x 代表列，point.y代表行
        if (curPoint.x - 1 >= 0 && curPoint.y - 2 >= 0) {
            // 马儿可以向上的左面走
            pointList.add(new Point(curPoint.x - 1, curPoint.y - 2));
        }
        if (curPoint.x + 1 < X && curPoint.y - 2 >= 0) {
            // 马儿可以向上的右面走
            pointList.add(new Point(curPoint.x + 1, curPoint.y - 2));
        }
        if (curPoint.x - 1 >= 0 && curPoint.y + 2 < Y) {
            // 马儿可以向下的左面走
            pointList.add(new Point(curPoint.x - 1, curPoint.y + 2));
        }
        if (curPoint.x + 1 < X && curPoint.y + 2 < Y) {
            // 马儿可以向下的右面走
            pointList.add(new Point(curPoint.x + 1, curPoint.y + 2));
        }
        if (curPoint.x - 2 >= 0 && curPoint.y - 1 >= 0) {
            // 马儿可以向左的上面走
            pointList.add(new Point(curPoint.x - 2, curPoint.y - 1));
        }
        if (curPoint.x - 2 >= 0 && curPoint.y + 1 < Y) {
            // 马儿可以向左的下面走
            pointList.add(new Point(curPoint.x - 2, curPoint.y + 1));
        }
        if (curPoint.x + 2 < X && curPoint.y - 1 >= 0) {
            // 马儿可以向右的上面走
            pointList.add(new Point(curPoint.x + 2, curPoint.y - 1));
        }
        if (curPoint.x + 2 < X && curPoint.y + 1 < Y) {
            // 马儿可以向右的下面走
            pointList.add(new Point(curPoint.x + 2, curPoint.y + 1));
        }
        return pointList;
    }
}
