package com.liu.datastructure.queue;

/**
 * @Description: 使用数组模拟队列
 * @author: liurunkai
 * @Date: 2020/2/11 10:48
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        arrayQueue.addQueue(1);
        arrayQueue.addQueue(2);
        arrayQueue.addQueue(3);
        System.out.println("遍历队列");
        arrayQueue.showQueue();
//        System.out.println("显示队列头数据1");
//        arrayQueue.headQueue();
//        System.out.println("显示队列头数据2");
//        arrayQueue.headQueue();
        System.out.println("从队列取出数据");
        arrayQueue.getQueue();
//        arrayQueue.getQueue();
//        arrayQueue.getQueue();
//        System.out.println("添加数据");
//        // 问题：虽然数据已经取出，但是原来的空间不能用，再次添加数据还是加不进去
//        arrayQueue.addQueue(4);
        System.out.println("显示队列头数据3");
        arrayQueue.headQueue();
        System.out.println("遍历队列");
        arrayQueue.showQueue();
//        System.out.println("开始取头数据");
//        for (int i = 0; i < 2; i++) {
//            arrayQueue.getQueue();
//        }
//        System.out.println("显示队列头数据");
//        arrayQueue.headQueue();
//        System.out.println("遍历队列");
//        arrayQueue.showQueue();
    }

}

class ArrayQueue {
    /**
     * 表示数组的最大容量
     */
    private int maxSize;
    // 队列头
    private int front;
    // 队列尾
    private int rear;
    // 该数组用于存放数据，用于模拟队列
    private int[] array;

    public ArrayQueue(Integer maxSize) {
        this.maxSize = maxSize;
        front = -1; //指向队列头部，front是指向队列头的前一个位置
        rear = -1; // 指向队列尾部，指向队列尾的数据(即就是队列最后一个数据)
        array = new int[maxSize];
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据
     */
    public void addQueue(int data) {
        // 判断队列是否已满
        if (!isFull()) {
            rear++;
            array[rear] = data;
        } else {
            System.out.println("队列已满");
        }
    }

    /**
     * 显示队列数据
     */
    public void showQueue() {
        if (!isEmpty()) {
            // 遍历时，i从front+1开始，因为前面的元素可能被取出
            for (int i = front + 1; i < array.length; i++) {
                System.out.println(array[i]);
            }
        } else {
            System.out.println("队列为空");
        }
    }

    /**
     * 获取数据
     */
    public void getQueue() {
        if (!isEmpty()) {
            // System.out.println(array[++front]);
            front++;
            System.out.println(array[front]);
        } else {
            System.out.println("队列为空");
        }
    }

    /**
     * 展示队列头数据，不是取出数据
     */
    public void headQueue() {
        if(!isEmpty()) {
            System.out.println(array[front+1]);
        } else {
            System.out.println("队列为空");
        }
    }

}
