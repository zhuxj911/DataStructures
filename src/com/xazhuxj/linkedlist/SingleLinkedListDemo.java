package com.xazhuxj.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // write your code here
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

//        singleLinkedList.add(heroNode1);
//        singleLinkedList.add(heroNode2);
//        singleLinkedList.add(heroNode3);
//        singleLinkedList.add(heroNode4);

        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode2);
//        singleLinkedList.addByOrder(heroNode3);
        System.out.println("~~~~~修改前的的链表情况~~~~~：");
        singleLinkedList.list();

        HeroNode newHeroNode = new HeroNode(2, "小小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("~~~~~修改后的链表情况~~~~~：");
        singleLinkedList.list();
    }
}

//定义SingleLinkedList,真正的单链表
class SingleLinkedList {
    //头结点， 头结点不要动， 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //添加节点到单向链表
    //当不考虑编号顺序时的思路：
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的next 指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助节点temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            if (temp.next == null) break;

            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //退出循环时，temp就是指向链表的最后
        temp.next = heroNode;
    }

    /**
     * 添加英雄时，根据排名将英雄插入到指定位置
     * 如果有这个排名，则添加失败，并给出提示
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        //因为单链表，我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; //flag标志添加的变化是否存在，默认为false
        while (true) {
            if (temp.next == null) break; //说明temp已经在链表的最后
            if (temp.next.no > heroNode.no) { //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) { //说明希望添加的heroNode的编号已然存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next;
        }
        if(flag){ //不能添加，说明编号存在
            System.out.printf("准备插入的英雄编号 %d 已经存在，不能加入\n", heroNode.no);
        }else{
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    /**
     * 修改节点的信息， 根据no编号修改，即no编号不能改
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode){
        if (head.next == null){
            System.out.println("链表为空~~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if(temp == null) break;
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next; //循环后移
        }
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.printf("没有找到编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return head.next == null;
    }

    //显示链表
    public void list() {
        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;
        while (true) {
            if (temp == null) break;
            System.out.println(temp);

            temp = temp.next;//后移，否则死循环
        }
    }
}

/**
 * 链表节点
 */
class HeroNode {
    //data field
    public int no;
    public String name;
    public String nickname;

    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;

        this.next = null;
    }

    //为了显示方便，重写toString()
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
//                ", next=" + next +
                '}';
    }
}
