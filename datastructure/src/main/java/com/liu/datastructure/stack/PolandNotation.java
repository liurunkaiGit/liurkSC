package com.liu.datastructure.stack;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 中缀表达式转后缀表达式及逆波兰表达式（后缀表达式）实现计算求值过程
 * @author: liurunkai
 * @Date: 2020/2/14 15:52
 */
public class PolandNotation {
    public static void main(String[] args) {
//        String suffixExpression = "3 4 + 5 * 6 -";
        // 中缀表达式转后缀表达式
        String str = "1 + ( ( 2 + 3 ) * 4 ) - 5";
//        String str = "( 3 + 4 ) * 5 - 6";
        String suffixExpression = revert(str);
        System.out.println("后缀表达式" + suffixExpression);
        String[] poland = suffixExpression.split(" ");
        /**
         * （1）从左到右扫描，将3和4压入堆栈
         * （2）遇到+运算符时，因此弹出4和3(4为栈顶元素、3为次顶元素)，计算3+4的值得7，再将7入栈
         * （3）将5入栈
         * （4）接下来是*运算符，因此弹出5和7，计算7*5=35，再将35入栈
         * （5）最后是-运算符，计算出35-6=29，由此得出最终结果
         */
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < poland.length; i++) {
            if(!poland[i].matches("\\d+")) { // poland[i].matches("\\d+") 匹配的是多位数
                String num1 = stack.pop();
                String num2 = stack.pop();
                int calculator = calculator(Integer.valueOf(num1), Integer.valueOf(num2), poland[i].charAt(0));
                stack.push(String.valueOf(calculator));
            }else {
                stack.push(poland[i]);
            }
        }
        System.out.println("结果为=" + stack.pop());
    }

    /**
     * 中缀表达式转后缀表达式
     * 1.初始化两个栈：运算符栈s1和存储中间结果的栈s2
     * 2.从左到右扫描中缀表达式
     * 3.遇到操作数时，将其压入s2
     * 4.遇到运算符时，比较其与s1栈顶运算符的优先级
     * (1)如果s1为空，或者栈顶运算符为左括号“(”，则直接将此运算符入栈
     * (2)如果优先级比栈顶运算符的优先级高，也将其压入栈s1
     * (3)否则，将s1栈顶的运算符弹出并压入s2中，然后再与现在的s1栈顶的运算符比较优先级，重复4-1的操作
     * 5.遇到括号时
     * (1)遇到左括号时，直接压入s1
     * (2)如果是右括号，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6.重复步骤2-5，直到表达式最右端
     * 7.将s1中剩余的运算符依次弹出并压入s2
     * 依次弹出s2中的元素并输出，结果的逆序即为中缀转后缀表达式的结果
     * @param str
     * @return
     */
    private static String revert(String str) { //( 3 + 4 ) * 5 - 6
        Stack<String> s1 = new Stack<>();//符号栈
        Stack<String> s2 = new Stack<>();// 中间栈
        String[] s = str.split(" ");
        for (String s3 : s) {
            if(s3.matches("\\d+")) { //匹配多位数
                s2.push(s3);
            } else {
                if(s3.equals("(") || s1.empty()) {
                    s1.push(s3);
                } else if(s3.equals(")")) {
                    while (true) {
                        if(s1.peek().equals("(")) {
                            s1.pop();
                            break;
                        } else {
                            s2.push(s1.pop());
                        }
                    }
                } else { //运算符
                    if(priority(s3) > priority(s1.peek())) {
                        s1.push(s3);
                    } else {
                        while (true) {
                            if (s1.empty() || s1.peek().equals("(") || priority(s3) > priority(s1.peek())) {
                                s1.push(s3);
                                break;
                            } else {
                                s2.push(s1.pop());
                            }
                        }
                    }
                }
            }
        }
        while (!s1.empty()) {
            s2.push(s1.pop());
        }
        List<String> strings = new ArrayList<>();
        while (!s2.empty()) {
            strings.add(s2.pop());
        }
        String str2 = "";
        for (int i = strings.size() - 1; i >= 0; i--) {
            if (i == 0) {
                str2 += strings.get(i);
            } else {
                str2 += strings.get(i) + " ";
            }
        }
        return str2;
    }

    // 返回运算符的优先级，char和int可以混用，也可以写成char opre
    public static int priority(String o) {
        char opre = o.charAt(0);
        if(opre == '*' || opre == '/') {
            return 1;
        } else if(opre == '+' || opre == '-'){
            return 0;
        } else {
            return -1;
        }
    }

    // 判断是数字还是符号，char和int可以混用，也可以写成int c
    public static Boolean isOpre(String c) {
        char c1 = c.charAt(0);
        return c1 == '+' || c1 == '-' || c1 == '/' || c1 == '*';
    }

    //计算两个数的结果，char和int可以混用，也可以写成char opre
    public static int calculator(int num1,int num2,char opre) {
        int res = 0;
        switch (opre) {
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }

}
