package com.liu.datastructure.tree;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/21 10:46
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
//        arrayBinaryTree.preOrder(0);
//        arrayBinaryTree.infixOrder(0);
        arrayBinaryTree.postOrder(0);
    }
}

class ArrayBinaryTree {
    private int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    // 前序遍历
    public void preOrder(Integer index) {
        System.out.println(array[index]);
        // 向左递归遍历
        if ((2 * index + 1) < array.length) {
            preOrder(2 * index + 1);
        }
        // 向右递归遍历
        if ((2 * index + 2) < array.length) {
            preOrder(2 * index + 2);
        }
    }
    // 中序遍历
    public void infixOrder(Integer index) {
        // 向左递归遍历
        if ((2 * index + 1) < array.length) {
            infixOrder(2 * index + 1);
        }
        System.out.println(array[index]);
        // 向右递归遍历
        if ((2 * index + 2) < array.length) {
            infixOrder(2 * index + 2);
        }
    }
    // 后序遍历
    public void postOrder(Integer index) {
        // 向左递归遍历
        if ((2 * index + 1) < array.length) {
            postOrder(2 * index + 1);
        }
        // 向右递归遍历
        if ((2 * index + 2) < array.length) {
            postOrder(2 * index + 2);
        }
        System.out.println(array[index]);
    }
}


