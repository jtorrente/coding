package es.jtorrente.exercises.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Created by jtorrente on 16/07/2015.
 */
public class Calculator {
    private static final int PLUS = -1;
    private static final int MINUS = -2;
    private static final int MULT = -3;
    private static final int DIV = -4;

    // 10 + 1 * 3
    public int calculate(String s) {
        int operand = 0; // operand=1

        Deque<Integer> stack = new ArrayDeque<>(); // 13

        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i); // c='3'
            if (isDigit(c)) {
                operand *= 10;
                operand += c-'0'; // operand = 3
            } else {
                int op = operator(c); // op=MULT
                if (op!=-5) {
                    if (stack.isEmpty() || compareOperators(stack.peek(), op)>0) {
                        stack.push(operand);
                    } else {
                        int prevOp = stack.pop(); // PLUS
                        int leftOp = stack.pop();
                        int result = calculate(leftOp, operand, prevOp);
                        stack.push(result);
                    }
                    operand = 0;
                    stack.push(op);
                }
            }
        }

        // Complete
        if (!stack.isEmpty()){
            int operator = stack.pop();
            int leftOp = stack.pop();
            if (stack.isEmpty() || compareOperators(stack.peek(), operator)>0) {
                stack.push(calculate(leftOp, operand, operator));
            } else {
                stack.push(leftOp);
                stack.push(operator);
                stack.push(operand);
            }
        } else {
            stack.push(operand);
        }

        while (stack.size()>1) {
            int leftOp = stack.pollLast();
            int operator = stack.pollLast();
            int rightOp = stack.pollLast();
            stack.offerLast(calculate(leftOp, rightOp, operator));
        }
        return stack.pop();
    }

    private int calculate(int operand1, int operand2, int operator) {
        switch(operator) {
            case PLUS: return operand1+operand2;
            case MINUS: return operand1-operand2;
            case MULT: return operand1*operand2;
            case DIV: return (operand2==0?Integer.MAX_VALUE:operand1/operand2);
        }
        return -1;
    }

    private boolean isDigit(char c) {
        return c>='0' && c<='9';
    }

    private int operator(char c) {
        switch (c) {
            case '+': return PLUS;
            case '-': return MINUS;
            case '*': return MULT;
            case '/': return DIV;
        }
        return -5;
    }

    private int compareOperators(int op1, int op2) {
        if ((op1^op2)==1 || (op1^op2)==0) {
            return 0;
        } else if (op1<op2) {
            return -1;
        }
        return 1;
    }
}