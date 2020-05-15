package com.xazhuxj.stack;

/**
 * 栈的应用：计算器
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "7+2*6-4";
        int result = ArrayStack2.calExpression(expression);

        System.out.printf("表达式 %s = %d", expression, result);
    }
}

/**
 * 数组模拟栈
 *
 * @author xazhu
 *
 */
class ArrayStack2 {
    private int top = -1;
    private int[] stack;
    private int maxSize;

    public ArrayStack2(int maxSize) {
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
            throw new RuntimeException("栈为空，无数据");
        }

        int value = stack[top];
        stack[top] = 0; // 将数据擦除，以免混淆
        top--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无数据");
        }
        return stack[top];
    }

    public void list() {
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 返回运算符的优先级，优先级由程序员来确定，使用数字来表示 数字越大，则优先级就越高
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; // 注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;// 注意顺序
                break;
            default:
                break;
        }
        return res;
    }

    /**
     * 第一版的表达式计算 存在问题：只能进行当个数字的size运算
     *
     * @param expression
     * @return
     */
    public static int calExpression(String expression) {
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index = 0; // 用于扫描表达式
        int num1, num2;
        int oper = 0, res = 0;
        char ch = ' ';// 将每次扫描得到的char保存到ch
        while (true) {
            // 以此得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)) { // 如果是运算符
                // 判断当前的符号是否为空
                if (operStack.isEmpty()) {
                    // 若果符号栈为空，直接入栈
                    operStack.push(ch);
                } else {
                    // 如果符号栈有操作符，就进行比较
                    // 如果当前的操作符的优先级小于或等于占中的操作符，就熊数栈中pop出两个数
                    // 再从符号栈中pop出一个符号，进行运算，将得到的结果，入数栈，然后将当前的操作符如符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        // 如果当前的操作符优先级大于占中的操作符，直接入栈
                        operStack.push(ch);
                    }
                }
            } else {
                // 如果是数，直接如数栈
                numStack.push(ch - 48); // 需要存入数字的相应的ascii码值
            }
            // 让index+1，并判断是否扫描到exprssion最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        // 当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并计算
        while (true) {
            // 如果符号栈为空，则计算到最后的结果
            if (operStack.isEmpty())
                break;
            else {
                num1 = numStack.pop();
                num2 = numStack.pop();
                oper = operStack.pop();
                res = numStack.cal(num1, num2, oper);
                numStack.push(res);
            }
        }
        return numStack.pop();
    }
}
