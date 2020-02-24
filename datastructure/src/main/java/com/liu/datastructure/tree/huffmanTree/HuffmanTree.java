package com.liu.datastructure.tree.huffmanTree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 赫夫曼树
 * @author: liurunkai
 * @Date: 2020/2/22 16:03
 */
public class HuffmanTree {
    public static void main(String[] args) {
        Integer[] array = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(array);
        if (root != null) {
            root.preOrder();
        }
    }

    /**
     * 创建赫夫曼树
     * @param array
     * @return
     */
    private static Node createHuffmanTree(Integer[] array) {
        List<Node> nodeList = Arrays.stream(array).map(v -> new Node(v)).collect(Collectors.toList());
        while (nodeList.size() > 1) {
            // 排序Collections.sort(nodeList);list的对象必须实现comparable接口
            Collections.sort(nodeList);
            // 取出根节点权值较小的两颗二叉树，组成一棵新的二叉树，新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(parent);
        }
        return nodeList.get(0);
    }
}

// 为了支持排序，使用collections.sort()，需要实现Comparable接口
class Node implements Comparable<Node>{
    private Integer value; //权的值，节点的权
    private Node left; //左子节点
    private Node right; // 右子节点

    // 前序遍历测试赫夫曼树
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // this.value - o.value：对value进行升序排序，o.value - this.value：对value进行降序排序
        return this.value - o.value;
    }
}
