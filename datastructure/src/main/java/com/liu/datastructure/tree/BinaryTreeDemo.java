package com.liu.datastructure.tree;

/**
 * @Description: 二叉树
 * @author: liurunkai
 * @Date: 2020/2/20 16:05
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "松江");
        HeroNode node2 = new HeroNode(2, "无用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        // 构建二叉树
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");
//        binaryTree.postOrder();
//        HeroNode heroNode = binaryTree.preOrderSearch(5);
//        System.out.println(heroNode);
//        HeroNode heroNode2 = binaryTree.infixOrderSearch(5);
//        System.out.println(heroNode2);
//        HeroNode heroNode3 = binaryTree.postOrderSearch(5);
//        System.out.println(heroNode3);
//        binaryTree.preOrder();
        System.out.println("删除节点");
        binaryTree.delete(5);
        binaryTree.preOrder();
    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (root != null) {
            root.preOrder();
        }
    }
    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            this.root.infixOrder();
        }
    }
    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            root.postOrder();
        }
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(Integer id) {
        if (root != null) {
            HeroNode heroNode = root.preOrderSearch(id);
            return heroNode;
        } else {
            System.out.println("空");
            return null;
        }
    }
    // 中序遍历查找
    public HeroNode infixOrderSearch(Integer id) {
        if (root != null) {
            HeroNode heroNode = root.infixOrderSearch(id);
            return heroNode;
        } else {
            System.out.println("空");
            return null;
        }
    }
    // 后序遍历查找
    public HeroNode postOrderSearch(Integer id) {
        if (root != null) {
            HeroNode heroNode = root.postOrderSearch(id);
            return heroNode;
        } else {
            System.out.println("空");
            return null;
        }
    }
    // 删除
    public void delete(Integer id) {
        if (root != null) {
            if (!root.getId().equals(id)) {
                root.delete(id);
            } else {
                root = null;
            }
        } else {
            System.out.println("空树");
            return;
        }
    }
}

class HeroNode {
    private Integer id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if(this.getLeft() != null) {
            this.getLeft().preOrder();
        }
        if (this.getRight() != null) {
            this.getRight().preOrder();
        }
    }
    // 中序遍历
    public void infixOrder() {
        if(this.getLeft() != null) {
            this.getLeft().infixOrder();
        }
        System.out.println(this);
        if (this.getRight() != null) {
            this.getRight().infixOrder();
        }
    }
    // 后序遍历
    public void postOrder() {
        if(this.getLeft() != null) {
            this.getLeft().postOrder();
        }
        if (this.getRight() != null) {
            this.getRight().postOrder();
        }
        System.out.println(this);
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(Integer id) {
        System.out.println("前序遍历查找");
        if (this.getId().equals(id)) {
            return this;
        }
        HeroNode heroNode = null;
        if (this.getLeft() != null) {
            heroNode = this.getLeft().preOrderSearch(id);
        }
        if (heroNode != null) {
            return heroNode;
        }
        if (this.getRight() != null) {
            heroNode = this.getRight().preOrderSearch(id);
        }
        return heroNode;
    }

    // 中序遍历查找
    public HeroNode infixOrderSearch(Integer id) {
        HeroNode heroNode = null;
        if (this.getLeft() != null) {
            heroNode = this.getLeft().infixOrderSearch(id);
        }
        if (heroNode != null) {
            return heroNode;
        }
        System.out.println("中序遍历查找");
        if (this.getId().equals(id)) {
            return this;
        }
        if (this.getRight() != null) {
            heroNode = this.getRight().infixOrderSearch(id);
        }
        return heroNode;
    }

    // 后序遍历查找
    public HeroNode postOrderSearch(Integer id) {
        HeroNode heroNode = null;
        if (this.getLeft() != null) {
            heroNode = this.getLeft().postOrderSearch(id);
        }
        if (heroNode != null) {
            return heroNode;
        }
        if (this.getRight() != null) {
            heroNode = this.getRight().postOrderSearch(id);
        }
        if (heroNode != null) {
            return heroNode;
        }
        System.out.println("后序遍历查找");
        if (this.getId().equals(id)) {
            return this;
        }
        return null;
    }

    // 删除
    public void delete(Integer id) {
        if (this.getLeft() != null) {
            if (this.getLeft().getId().equals(id)) {
                this.setLeft(null);
                return;
            }
            this.getLeft().delete(id);
        }
        if (this.getRight() != null) {
            if (this.getRight().getId().equals(id)) {
                this.setRight(null);
                return;
            }
            this.getRight().delete(id);
        }
    }

    public HeroNode(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
