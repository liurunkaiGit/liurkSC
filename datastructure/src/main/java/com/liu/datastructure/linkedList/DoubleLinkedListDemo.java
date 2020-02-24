package com.liu.datastructure.linkedList;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/11 20:51
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2(1, "松江");
        HeroNode2 heroNode3 = new HeroNode2(3, "黑旋风");
        HeroNode2 heroNode2 = new HeroNode2(2, "花和尚");
        HeroNode2 heroNode7 = new HeroNode2(7, "栏里白条");
        HeroNode2 heroNode6 = new HeroNode2(6, "武松");
        HeroNode2 heroNode26 = new HeroNode2(2, "花和尚222");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        System.out.println("遍历双向链表");
        doubleLinkedList.showDoubleLinkedList();
//        System.out.println("向双向链表中添加节点");
//        doubleLinkedList.addLinkedList(heroNode1);
//        doubleLinkedList.addLinkedList(heroNode3);
//        doubleLinkedList.addLinkedList(heroNode2);
//        System.out.println("遍历");
//        doubleLinkedList.showDoubleLinkedList();
        System.out.println("向双向链表中顺序添加节点");
        doubleLinkedList.addLinkedListOrder(heroNode1);
        doubleLinkedList.addLinkedListOrder(heroNode3);
        doubleLinkedList.addLinkedListOrder(heroNode2);
        doubleLinkedList.addLinkedListOrder(heroNode7);
        doubleLinkedList.addLinkedListOrder(heroNode6);
//        System.out.println("修改节点");
//        doubleLinkedList.update(heroNode26);
//        System.out.println("删除节点");
//        doubleLinkedList.delete(heroNode3);
//        doubleLinkedList.delete(heroNode1);
//        doubleLinkedList.delete(heroNode2);
        System.out.println("再次遍历");
        doubleLinkedList.showDoubleLinkedList();
//        System.out.println("再次遍历");
//        doubleLinkedList.showDoubleLinkedList();
    }
}

/**
 * 创建单链表来管理HeroNode
 */
class DoubleLinkedList {
    // 创建一个头节点,头节点不要动，头节点不存放具体的数据
    HeroNode2 head = new HeroNode2(0, "");

    /**
     * 向链表中添加数据，不考虑顺序
     *
     * @param heroNode
     */
    public void addLinkedList(HeroNode2 heroNode) {
        // 因为头节点不能动，因此找一个辅助变量来寻找链表的最后一个节点
        HeroNode2 temp = head;
        // 遍历链表，找到链表的最后一个节点
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
        // 当找到最后一个节点时，将最后一个节点的next域指向要添加的节点
        temp.setNext(heroNode);
        heroNode.setPre(temp);
    }

    /**
     * 向链表中添加数据，考虑顺序
     *
     * @param heroNode
     */
    public void addLinkedListOrder(HeroNode2 heroNode) {
        // 因为头节点不能动，因此找一个辅助变量来寻找链表的最后一个节点
        HeroNode2 temp = head;
        if (temp.getNext() == null) {
            temp.setNext(heroNode);
        } else {
            // 遍历链表，找到新添加节点的位置
            while (true) {
                if (heroNode.getNo() < temp.getNext().getNo()) {
                    heroNode.setNext(temp.getNext()); // 新添加节点的下一个节点为temp的下一个节点
                    temp.setNext(heroNode);// 设置头列表的下一个节点为新添加节点
                    heroNode.setPre(temp);
                    temp.getNext().setPre(heroNode);
                    break;
                } else {
                    if (heroNode.getNo() > temp.getNext().getNo() && temp.getNext().getNext() != null) {
                        if (heroNode.getNo() == temp.getNext().getNext().getNo()) {
                            System.out.println("该节点已存在,编号是" + heroNode.getNo());
                            return;
                        }
                    } else if (heroNode.getNo() > temp.getNext().getNo() && temp.getNext().getNext() == null) {
                        temp.getNext().setNext(heroNode);
                        heroNode.setPre(temp.getPre());
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
    public void update(HeroNode2 heroNode) {
        HeroNode2 temp = head.getNext();
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        while (true) {
            if (temp.getNo() == heroNode.getNo()) {
                temp.setName(heroNode.getName());
                break;
            }
            if (temp.getNext() != null) {
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
    public void delete(HeroNode2 heroNode) {
        HeroNode2 temp = head.getNext();
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        while (true) {
            if (temp.getNo() == heroNode.getNo()) {
                if (temp.getNext() != null) {
                    temp.getPre().setNext(temp.getNext());
                    temp.getNext().setPre(temp.getPre());
                } else {
                    temp.getPre().setNext(null);
                }
                break;
            }
            if (temp.getNext() != null) {
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
    public void showDoubleLinkedList() {
        if (head.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.getNext();
        while (true) {
            System.out.println(temp);
            temp = temp.getNext();
            if (temp == null) {
                break;
            }
        }
    }
}

/**
 * 定义HeroNode，每个heroNode对象就是一个节点
 */
class HeroNode2 {

    private int no;
    private String name;
    private HeroNode2 next; //指向下一个节点
    private HeroNode2 pre;//指向上一个节点

    public HeroNode2(int no, String name) {
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

    public HeroNode2 getNext() {
        return next;
    }

    public void setNext(HeroNode2 next) {
        this.next = next;
    }

    public HeroNode2 getPre() {
        return pre;
    }

    public void setPre(HeroNode2 pre) {
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
