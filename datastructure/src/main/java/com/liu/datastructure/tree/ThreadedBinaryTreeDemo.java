package com.liu.datastructure.tree;

/**
 * @Description: 线索化二叉树
 * @author: liurunkai
 * @Date: 2020/2/21 11:54
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode2 root = new HeroNode2(1, "松江");
        HeroNode2 node2 = new HeroNode2(3, "无用");
        HeroNode2 node3 = new HeroNode2(6, "卢俊义");
        HeroNode2 node4 = new HeroNode2(8, "林冲");
        HeroNode2 node5 = new HeroNode2(10, "关胜");
        HeroNode2 node6 = new HeroNode2(14, "关胜");
        // 构建二叉树
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        // 测试中序线索化
        threadedBinaryTree.threadedNods(root);
        System.out.println("中序遍历线索化二叉树");
        threadedBinaryTree.infixOrderThreaded();
        // 测试10号节点
//        HeroNode2 left = node5.getLeft();
//        System.out.println("10号节点的前驱节点" + left);
//        HeroNode2 right = node5.getRight();
//        System.out.println("10号节点的后继节点" + right);
    }
}

class ThreadedBinaryTree {
    private HeroNode2 root;
    private HeroNode2 pre; // 指向前驱节点

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }

    public void infixOrderThreaded() {
        HeroNode2 node = root;
        while (node != null) {
            // 循环的找到leftType==1的节点，第一个找到的就是8这个节点，因为当leftType==1时，说明该节点是按照线索化处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            // 打印当前节点
            System.out.println(node);
            // 如果当前节点的后继节点指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                // 获取当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历的节点
            node = node.getRight();
        }
    }

    // 中序线索化二叉树
    public void threadedNods(HeroNode2 heroNode2) {
        if (heroNode2 == null) {
            return;
        }
        // 线索化左子树
        threadedNods(heroNode2.getLeft());
        // 线索化当前节点
        if (heroNode2.getLeft() == null) {
            //指向前驱节点
            heroNode2.setLeft(pre);
            heroNode2.setLeftType(1);
        }
        // 处理后继节点，已经是下一次，heroNode2已经指向后一个节点了，所以当前节点就变成了pre
        if (pre != null && pre.getRight() == null) {
            //指向后继节点
            pre.setRight(heroNode2);
            pre.setRightType(1);
        }
        // 将前驱节点也后移指向当前节点，因为当前节点已经后移了
        // 没处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = heroNode2;
        // 线索化右子树
        threadedNods(heroNode2.getRight());
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
    public HeroNode2 preOrderSearch(Integer id) {
        if (root != null) {
            HeroNode2 heroNode = root.preOrderSearch(id);
            return heroNode;
        } else {
            System.out.println("空");
            return null;
        }
    }
    // 中序遍历查找
    public HeroNode2 infixOrderSearch(Integer id) {
        if (root != null) {
            HeroNode2 heroNode = root.infixOrderSearch(id);
            return heroNode;
        } else {
            System.out.println("空");
            return null;
        }
    }
    // 后序遍历查找
    public HeroNode2 postOrderSearch(Integer id) {
        if (root != null) {
            HeroNode2 heroNode = root.postOrderSearch(id);
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

class HeroNode2 {
    private Integer id;
    private String name;
    private HeroNode2 left;
    private HeroNode2 right;
    // 0:左子树，1：前驱节点
    private int leftType;
    // 0:右子树，1：后继节点
    private int rightType;

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.getLeft() != null) {
            this.getLeft().preOrder();
        }
        if (this.getRight() != null) {
            this.getRight().preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.getLeft() != null) {
            this.getLeft().infixOrder();
        }
        System.out.println(this);
        if (this.getRight() != null) {
            this.getRight().infixOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.getLeft() != null) {
            this.getLeft().postOrder();
        }
        if (this.getRight() != null) {
            this.getRight().postOrder();
        }
        System.out.println(this);
    }

    // 前序遍历查找
    public HeroNode2 preOrderSearch(Integer id) {
        System.out.println("前序遍历查找");
        if (this.getId().equals(id)) {
            return this;
        }
        HeroNode2 heroNode = null;
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
    public HeroNode2 infixOrderSearch(Integer id) {
        HeroNode2 heroNode = null;
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
    public HeroNode2 postOrderSearch(Integer id) {
        HeroNode2 heroNode = null;
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

    public HeroNode2(Integer id, String name) {
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

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}