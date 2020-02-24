package com.liu.datastructure.stack;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/13 19:52
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(3);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.list();
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.pop();
    }
}

// 创建一个类ArrayStack来模拟栈
class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top + 1 == maxSize;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if(!isFull()) {
            top++;
            stack[top] = value;
        } else {
            System.out.println("栈满");
        }
    }

    public void pop() {
        if(!isEmpty()) {
            System.out.println(stack[top]);
            top--;
        } else {
            System.out.println("占空");
            throw new RuntimeException("栈空");
        }
    }

    public void list() {
        if(!isEmpty()) {
            for (int i = top; i >= 0; i--) {
                System.out.println(stack[i]);
            }
        } else {
            System.out.println("栈空");
        }
    }
}
