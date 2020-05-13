package com.xazhuxj.linkedlist;

/**
 * 使用head头的双向链表实现-水浒英雄排行榜
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode2 heroNode1  = new HeroNode2(1, "宋江", "天魁星·及时雨");
        HeroNode2 heroNode2  = new HeroNode2(2, "卢俊义", "天罡星·玉麒麟");
        HeroNode2 heroNode3  = new HeroNode2(3, "吴用", "天机星·智多星");
        HeroNode2 heroNode4  = new HeroNode2(4, "公孙胜", "天闲星·入云龙");
        HeroNode2 heroNode5  = new HeroNode2(5, "关胜", "天勇星·大刀");
        HeroNode2 heroNode6  = new HeroNode2(6, "林冲", "天雄星·豹子头");
        HeroNode2 heroNode7  = new HeroNode2(7, "秦明", "天猛星·霹雳火");
        HeroNode2 heroNode8  = new HeroNode2(8, "呼延灼", "天威星·双鞭将");
        HeroNode2 heroNode9  = new HeroNode2(9, "花荣", "天英星·小李广");
        HeroNode2 heroNode10 = new HeroNode2(10, "柴进", "天贵星·小旋风");
        HeroNode2 heroNode11 = new HeroNode2(11, "李应", "天富星·扑天雕");
        HeroNode2 heroNode12 = new HeroNode2(12, "朱仝", "天满星·美髯公");
        HeroNode2 heroNode13 = new HeroNode2(13, "鲁智深", "天孤星·花和尚");
        HeroNode2 heroNode14 = new HeroNode2(14, "武松", "天伤星·行者");
        HeroNode2 heroNode17 = new HeroNode2(17, "杨志", "天暗星·青面兽");
        HeroNode2 heroNode20 = new HeroNode2(20, "戴宗", "天速星·神行太保");
        HeroNode2 heroNode22 = new HeroNode2(22, "李逵", "天杀星·黑旋风");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

//        doubleLinkedList.add(heroNode1);
//        doubleLinkedList.add(heroNode6);
//        doubleLinkedList.add(heroNode14);
//        doubleLinkedList.add(heroNode8);
//        doubleLinkedList.add(heroNode17);
//        doubleLinkedList.add(heroNode3);
//        doubleLinkedList.add(heroNode20);
//        doubleLinkedList.add(heroNode13);
//        doubleLinkedList.add(heroNode5);
//        doubleLinkedList.add(heroNode11);
//        doubleLinkedList.add(heroNode22);
//        doubleLinkedList.add(heroNode12);
//        doubleLinkedList.add(heroNode4);
//        doubleLinkedList.add(heroNode10);
//        doubleLinkedList.add(heroNode7);
//        doubleLinkedList.add(heroNode2);
//        doubleLinkedList.add(heroNode9);
        doubleLinkedList.addByOrder(heroNode1);
        doubleLinkedList.addByOrder(heroNode6);
        doubleLinkedList.addByOrder(heroNode14);
        doubleLinkedList.addByOrder(heroNode8);
        doubleLinkedList.addByOrder(heroNode17);
        doubleLinkedList.addByOrder(heroNode3);
        doubleLinkedList.addByOrder(heroNode20);
        doubleLinkedList.addByOrder(heroNode13);
        doubleLinkedList.addByOrder(heroNode5);
        doubleLinkedList.addByOrder(heroNode11);
        doubleLinkedList.addByOrder(heroNode22);
        doubleLinkedList.addByOrder(heroNode12);
        doubleLinkedList.addByOrder(heroNode4);
        doubleLinkedList.addByOrder(heroNode10);
        doubleLinkedList.addByOrder(heroNode7);
        doubleLinkedList.addByOrder(heroNode2);
        doubleLinkedList.addByOrder(heroNode9);

        System.out.println("~~~~~双向链表内容：~~~~~：");
        doubleLinkedList.list();

        HeroNode2 newHeroNode = new HeroNode2(2, "小小卢", "玉麒麟~~");
        doubleLinkedList.update(newHeroNode);
        System.out.println("~~~~~修改后的链表情况~~~~~：");
        doubleLinkedList.list();

        //删除一个节点
        doubleLinkedList.del(2);
        System.out.println("~~~~~删除一个节点后的链表情况~~~~~：");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList{
    //头结点， 头结点不要动， 不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");
    public HeroNode2 getHead() {
        return head;
    }
    //显示链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) break;
            System.out.println(temp);

            temp = temp.next;//后移，否则死循环
        }
    }

    //添加节点到双向链表
    //当不考虑编号顺序时的思路：
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的next 指向新的节点
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此我们需要一个辅助节点temp，而且只能从head开始
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            if (temp.next == null) break;

            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //退出循环时，temp就是指向链表的最后
        temp.next = heroNode;
        heroNode.pre = temp; //形成双向链表
    }

    /**
     * 作业：TODO->DONE
     * 添加英雄时，根据排名将英雄插入到指定位置
     * 如果有这个排名，则添加失败，并给出提示     *
     * @param heroNode
     */
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head; //添加只能从head开始
        boolean flag = false; //flag标志添加的变化是否存在，默认为false
        while (temp.next != null) {
            if (temp.next.no > heroNode.no) { //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) { //说明希望添加的heroNode的编号已然存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next;
        }
        if (flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄编号 %d 已经存在，不能加入\n", heroNode.no);
        } else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            heroNode.pre = temp;
            temp.next = heroNode;
        }

    }

    /**
     * 修改节点的信息， 根据no编号修改，即no编号不能改
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) break;
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next; //循环后移
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    /**
     * 双向链表删除节点
     * 思路：
     * 我们可以直接找到需要删除的这个节点，进行自我删除
     */
    public void del(int no) {
        if (head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }

        HeroNode2 temp = head.next; //辅助节点,初始化为第一个节点，注意与单向节点的不同
        boolean flag = false;
        while (true) {
            if (temp == null) { //已经到链表的最后
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next; //后一，遍历
        }

        if (flag) {
//            temp.next = temp.next.next; //单向链表的删除方式
            temp.pre.next = temp.next;
            //这里下面的这句话有问题的，如果是最后一个元素，就不需要执行下面这句，否则会出现空指针异常
            if(temp.next != null) temp.next.pre = temp.pre;
        } else {
            System.out.println("要删除的节点不存在！");
        }

    }

}

/**
 * 双向链表节点
 */
class HeroNode2 {
    //data field
    public int no;
    public String name;
    public String nickname;

    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}