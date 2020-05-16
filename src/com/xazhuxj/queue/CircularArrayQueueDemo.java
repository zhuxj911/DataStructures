package com.xazhuxj.queue;

import java.util.Scanner;

public class CircularArrayQueueDemo {
    public static void main(String[] args) {
        CircularArrayQueue arrayQueue = new CircularArrayQueue(4); //因为有一个约定空闲，古有效数据为3
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出队列");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0); //接收一个字符
            switch (key) {
                case 's':
                    arrayQueue.list();
                    break;
                case 'a':
                    try {
                        System.out.println("请输入一个数据");
                        int value = scanner.nextInt();
                        arrayQueue.add(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int v = arrayQueue.getQueue();
                        System.out.println("取出的数据为" + v);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int h = arrayQueue.headQueue();
                        System.out.println("队列头数据为" + h);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("~~~程序已经退出~~~");
    }
}


/**
 * 数组模拟环形队列
 * 该设计无法循环利用取走数据留下的空间
 */
class CircularArrayQueue {

    private int maxSize;
    /**
     * 1. front变量的含义做一个调整：front指向队列的第一个元素（原来是指向第一个元素的前边一个位置）
     * front的初始值=0
     */
    private int front;

    /**
     * 2. rear变量的含义做一调整：rear指向队列的最后一个元素的后一个位置（原来是指向后一个元素）。
     * 因为希望空出一个空间作为约定。
     * rear的初始值=
     * 0
     */
    private int rear;  //指向队列尾
    private int[] arr;

    public CircularArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0; //指向队列头的数据
        rear = 0;  //指向队列尾，即队列尾的最后一个数据的后边
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void add(int data) {
        if (isFull()) {
            throw new RuntimeException("队列满，不能添加数据");
        }
        arr[rear] = data; //rear 是指向队尾的数据后边的一个数据
        rear = (rear + 1) % maxSize; //rear后移，这里必须考虑
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
       //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保存到临时变量
        //2.将front后移s
        //3. 将临时变量返回
        int value = arr[front];
        front = (front + 1) % maxSize; //通过取模的方式将数据置到前边，构成循环
        return value;
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[front];
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("队列空，无数据");
            return;
        }

        //思路：从front开始遍历，遍历多少个元素？
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列的有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }
}