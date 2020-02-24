package com.liu.datastructure.recursion;

/**
 * @Description: 八皇后问题
 * @author: liurunkai
 * @Date: 2020/2/15 19:14
 */
public class EightKing {

    int[] array = new int[8];
    static int count = 0;
    public static void main(String[] args) {
        EightKing eightKing = new EightKing();
        eightKing.check(0);
        System.out.println("一共有"+count+"种解法");
    }

    /**
     * 放置皇后
     *
     * @param n
     */
    public void check(int n) {
        if(n == 8) { //因为n从0开始，当n=8时说明开始放置第9个皇后即前8个皇后已经放好
            print();
            return;
        }
        // 依次放入皇后，并判断是否冲突
        for (int i = 0; i < 8; i++) {
            // 先把当前这个皇后放到当前行的第i列
            array[n] = i;
            if (judge(n)) {
                // 说明当前这个皇后放置的这个位置不冲突，则开始放置下一个皇后
                check(n+1);
            }
            // 如果冲突，则继续执行array[n]=i即将该皇后放置到当前冲突列的下一列位置，因为for循环，i++相当于下一列
        }
    }

    /**
     * 检测第n个皇后和前面n减一个皇后是否冲突
     *
     * @param n n表示放置第n个皇后
     * @return
     */
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // array[n] == array[i] 判断第n个皇后和第i个皇后是否在同一列
            // Math.abs(n - i) == Math.abs(array[n] - array[i]) 判断第n个皇后和第i个皇后是否在同一斜线
            // 没必要判断是否在同一行，因为第n个皇后n就表示第几行，所以不存在同一行的情况
            if (array[n] == array[i] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    // 遍历array
    public void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "");
        }
        System.out.println();
    }
}
