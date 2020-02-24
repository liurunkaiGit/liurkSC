package com.liu.datastructure.queue;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/11 20:01
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        CircleArrayQueue arrayQueue = new CircleArrayQueue(3);
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
        arrayQueue.getQueue();
        arrayQueue.getQueue();
//        System.out.println("添加数据");
//        // 问题：虽然数据已经取出，但是原来的空间不能用，再次添加数据还是加不进去
        arrayQueue.addQueue(4);
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

class CircleArrayQueue {
    /**
     * 表示数组的最大容量
     */
    private int maxSize;
    // 队列头:front的初始值就指向队列的第一个元素，即front的初始值为0
    private int front;
    // 队列尾；rear指向队列的最后一个元素的后一个位置，空出一个空间作为约定，rear的初始值也为0
    private int rear;
    // 该数组用于存放数据，用于模拟队列
    private int[] array;

    public CircleArrayQueue(Integer maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
            array[rear] = data;
            rear = (rear + 1) % maxSize;
        } else {
            System.out.println("队列已满");
        }
    }

    /**
     * 显示队列数据
     */
    public void showQueue() {
        if (!isEmpty()) {
            // 获取当前队列的有效个数
            int count = (rear + maxSize - front) % maxSize;
            // 从front开始遍历,因为前面的元素已经被取出
            for (int i = front; i < front + count; i++) {
                System.out.println(array[i % maxSize]);
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
            System.out.println(array[front]);
            front = (front + 1) % maxSize;
        } else {
            System.out.println("队列为空");
        }
    }

    /**
     * 展示队列头数据，不是取出数据
     */
    public void headQueue() {
        if (!isEmpty()) {
            System.out.println(array[front]);
        } else {
            System.out.println("队列为空");
        }
    }

}