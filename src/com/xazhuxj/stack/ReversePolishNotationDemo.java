package com.xazhuxj.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolishNotationDemo {

    public static void main(String[] args) {
        // 先定义逆波兰表达式 （3+4）*5-6 => 3 4 + 5 * 6 -
        // 为了简化，逆波兰表达式中的数字与符号用空格隔开
        // 4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / + =76
//		String suffixExpression = "3 4 + 5 * 6 -";
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
//        // 思路
//        // 1. 先将"3 4 + 5 * 6 -" => 放到ArrayList中
//        // 2. 将ArrayList传递给一个方法，遍历ArrayList 配合栈完成计算
//

//
//        List<String> rpnList = rpn.getListString(suffixExpression);
//        System.out.println("rpnList=" + rpnList);
//        int res = rpn.calculateRpnExpression(rpnList);
//        System.out.printf("%s = %d", rpnList, res);

        String infixExpression = "1+((2+3)*4)-5"; // =》 1 2 3 + 4 * + 5 -
        ReversePolishNotation rpn = new ReversePolishNotation();
        List<String> infixExpressList = rpn.toInfixExpressionList(infixExpression);
        System.out.println(infixExpressList);

        List<String> suffixExpressionList;
        System.out.println("后缀表达式对应的为：");
        try {
            suffixExpressionList = rpn.parseSuffixExpressionList(infixExpressList);
            System.out.println(suffixExpressionList);
            System.out.printf("%s = %d", infixExpression, rpn.calculateRpnExpression(suffixExpressionList));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}

class ReversePolishNotation {

    public List<String> getListString(String suffixExpression) {
        String[] splitStrings = suffixExpression.split(" ");
        java.util.List<String> list = new ArrayList<>();
        for (String ele : splitStrings) {
            list.add(ele);
        }
        return list;
    }

    /**
     * 完成对逆波兰表达式的运算 思路： 1. 从左到右扫描，将3和4压入栈 2.
     * 遇到+，弹出4和3（4为栈顶元素，3为次栈顶元素），计算出3+4，得7，再将7入栈 3. 将5入栈 4.
     * 接下来是×，弹出5和7，计算出7×5=35，将35入栈 5. 将6入栈 6. 最后-运算， 计算出35-6，得29，最终成果
     *
     * @return
     */
    public int calculateRpnExpression(List<String> rpnList) {
        Stack<String> stack = new Stack<>();
        for (String item : rpnList) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                switch (item) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2; // 注意顺序
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;// 注意顺序
                        break;
                    default:
                        throw new RuntimeException("运算符不支持");
                }
                stack.push(String.valueOf(result));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        int i = 0;
        char c;
        do {
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) { //非数字
                ls.add("" + c);
                i++;
            } else { //是数字，需考虑多位数问题
                String str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    /**
     * 中缀表达式转后缀表达式的思路步骤分析：
     * 1. 初始化两个栈：运算符栈s1和存储中间结果的栈s2
     * 2. 从左到右扫描中缀表达式
     * 3. 遇到数时，将其压入s2
     * 4. 遇到运算符时，比较其与s1栈顶运算符的优先级
     * 4.1 如果s1为空，或栈顶运算符为左括号“(”， 则直接将此运算符入栈
     * 4.2 否则，若优先级比栈顶运算符的高，也将运算符入栈s1
     * 4.3 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到4.1与s1中新的栈顶运算符相比较
     * 5. 遇到括号时：
     * 5.1 如果是左括号“（”， 则直接压入s1
     * 5.2 如果是右括号“）”， 则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6. 重复步骤2至5，直到表达式的最右边
     * 7. 将s1中剩余的运算符依次弹出并压入s2
     * 8. 依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     */
    public List<String> parseSuffixExpressionList(List<String> ls) {
        Stack<String> s1 = new Stack<>();
        //说明：因为s2这个栈，在整个转换过程中，没有pop操作，而且后面还要逆序输出
        //因此比较麻烦，这里我们就不用Stack<String>,直接使用List<String> s2
        List<String> s2 = new ArrayList<>();
        for (String item : ls) {
            if (item.matches("\\d+")) { //3. 遇到数时，将其压入s2
                s2.add(item);
            } else if (item.equals("(")) {
                //5.1 如果是左括号“（”， 则直接压入s1
                s1.push(item);
            } else if (item.equals(")")) {
                //5.2 如果是右括号“)”， 则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); //!!!将 ( 弹出，消除小括号
            } else { //已经是运算符的情况了
               //4.1 如果s1为空，或栈顶运算符为左括号“(”， 则直接将此运算符入栈(while循环中的第1、2个条件)
                //当item的优先级小于等于栈顶运算符，将s1栈顶的运算符弹出并压入到s2中，再次转到4.1与s1中新的栈顶运算符相比较
                while (s1.size() != 0 && !s1.peek().equals("(") && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //7. 将s1中剩余的运算符依次弹出并压入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2; //因为是List，所以正常输出，就是逆波兰表达式（后缀表达式）
    }
}

//编写一个类Operation，可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;

            case "-":
                result = SUB;
                break;

            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                throw new RuntimeException("不支持的操作符");
        }
        return result;
    }
}
