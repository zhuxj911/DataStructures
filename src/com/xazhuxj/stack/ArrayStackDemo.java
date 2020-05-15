package com.xazhuxj.stack;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; // 是否退出菜单
        Scanner scanner = new Scanner(System.in);
        
        while (loop) {
            System.out.println("show: 显示栈");
            System.out.println("exit: 退出栈");
            System.out.println("push: 入栈");
            System.out.println("pop: 出栈");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    try {
                        System.out.println("请输入一个数");
                        int value = scanner.nextInt();
                        stack.push(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "pop":
                    try {
                        int result = stack.pop();
                        System.out.printf("\n出栈的数据是%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
        System.out.println("~~~~~已退出~~~~~");
    }

}

/**
 * 数组模拟栈
 * @author xazhu
 *
 */
class ArrayStack {
    private int top = -1;
    private int[] stack;
    private int maxSize;

    public ArrayStack(int maxSize) {
        super();
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈已满，无法加入数据");
        }

        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空，无数据");
        }

        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

}