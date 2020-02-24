package com.liu.datastructure.hashTable;

/**
 * @Description: 哈希表：数组+链表
 * @author: liurunkai
 * @Date: 2020/2/20 11:37
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(3);
        Emp emp1 = new Emp(1, "name1");
        Emp emp2 = new Emp(2, "name2");
        Emp emp3 = new Emp(3, "name3");
        Emp emp4 = new Emp(4, "name4");
//        hashTable.add(emp1);
        hashTable.add(emp2);
        hashTable.add(emp3);
//        hashTable.add(emp4);
//        hashTable.list();
        hashTable.findEmp(4);
    }
}

// 哈希表
class HashTable {
    private EmpLinkedList[] empLinkedListArray;
    private Integer size;

    public HashTable(Integer size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        // 注意：这里需要初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        int empLinkedListIndex = hashFun(emp.getId());
        empLinkedListArray[empLinkedListIndex].add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public void findEmp(Integer id) {
        int empLinkedListIndex = hashFun(id);
        empLinkedListArray[empLinkedListIndex].findEmp(id);
    }

    public int hashFun(Integer id) {
        return id % size;
    }
}

// 链表
class EmpLinkedList {
    private Emp head = null; //头指针，默认为空

    // 添加员工
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        while (true) {
            if (head.getNext() == null) {
                head.setNext(emp);
                break;
            }
            head = head.getNext();
        }
    }

    //遍历员工
    public void list(Integer i) {
        if (head == null) {
            System.out.println("链表" + i + "为空");
            return;
        }
        while (true) {
            System.out.print(i + "=>" + head);
            if (head.getNext() == null) {
                break;
            }
            head = head.getNext();
        }
        System.out.println();
    }
    // 查找员工
    public void findEmp(Integer id) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        while (true) {
            if (head.getId().equals(id)) {
                System.out.println("员工"+head);
                break;
            }
            if (head.getNext() == null) {
                System.out.println("员工不存在");
                break;
            }
            head = head.getNext();
        }
    }
}

// 员工
class Emp {
    private Integer id;
    private String name;
    private Emp next;

    public Emp(Integer id, String name) {
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

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
