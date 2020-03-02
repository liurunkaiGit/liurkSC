package com.liu.datastructure.tree.avl;

import java.util.Arrays;

/**
 * @Description: 平衡二叉树
 * @author: liurunkai
 * @Date: 2020/3/1 12:38
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] array = {4, 3, 6, 5, 7, 8}; // 左旋转
//        int[] array = {10, 12, 8, 9, 7, 6};// 右旋转
        int[] array = {10,11,7,6,8,9}; //双旋转
        AVLTree avlTree = new AVLTree();
        Arrays.stream(array)
                .forEach(nodeValue -> avlTree.add(new Node(nodeValue)));
        avlTree.infixOrder();
        System.out.println("==============");
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot());
        System.out.println(avlTree.getRoot().getLeft());
        System.out.println(avlTree.getRoot().getRight());
    }
}

//创建二叉排序树
class AVLTree {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}

//创建节点
class Node {
    private Integer value;
    private Node left;
    private Node right;

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    // 返回以该节点为根节点对的树的高度
    public int height() {
//        if (this.left == null) {
//            return 0;
//        }
//        int leftHeight = this.left.height();
//        if (this.right == null) {
//            return 0;
//        }
//        int rightHeight = this.right.height();
//        return Math.max(leftHeight, rightHeight) + 1;
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    public void add(Node node) {
        if (this.value > node.getValue()) { //如果当前节点的值大于要添加节点的值
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        if ((rightHeight() - leftHeight()) > 1) { //右子树与左子树的高度差大于1
            if (right != null && right.leftHeight() > right.rightHeight()) { // 右子树的左子树的高度大于右子树的高度，先将右子树右旋转
                right.rightRotate();
            }
            // 再将整棵树左旋转
            leftRotate();
            // 因为是每添加完一个节点后，就要进行一次左旋转，所以左旋转完了之后要return，防止后面发生右旋转(下面的右旋转代码)
            return;
        }
        if ((leftHeight() - rightHeight()) > 1) { //右旋转
            if (left != null && left.rightHeight() > left.leftHeight()) { // 左子树的右子树的高度大于左子树的高度，先将左子树左旋转
                left.leftRotate();
            }
            // 然后再将整颗二叉树右旋转
            rightRotate();
        }
    }

    // 左旋转
    public void leftRotate() {
        // （1）创建一个新的节点newNode(newNode的值等于根节点的值)
        Node newNode = new Node(value);
        // （2）把新的节点的左子树设置为当前节点的左子树newNode.left = left
        newNode.left = left;
        // 把新的节点的右子树设置为当前节点的右子树的左子树
        newNode.right = right.left;
        // 把当前节点的值换为右子节点的值
        value = right.value;
        //把当前节点的右子树设置为右子树的右子树
        right = right.right;
        //（6）把当前节点的左子树设置为新的节点
        left = newNode;
    }

    // 右旋转
    public void rightRotate() {
        //（1）创建一个新的节点，节点的值为根节点的值
        Node newNode = new Node(value);
        //（2）将新的节点的右子树设置为当前节点的右子树
        newNode.right = right;
        //（3）新的节点的左子树设置当前节点左子树的右子树
        newNode.left = left.right;
        //（4）将当前节点的值设置为当前节点的左子节点的值
        value = left.value;
        // 将当前节点的左子树设置为左子树的左子树
        left = left.left;
        // 将当前节点的右子树设置为新的节点
        right = newNode;
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public Node(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
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
}

