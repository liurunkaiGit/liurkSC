package com.liu.datastructure.tree;

import java.util.Arrays;

/**
 * @Description: 二叉排序树
 * @author: liurunkai
 * @Date: 2020/2/28 19:39
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] array = {7, 3, 10, 12, 5, 1, 9, 0};
        BinarySortTree binarySortTree = new BinarySortTree();
        Arrays.stream(array)
                .forEach(nodeValue -> binarySortTree.add(new Node(nodeValue)));
        binarySortTree.infixOrder();
        System.out.println("==============");
//        binarySortTree.del(2);
//        binarySortTree.del(5);
//        binarySortTree.del(9);
//        binarySortTree.del(12);
//        binarySortTree.del(1);
//        binarySortTree.del(3);
        binarySortTree.del(7);
        binarySortTree.infixOrder();
//        System.out.println(binarySortTree.searchDelNode(1));
//        System.out.println(binarySortTree.searchDelNodeParent(3));

    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    public void del(int value) {
        if (root != null) {
//            if (root.getValue() != value) {
                root.del(value);
//            }
        } else {
            System.out.println("空树");
        }
    }

    public Node findMinNode(int value) {
        return root.findMinNode(new Node(value));
    }

    public Node searchDelNode(int value) {
        if (root != null) {
            if (root.getValue() != value) {
                return root.searchDelNode(value);
            } else {
                return root;
            }
        } else {
            return null;
        }
    }

    public Node searchDelNodeParent(int value) {
        if (root != null) {
            if (root.getValue() != value) {
                return root.searchDelNodeParent(value);
            } else {
                return root;
            }
        } else {
            return null;
        }
    }

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
}

//创建节点
class Node {
    private Integer value;
    private Node left;
    private Node right;

    public Node(Integer value) {
        this.value = value;
    }

    // 删除节点
    public void del(int value) {
        Node node = searchDelNode(value);
        Node parentNode = searchDelNodeParent(value);
        if (node != null) {
            if (node.left == null && node.right == null) { //删除的节点是叶子节点
                if (parentNode.left != null && parentNode.left.value == value) {
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == value) {
                    parentNode.right = null;
                }
            } else if (node.left != null && node.right != null) { //删除有两个子节点的节点
                // 找到待删除节点的右子树的最小节点
                Node minNode = findMinNode(node.right);
                node.value = minNode.value;
            } else { //删除有1个子节点的节点
                if (node.left != null) {
                    Node tempNode = node.left;
                    if (parentNode.left != null && parentNode.left.value == value) {
                        parentNode.left = tempNode;
                    } else if (parentNode.right != null && parentNode.right.value == value) {
                        parentNode.right = tempNode;
                    }
                } else {
                    Node tempNode = node.right;
                    if (parentNode.left != null && parentNode.left.value == value) {
                        parentNode.left = tempNode;
                    } else if (parentNode.right != null && parentNode.right.value == value) {
                        parentNode.right = tempNode;
                    }
                }
            }
        }
    }

    // 找最小节点,并删除
    public Node findMinNode(Node node) {
        Node minNode = null;
        if (node.left == null) {
            minNode = node; // 将最小节点返回
        } else {
            minNode = findMinNode(node.left);
        }
        del(minNode.value);
        return minNode;
//        while (node.left != null) {
//            node = node.left;
//        }
//        del(node.value);
//        return node;
    }

    // 查找要删除的节点
    public Node searchDelNode(int value) {
        if (this.value == value) {
            return this;
        } else if (this.value <= value) {
            if (this.right == null) {
                return null;
            }
            return this.right.searchDelNode(value);
        } else {
            if (this.left == null) {
                return null;
            }
            return this.left.searchDelNode(value);
        }
    }

    // 查找要删除节点的父节点
    public Node searchDelNodeParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else if (this.value <= value) {
            return this.right.searchDelNodeParent(value);
        } else if (this.value > value) {
            return this.left.searchDelNodeParent(value);
        }
        return null;
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
}
