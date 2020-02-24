package com.liu.datastructure.linkedList;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/2/13 11:20
 */
public class SingleCircleLinkedListDemo {
    public static void main(String[] args) {
        SingleCircleLinkedList singleCircleLinkedList = new SingleCircleLinkedList();
//        singleCircleLinkedList.addBoy(5);
//        System.out.println("遍历单向环形链表");
//        singleCircleLinkedList.show();
        singleCircleLinkedList.delete(1, 2, 5); //出圈顺序为2-4-1-5-3
    }
}

class SingleCircleLinkedList {
    // 创建第一个节点，当前没有编号
    Boy first = null;
    // 辅助变量，当前boy
    Boy curBoy = null;

    // 添加小孩节点，构建成一个环形链表
    public void addBoy(int n) {
        for (int i = 1; i <= n; i++) {
            // 创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first; // 让curBoy指向第一个小孩
            } else {
                // 让当前节点指向新加的boy
                curBoy.setNext(boy);
                // 将新加的boy指向first
                boy.setNext(first);
                // 将当前节点指向新加的boy
                curBoy = boy;
            }
        }
    }

    // 遍历单向环形链表
    public void show() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Boy temp = first;
        while (true) {
            System.out.println("当前小孩编号为" + temp.getNo());
            if (temp.getNext() == first) {
                break;
            }
            temp = temp.getNext();
        }
    }

    /**
     * 出圈
     *
     * @param start  从第几个小孩开始数
     * @param num    数到第几下出圈
     * @param boyNum 总共有多少个小孩
     */
    public void delete(int start, int num, int boyNum) {
        addBoy(boyNum); // 创建单向环形链表
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        // 需要创建一个辅助变量helper，事先应该指向环形链表的最后一个节点
        Boy temp = first;
        Boy helper = null;
        while (true) {
            if (temp.getNext() == first) {
                helper = temp;
                break;
            }
            temp = temp.getNext();
        }
        // 小孩报数前，先让first和helper移动到start-1的位置
        for (int i = 0; i < start - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        while (true) {
            if (helper == first) {//此时圈中只有一个小孩
                break;
            }
            // 出圈，让first和helper移动到num-1次，first这个节点出圈
            for (int i = 0; i < num - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("小孩出圈" + first.getNo());
            first = first.getNext();// 让开始节点指向出圈节点的下一个
            helper.setNext(first); // 出圈，让helper的下一个节点指向first节点
        }
        System.out.println("最后一个小孩出圈" + first.getNo());
    }
}

/**
 * 创建一个boy类
 */
class Boy {
    private Integer no;
    private Boy next;

    public Boy(Integer no) {
        this.no = no;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
