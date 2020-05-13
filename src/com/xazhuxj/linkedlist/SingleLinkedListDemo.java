package com.xazhuxj.linkedlist;
import java.util.Stack;

/**
 * 使用head头的双向链表实现-水浒英雄排行榜
 * 单向链表的缺点分析：
 * 1） 查找的方向只能是一个方向
 * 2）不能自我删除，需要辅助节点，所以删除节点时， 总是找到temp（temp是待删除节点的前一个节点）的下一个节点来删除的
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // write your code here
        HeroNode heroNode1 = new HeroNode(1, "宋江", "天魁星·及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "天罡星·玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "天机星·智多星");
        HeroNode heroNode6 = new HeroNode(6, "林冲", "天雄星·豹子头");
        HeroNode heroNode4 = new HeroNode(4, "公孙胜", "天闲星·入云龙");
        HeroNode heroNode5 = new HeroNode(5, "关胜", "天勇星·大刀");
        HeroNode heroNode7 = new HeroNode(7, "秦明", "天猛星·霹雳火");
        HeroNode heroNode8 = new HeroNode(8, "呼延灼", "天威星·双鞭将");
        HeroNode heroNode9 = new HeroNode(9, "花荣", "天英星·小李广");
        HeroNode heroNode10 = new HeroNode(10, "柴进", "天贵星·小旋风");
        HeroNode heroNode11 = new HeroNode(11, "李应", "天富星·扑天雕");
        HeroNode heroNode12 = new HeroNode(12, "朱仝", "天满星·美髯公");
        HeroNode heroNode14 = new HeroNode(14, "武松", "天伤星·行者");
        HeroNode heroNode17 = new HeroNode(17, "杨志", "天暗星·青面兽");
        HeroNode heroNode22 = new HeroNode(22, "李逵", "天杀星·黑旋风");
        HeroNode heroNode20 = new HeroNode(20, "戴宗", "天速星·神行太保");
        HeroNode heroNode13 = new HeroNode(13, "鲁智深", "天孤星·花和尚");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

//        singleLinkedList.add(heroNode1);
//        singleLinkedList.add(heroNode2);
//        singleLinkedList.add(heroNode3);
//        singleLinkedList.add(heroNode4);

        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode6);
        singleLinkedList.addByOrder(heroNode14);
        singleLinkedList.addByOrder(heroNode8);
        singleLinkedList.addByOrder(heroNode17);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode22);
        singleLinkedList.addByOrder(heroNode20);
        singleLinkedList.addByOrder(heroNode13);

        SingleLinkedList otherSingleLinkedList = new SingleLinkedList();
        otherSingleLinkedList.addByOrder(heroNode12);
        otherSingleLinkedList.addByOrder(heroNode4);
        otherSingleLinkedList.addByOrder(heroNode5);
        otherSingleLinkedList.addByOrder(heroNode10);
        otherSingleLinkedList.addByOrder(heroNode7);
        otherSingleLinkedList.addByOrder(heroNode2);
        otherSingleLinkedList.addByOrder(heroNode9);
        otherSingleLinkedList.addByOrder(heroNode11);

        System.out.println("~~~~~合并前的链表情况~~~~~：");
        System.out.println("~~~~~链表1：~~~~~：");
        singleLinkedList.list();
        System.out.println("~~~~~链表2：~~~~~：");
        otherSingleLinkedList.list();
        System.out.println("~~~~~合并后的链表：~~~~~：");
        SingleLinkedList mergeSingleLinkedList;
        mergeSingleLinkedList = singleLinkedList.mergeOrderSingleLinkedList(otherSingleLinkedList);
        mergeSingleLinkedList.list();

//        singleLinkedList.addByOrder(heroNode3);
//        System.out.println("~~~~~修改前的的链表情况~~~~~：");
//        singleLinkedList.list();

//        HeroNode newHeroNode = new HeroNode(2, "小小卢", "玉麒麟~~");
//        singleLinkedList.update(newHeroNode);
//        System.out.println("~~~~~修改后的链表情况~~~~~：");
//        singleLinkedList.list();
//
//        //删除一个节点
//        singleLinkedList.del(1);
//        singleLinkedList.del(4);
//        System.out.println("~~~~~删除一个节点后的链表情况~~~~~：");
//        singleLinkedList.list();
//
//        System.out.printf("~~~~~有效的节点个数:%d~~~~~：\n", singleLinkedList.size());
//
//        System.out.println("~~~~~倒数第1个元素是：~~~~~：");
//        HeroNode res = singleLinkedList.findlastIndexNode(1);
//        System.out.println(res);
//
//        System.out.println("~~~~~倒数第2个元素是：~~~~~：");
//        res = singleLinkedList.findlastIndexNode(2);
//        System.out.println(res);
//
//        System.out.println("~~~~~倒数第3个元素是：~~~~~：");
//        res = singleLinkedList.findlastIndexNode(3);
//        System.out.println(res);

//        System.out.println("~~~~~reverse前的链表情况~~~~~：");
//        singleLinkedList.list();
//        System.out.println("~~~~~reverse后的链表情况~~~~~：");
//        singleLinkedList.reverse();
//        singleLinkedList.list();
//
//        System.out.println("~~~~~逆序打印效果~~~~~：");
//        singleLinkedList.reversePrint();
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
     * 删除节点
     *从单链表中删除一个节点的 思路
     *
     * 1. 我们先找到需要删除的这个节点的前一个节点temp（指向待删除节点的前一个节点）
     * 2. head 不能动，因此需要一个temp辅助节点找到节点前一个节点
     */
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null) { //已经到链表的最后
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next; //后一，遍历
        }

        if(flag) {
            temp.next = temp.next.next;
        }else{
            System.out.println("要删除的节点不存在！");
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

    public int size(){
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next; //遍历
        }
        return length;
    }


    /**
     * 查找单链表中的倒数第k个节点【新浪面试题】
     * 思路：
     * 1. 编写一个方法，接收head节点， 同时接收一个index
     * 2. index 表示是倒数第index个节点
     * 3. 先把链表从头到尾遍历，得到链表的总的长度 size()
     * 4. 得到size后，我们从链表的第一个开始遍历（size - index）个，就可以得到
     * 5. 如果找到了，则返回该节点，否则返回null
     */
    public HeroNode findlastIndexNode(int index){
        if(head.next == null) return null;

        int sz = size();
        if(index <=0 || index > sz) return null;

        //定义给辅助变量，for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i=0; i<sz-index; i++){
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 翻转链表
     * 思路：
     * 1. 西安定义一个节点reversehead = new HeroNode()
     * 2. 从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
     * 3. 原来的链表的head.next = reverseHead.next
     */
    public void reverse(){
        if(head.next == null || head.next.next == null) return;

        HeroNode cur = head.next; //定义辅助的指针，帮助我们遍历原来的链表
        HeroNode next = null; //指向纺前街店的下一个节点
        HeroNode reverseHead = new HeroNode(0,"", "");
        while(cur != null){
            next = cur.next; //先暂时保存当前节点的下一个节点，因为后面要使用
            cur.next = reverseHead.next; //将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;
            cur = next; //让cur后移
        }
        //将head.next指向reverseHead.next
        head.next = reverseHead.next;
    }

    /**
     * 逆序打印
     * 思路：
     * 1. 方式一：先将单链表进行翻转，然后在遍历即可，这样的问题是会剖爱原来的单链表的结构，不建议
     * 2. 方式二：可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
     */
    public void reversePrint(){
        if(head.next == null) return;

        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    /**
     * 合并两个有序链表，合并之后的链表依然有序
     * 思路同前：
     * 新生成以一个mergeHead，然后从两个链表中摘取数据进行比较，然后插入
     * @param singleLinkedList
     * @return
     */
    public SingleLinkedList mergeOrderSingleLinkedList(SingleLinkedList singleOrderLinkedList){
        //TODO
        return null;
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
