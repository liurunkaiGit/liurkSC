package com.liu.datastructure.linkedList;

import java.util.Stack;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/11 20:51
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "松江");
        HeroNode heroNode3 = new HeroNode(3, "黑旋风");
        HeroNode heroNode2 = new HeroNode(2, "花和尚");
        HeroNode heroNode7 = new HeroNode(7, "栏里白条");
        HeroNode heroNode6 = new HeroNode(6, "武松");
        HeroNode heroNode26 = new HeroNode(2, "花和尚222");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        System.out.println("遍历单链表");
//        singleLinkedList.showSingleLinkedList();
        System.out.println("向链表中添加节点");
//        singleLinkedList.addLinkedList(heroNode1);
//        singleLinkedList.addLinkedList(heroNode3);
//        singleLinkedList.addLinkedList(heroNode2);
        System.out.println("向单链表中顺序添加节点");
        singleLinkedList.addLinkedListOrder(heroNode1);
        singleLinkedList.addLinkedListOrder(heroNode3);
        singleLinkedList.addLinkedListOrder(heroNode2);
        singleLinkedList.addLinkedListOrder(heroNode7);
        singleLinkedList.addLinkedListOrder(heroNode6);
        System.out.println("再次遍历");
        singleLinkedList.showSingleLinkedList();
//        System.out.println("反转");
//        singleLinkedList.revert();
//        System.out.println("再次遍历");
//        singleLinkedList.showSingleLinkedList();
        System.out.println("从尾到头打印单链表");
//        singleLinkedList.print1();
        singleLinkedList.print2();
//        int length = singleLinkedList.getLength();
//        System.out.println("长度是"+length);
//        singleLinkedList.getKNode(1);
//        System.out.println("修改节点");
//        singleLinkedList.update(heroNode26);
//        System.out.println("删除节点");
//        singleLinkedList.delete(heroNode7);
//        System.out.println("再次遍历");
//        singleLinkedList.showSingleLinkedList();
    }
}

/**
 * 创建单链表来管理HeroNode
 */
class SingleLinkedList {
    // 创建一个头节点,头节点不要动，头节点不存放具体的数据
    HeroNode head = new HeroNode(0, "");

    /**
     * 向链表中添加数据，不考虑顺序
     *
     * @param heroNode
     */
    public void addLinkedList(HeroNode heroNode) {
        // 因为头节点不能动，因此找一个辅助变量来寻找链表的最后一个节点
        HeroNode temp = head;
        // 遍历链表，找到链表的最后一个节点
        while (true) {
            if(temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
        // 当找到最后一个节点时，将最后一个节点的next域指向要添加的节点
        temp.setNext(heroNode);
    }

    /**
     * 向链表中添加数据，考虑顺序
     *
     * @param heroNode
     */
    public void addLinkedListOrder(HeroNode heroNode) {
        // 因为头节点不能动，因此找一个辅助变量来寻找链表的最后一个节点
        HeroNode temp = head;
        if(temp.getNext() == null) {
            temp.setNext(heroNode);
        } else {
            // 遍历链表，找到新添加节点的位置
            while (true) {
                if(heroNode.getNo() < temp.getNext().getNo()) {
                    heroNode.setNext(temp.getNext());
                    temp.setNext(heroNode);
                    break;
                } else {
                    if(heroNode.getNo() > temp.getNext().getNo() && temp.getNext().getNext() != null) {
                        if(heroNode.getNo() == temp.getNext().getNext().getNo()) {
                            System.out.println("该节点已存在,编号是"+heroNode.getNo());
                            return;
                        }
                    } else if(heroNode.getNo() > temp.getNext().getNo() && temp.getNext().getNext() == null){
                        temp.getNext().setNext(heroNode);
                        break;
                    }
                }
                temp = temp.getNext();
            }
        }
    }

    /**
     * 根据编号修改节点的信息
     */
    public void update(HeroNode heroNode) {
        HeroNode temp = head.getNext();
        if(temp == null) {
            System.out.println("链表为空");
            return;
        }
        while (true) {
            if(temp.getNo() == heroNode.getNo()) {
                temp.setName(heroNode.getName());
                break;
            }
            if(temp.getNext() != null) {
                temp = temp.getNext();
            } else {
                System.out.println("该节点不存在");
                break;
            }
        }
    }

    /**
     * 根据编号删除节点的信息
     */
    public void delete(HeroNode heroNode) {
        HeroNode temp = head;
        if(temp.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        while (true) {
            if(temp.getNext().getNo() == heroNode.getNo()) {
                if(temp.getNext().getNext() != null) {
                    temp.setNext(temp.getNext().getNext());
                } else {
                    temp.setNext(null);
                }
                break;
            }
            if(temp.getNext() != null) {
                temp = temp.getNext();
            } else {
                System.out.println("该节点不存在");
                break;
            }
        }
    }

    /**
     * 遍历链表
     */
    public void showSingleLinkedList() {
        if(head.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.getNext();
        while (true) {
            System.out.println(temp);
            temp = temp.getNext();
            if (temp == null) {
                break;
            }
        }
    }

    /**
     * 求单链表中有效节点个数
     */
    public int getLength() {
        HeroNode temp = head.getNext();
        if(temp == null) {
            System.out.println("链表为空");
            return 0;
        }
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.getNext();
        }
        return length;
    }

    /**
     * 获取倒数第k个节点
     */
    public HeroNode getKNode(int k) {
        int k1 = getLength() - k + 1;
        HeroNode temp = head.getNext();
        if(temp == null) {
            System.out.println("链表为空");
            return null;
        }
        for (int i = 1; i <= getLength(); i++) {
            if(i == k1) {
                System.out.println("倒数第" + k + "个节点是" + temp);
                break;
            }
            temp = temp.getNext();
        }
        return temp;
    }

    /**
     * 单链表反转
     */
    public HeroNode revert() {
        // 链表为空或者只有一个节点直接返回
        if(head.getNext() == null || head.getNext().getNext() == null) {
            return null;
        }
        HeroNode temp = head.getNext();
        HeroNode next = null;
        HeroNode revertNode = new HeroNode(0,"");
        // 遍历链表：每遍历一个节点，就将其取出并放到新的链表revertNode的最前端
        while (temp != null) {
            // 保存当前节点的下一个节点
            next = temp.getNext();
            // 将temp的下一个节点指向新链表的最前端
            temp.setNext(revertNode.getNext());
            // 将temp连接到新的链表上
            revertNode.setNext(temp);
            // temp后移
            temp = next;
        }
        head.setNext(revertNode.getNext());
        return head;
    }

    /**
     * 从尾到头打印单向链表-利用倒数第k个节点
     */
    public void print1() {
        HeroNode temp = head;
        if(temp.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        for (int i = 1; i <= getLength(); i++) {
            System.out.println(getKNode(i));
        }
    }

    /**
     * 从尾到头打印单向链表-利用栈
     */
    public void print2() {
        HeroNode temp = head.getNext();
        if(temp.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        Stack<HeroNode> heroNodeStack = new Stack<>();
        while (temp != null) {
            heroNodeStack.add(temp);
            temp = temp.getNext();
        }
        while (heroNodeStack.size() > 0) {
            System.out.println(heroNodeStack.pop());
        }
    }

    /**
     * 从尾到头打印单向链表-先反转后遍历,不建议因为修改了单链表的存储顺序
     */
    public void print3() {
        HeroNode revert = revert();
        showSingleLinkedList();
    }
}

/**
 * 定义HeroNode，每个heroNode对象就是一个节点
 */
class HeroNode {

    private int no;
    private String name;
    private HeroNode next; //指向下一个节点

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
