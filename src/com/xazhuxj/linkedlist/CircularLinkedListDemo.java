package com.xazhuxj.linkedlist;

import javax.sound.midi.Soundbank;

public class CircularLinkedListDemo {
    public static void main(String[] args) {
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        circularLinkedList.add(25);
        circularLinkedList.list();
        circularLinkedList.countOut(1, 2, 25); //出圈的顺序为2->4->1->5->3*
    }
}

/**
 * 单向环形链表
 * 解决 Josephus 约瑟夫环问题
 * 约瑟夫问题（有时也称为约瑟夫斯置换，是一个出现在计算机科学和数学中的问题。在计算机编程的算法中，类似问题又称为约瑟夫环。又称“丢手绢问题”.）
 * 据说著名犹太历史学家 Josephus有过以下的故事：
 * 在罗马人占领乔塔帕特后，39 个犹太人与Josephus及他的朋友躲到一个洞中，
 * 39个犹太人决定宁愿死也不要被敌人抓到，于是决定了一个自杀方式，41个人排成一个圆圈，
 * 由第1个人开始报数，每报数到第3人该人就必须自杀，然后再由下一个重新报数，直到所有人都自杀身亡为止。
 * 然而Josephus 和他的朋友并不想遵从。
 * 首先从一个人开始，越过k-2个人（因为第一个人已经被越过），并杀掉第k个人。
 * 接着，再越过k-1个人，并杀掉第k个人。这个过程沿着圆圈一直进行，直到最终只剩下一个人留下，这个人就可以继续活着。
 * 问题是，给定了和，一开始要站在什么地方才能避免被处决？
 * Josephus要他的朋友先假装遵从，他将朋友与自己安排在第16个与第31个位置，于是逃过了这场死亡游戏
 */
class CircularLinkedList {

    //创建一个first节点，当前没有编号
    private CircularNode first = null;

    //构建单向环形链表的思路
    //1. 先创建第一个节点，让first指向该节点，并形成环形
    //2. 后面当我们每创建一个新的节点，就把该节点加入发哦已有的环形链表中即可
    public void add(int nums) { //创建nums个小孩的单向循环链表
        if (nums < 1) {
            System.out.println("nums值不正确，应>0");
            return;
        }

        CircularNode cur = null; //辅助，帮助构建环形链表
        //使用for循环创建
        for (int i = 1; i <= nums; i++) {
            //根据编号创建节点
            CircularNode newnode = new CircularNode(i);
            if (i == 1) { //第一个需要特殊处理
                first = newnode;
                first.next = first;
                cur = first; // 指向第一个节点, first不能动
            } else {
                cur.next = newnode;
                newnode.next = first;
                cur = newnode;
            }
        }
    }

    //遍历环形链表
    //1. 先让一个辅助指针curboy指向first节点
    //2. 然后通过一个while循环遍历该环形链表即可，curboy.next ==first
    public void list() {
        if (first == null) {
            System.out.println("链表为空，没有任何~~");
        }

        CircularNode cur = first; //first 不能动
        while (true) {
            System.out.printf("编号：%d\n", cur.no);
            if (cur.next == first) {
                break;
            }
            cur = cur.next; //后移
        }
    }


    /**
     * 比如5人，每次间隔2人，则出圈的顺序为2->4->1->5->3* 思路：
     * 需要创建一个辅助指针helper，指向环形链表的最后这个节点
     * 当报数时，让first与helper同时移动m-1次
     * 将first指向的节点出圈：first往前移一下first=first.next, 报数前：先让helper位于first前
     * helper.next = first,原来first指向的节点没有任何应用，将被垃圾回收
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countOut(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }

        CircularNode helper = first;
        while (true) {
            if (helper.next == first) { //说明已经到最后
                break;
            }
            helper = helper.next;
        }

        //小孩报数前，先让first和helper移动k-1
        for (int j = 0; j < startNo - 1; j++) {
            first = first.next;
            helper = helper.next;
        }

        //当小孩报数时，让first和helper 指针同时移动m-1，然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (true) {
            if (helper == first) { //说明圈中只有一人
                break;
            }
            for (int j = 0; j < countNum - 1; j++) {
                first = first.next;
                helper = helper.next;
            }
            System.out.printf("小孩%d出圈\n", first.no);
            first = first.next;
            helper.next = first;
        }
        System.out.printf("最后留在圈中的小孩是%d\n", first.no);
    }

}

/**
 * 链表节点(Josephus)
 */
class CircularNode {
    public int no;
    public CircularNode next;

    public CircularNode(int no) {
        this.no = no;
        this.next = null;
    }

    //为了显示方便，重写toString()
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
//                ", next=" + next +
                '}';
    }
}
