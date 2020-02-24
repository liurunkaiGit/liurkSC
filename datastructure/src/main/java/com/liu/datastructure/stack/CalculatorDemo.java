package com.liu.datastructure.stack;

/**
 * @Description: 栈实现综合计算器
 * @author: liurunkai
 * @Date: 2020/2/14 11:02
 */
public class CalculatorDemo {
    public static void main(String[] args) {
        String expression = "3+62/2*6-2";
        Calculator numStack = new Calculator(expression.getBytes().length);
        Calculator opreStack = new Calculator(expression.getBytes().length);
        for (int i = 0; i < expression.getBytes().length; i++) {
            char c = expression.substring(i, i + 1).charAt(0);
            if (opreStack.isOpre(c)) {
                // 如果是操作符，判断操作符栈是否为空
                if(opreStack.isEmpty()) {
                    opreStack.push(c);
                } else {
                    // 判断当前符号的优先级是否小于或者等于栈顶的符号，则取出数栈的两个数和符号栈中的一个符号进行运算
                    if(opreStack.priority(c) <= opreStack.priority(opreStack.peek())) {
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        int opre = opreStack.pop();
                        int result = opreStack.calculator(num1, num2, opre);
                        numStack.push(result);
                        opreStack.push(c);
                    } else {
                        // 判断当前符号的优先级是否大于栈顶的符号，直接入符号栈
                        opreStack.push(c);
                    }
                }
            } else {
                // 这行代码只能处理1位数的加减乘除运算，如何处理多位数的加减乘除
//                numStack.push(c - 48); // c 取到的是'1'需要转换成1，'1'和1相差48，所以需要减掉48
                // 处理多位数，不能发现是一个数就直接入数栈，因为可能是多位数
                // 在处理数时，需要向expression表达式的后面一位看一下是否是数字
                String kNum = ""; //用于拼接多位数
                kNum += c;
                if((i + 1) == expression.getBytes().length) {
                    numStack.push(Integer.valueOf(kNum));
                }else {
                    for (int j = i+1; j < expression.getBytes().length; j++) {
                        char c2 = expression.substring(j, j + 1).charAt(0);
                        if(numStack.isOpre(c2)) {
                            // 如果后一位是符号，则直接入数栈
                            numStack.push(Integer.valueOf(kNum));
                            break;
                        } else {
                            kNum+=c2;
                            i++;
//                        numStack.push(Integer.valueOf(kNum));
                        }
                    }
                }
            }
        }
        // 当对表达式处理完成后，需要将数栈的数取出来和符号栈的符号取出来进行计算，当符号栈为空时即数栈只有一个值，就是最后的结果
        while (true) {
            if (opreStack.isEmpty()) {
                break;
            }
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            int opre = opreStack.pop();
            int calculator = opreStack.calculator(num1, num2, opre);
            numStack.push(calculator);
        }
        System.out.println("结果是="+numStack.pop());
    }
}

class Calculator {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public Calculator(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top + 1 == maxSize;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if(!isFull()) {
            top++;
            stack[top] = value;
        } else {
            System.out.println("栈满");
        }
    }

    // 查看栈顶的元素，但是不取出来
    public int peek() {
        return stack[top];
    }

    public int pop() {
        if(!isEmpty()) {
            int value = stack[top];
            top--;
            return value;
        } else {
            System.out.println("占空");
            throw new RuntimeException("栈空");
        }
    }

    public void list() {
        if(!isEmpty()) {
            for (int i = top; i >= 0; i--) {
                System.out.println(stack[i]);
            }
        } else {
            System.out.println("栈空");
        }
    }

    // 判断是数字还是符号，char和int可以混用，也可以写成int c
    public Boolean isOpre(char c) {
        return c == '+' || c == '-' || c == '/' || c == '*';
    }
    // 返回运算符的优先级，char和int可以混用，也可以写成char opre
    public int priority(int opre) {
        if(opre == '*' || opre == '/') {
            return 1;
        } else if(opre == '+' || opre == '-'){
            return 0;
        } else {
            return -1;
        }
    }
    //计算两个数的结果，char和int可以混用，也可以写成char opre
    public int calculator(int num1,int num2,int opre) {
//        if(opre == '*') {
//            return num2 * num1;
//        } else if (opre == '/') {
//            return num2 / num1;
//        } else if (opre == '+') {
//            return num2 + num1;
//        } else if (opre == '-') {
//            return num2 - num1;
//        } else {
//            return -1;
//        }
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