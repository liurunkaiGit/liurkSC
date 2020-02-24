package com.liu.datastructure.stack;

import java.util.Stack;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/12 13:57
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        // 入栈
        stack.add(1);
        stack.add(2);
        stack.add(3);
        // 出栈
        while (stack.size() > 0) {
            // pop 取出栈顶的第一个元素
            System.out.println(stack.pop());
        }
    }
}
