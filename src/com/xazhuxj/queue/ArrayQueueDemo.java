package com.xazhuxj.queue;

import javax.security.sasl.RealmCallback;
import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
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
 * 数组模拟队列
 * 该设计无法循环利用取走数据留下的空间
 */
class ArrayQueue {

    private int maxSize;
    private int front; //指向队列头
    private int rear;  //指向队列尾
    private int[] arr;

    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; //指向队列头的前一个位置
        rear = -1;  //指向队列尾，即队列尾的最后一个数据
    }

    public boolean isFull(){
        return rear == maxSize -1;
    }

    public  boolean isEmpty(){
        return front == rear;
    }

    public  void add(int data){
        if( isFull() ){
            throw new RuntimeException("队列满，不能添加数据");
        }
        rear++;
        arr[rear] = data;
    }

    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];
    }

    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[front+1];
    }

    public  void list(){
        if(isEmpty()){
            System.out.println("队列空，无数据");
            return;
        }

        for (int i = 0; i <arr.length ; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }
}