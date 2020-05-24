package com.xazhuxj.tree;


/**
 * 二叉树演示
 *    1
 *  /   \
 *  2     3
 *   \   / \
 *    7 5   4
 *         /
 *        6
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义");
        HeroNode heroNode3 = new HeroNode(3, "吴用");
        HeroNode heroNode4 = new HeroNode(4, "公孙胜");
        HeroNode heroNode5 = new HeroNode(5, "关胜");
        HeroNode heroNode6 = new HeroNode(6, "林冲");
        HeroNode heroNode7 = new HeroNode(7, "秦明");

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);
        root.setLeft(heroNode2);
        root.setRight(heroNode3);

        heroNode2.setRight(heroNode7);
        heroNode3.setLeft(heroNode5);
        heroNode3.setRight(heroNode4);
        heroNode4.setLeft(heroNode6);

        System.out.println("前序遍历");
        binaryTree.preOrder(); //1 2 7 3 5 4 6

        System.out.println("中序遍历");
        binaryTree.infixOrder(); //2 7 1 5 3 6 4

        System.out.println("后序遍历");
        binaryTree.postOrder(); //7 2 5 6 4 3 1

//        System.out.println("前序遍历查找");
//        HeroNode resNode = binaryTree.preOrderSearch(5); //第5次找到
//        if(resNode != null) {
//            System.out.println("前序找到了,信息为" + resNode);
//        }else
//        {
//            System.out.printf("前序没有找到 %d 号英雄\n", 5);
//        }

//        System.out.println("中序遍历查找");
//        HeroNode resNode = binaryTree.infixOrderSearch(5); //第4次找到
//        if(resNode != null) {
//            System.out.println("中序找到了,信息为" + resNode);
//        }else
//        {
//            System.out.printf("中序没有找到 %d 号英雄\n", 5);
//        }

        System.out.println("后序遍历查找");
        HeroNode resNode = binaryTree.postOrderSearch(5); //第3次找到
        if(resNode != null) {
            System.out.println("后序找到了,信息为" + resNode);
        }else
        {
            System.out.printf("后序没有找到 %d 号英雄\n", 5);
        }

        binaryTree.delNode(5);
        binaryTree.preOrder();
    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    public  HeroNode preOrderSearch(int no){
        if( root != null){
            return  root.preOrderSearch(no);
        }
        else {
            return null;
        }
    }

    public  HeroNode infixOrderSearch(int no){
        if( root != null){
            return  root.infixOrderSearch(no);
        }
        else {
            return null;
        }
    }

    public  HeroNode postOrderSearch(int no){
        if( root != null){
            return  root.postOrderSearch(no);
        }
        else {
            return null;
        }
    }

    //删除结点,目前: 叶子结点与非叶子均直接删除
    //由于树为单向树,所以删除时,需要在父结点进行删除
    //所以删除时将删除结点作为左右子结点尽心判断删除
    public  void delNode(int no){
        root.DelNode(no);
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

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

    @java.lang.Override
    public java.lang.String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this); //先输出父结点

        if (this.left != null) { //递归向左子树前序遍历
            this.left.preOrder();
        }

        if (this.right != null) { //递归向左子树前序遍历
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) { //递归向左子树前序遍历
            this.left.infixOrder();
        }

        System.out.println(this); //输出父结点

        if (this.right != null) { //递归向左子树前序遍历
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.left != null) { //递归向左子树前序遍历
            this.left.postOrder();
        }

        if (this.right != null) { //递归向左子树前序遍历
            this.right.postOrder();
        }

        System.out.println(this); //输出父结点
    }

    /**
     * 前序遍历查找
     *
     * @param no
     * @return
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历~~");
        if (this.no == no) {
            return this;
        }

        //1. 判断当前结点的左子结点是否为空,如果不为空,则递归前序查找
        //2. 如果左递归前序查找找到结点,则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//说明找到
            return resNode;
        }

        //1. 判断当前结点的右子结点是否为空,如果不为空,则递归前序查找
        //2. 如果右递归前序查找找到结点,则返回
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }

        return resNode;
    }

    /**
     * 中序遍历查找
     *
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no) {
        //1. 判断当前结点的左子结点是否为空,如果不为空,则递归前序查找
        //2. 如果左递归前序查找找到结点,则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {//说明找到
            return resNode;
        }

        System.out.println("进入中序遍历~~");
        if (this.no == no) {
            return this;
        }

        //1. 判断当前结点的右子结点是否为空,如果不为空,则递归中序查找
        //2. 如果右递归中序查找找到结点,则返回
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }

        return resNode;
    }

    /**
     * 后序遍历查找
     *
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {
        //1. 判断当前结点的左子结点是否为空,如果不为空,则递归后序查找
        //2. 如果左递归后序查找找到结点,则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {//说明找到
            return resNode;
        }

        //1. 判断当前结点的右子结点是否为空,如果不为空,则递归前序查找
        //2. 如果右递归前序查找找到结点,则返回
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {//说明找到
            return resNode;
        }

        System.out.println("进入后序遍历~~");
        if (this.no == no) {
            return this;
        }

        return resNode;
    }

    //删除结点,目前: 叶子结点与非叶子均直接删除
    //由于树为单向树,所以删除时,需要在父结点进行删除
    //所以删除时将删除结点作为左右子结点尽心判断删除
    public void DelNode(int no){
        if(this.left != null && this.left.no == no){
            this.left = null;
            return;
        }

        if(this.right != null && this.right.no == no){
            this.right = null;
            return;
        }

        if(this.left != null){
            this.left.DelNode(no);
        }

        if(this.right != null){
            this.right.DelNode(no);
        }
    }
}