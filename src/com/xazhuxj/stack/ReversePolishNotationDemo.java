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
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        // 思路
        // 1. 先将"3 4 + 5 * 6 -" => 放到ArrayList中
        // 2. 将ArrayList传递给一个方法，遍历ArrayList 配合栈完成计算

        ReversePolishNotation rpn = new ReversePolishNotation();

        List<String> rpnList = rpn.getListString(suffixExpression);
        System.out.println("rpnList=" + rpnList);
        int res = rpn.calculateRpnExpression(rpnList);
        System.out.printf("%s = %d", rpnList, res);
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
}
